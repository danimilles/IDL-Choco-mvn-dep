/*
 * generated by Xtext 2.19.0
 */
package es.us.isa.idl.generator

import es.us.isa.idl.idl.ArithmeticDependency
import es.us.isa.idl.idl.ConditionalDependency
import es.us.isa.idl.idl.Dependency
import es.us.isa.idl.idl.GeneralClause
import es.us.isa.idl.idl.GeneralPredefinedDependency
import es.us.isa.idl.idl.GeneralPredicate
import es.us.isa.idl.idl.GeneralTerm
import es.us.isa.idl.idl.Operation
import es.us.isa.idl.idl.OperationContinuation
import es.us.isa.idl.idl.Param
import es.us.isa.idl.idl.RelationalDependency
import es.us.isa.idl.idl.impl.ArithmeticDependencyImpl
import es.us.isa.idl.idl.impl.ConditionalDependencyImpl
import es.us.isa.idl.idl.impl.GeneralPredefinedDependencyImpl
import es.us.isa.idl.idl.impl.GeneralTermImpl
import es.us.isa.idl.idl.impl.ParamImpl
import es.us.isa.idl.idl.impl.RelationalDependencyImpl
import java.util.HashMap
import java.util.List
import java.util.Map
import org.chocosolver.solver.Model
import org.chocosolver.solver.constraints.Constraint
import org.chocosolver.solver.variables.BoolVar
import org.chocosolver.solver.variables.IntVar
import org.chocosolver.solver.variables.Variable
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext

import static es.us.isa.idl.generator.ReservedWords.RESERVED_WORDS

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class IDLGenerator extends AbstractGenerator {

	val MIN_INTEGER = -1000
	val MAX_INTEGER = 1000

	var Map<String, Integer> stringIntMapping = new HashMap
	var Model model = new Model
	var Map<String, Variable> map = new HashMap

	new() {
	}
	
	new(Map<String, Integer> map, Model model) {
		this.stringIntMapping = map
		this.model = model
	}

	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		throw new Exception("Unsupported operation")
	}
	
	def Response doGenerateChocoModel(Resource resource) {
		
		for (dependency: resource.allContents.filter(Dependency).toIterable) {
			var Constraint cons = null
			if (dependency.dep.class == typeof(ConditionalDependencyImpl)) {
				writeConditionalDependency(dependency.dep as ConditionalDependency)
			} else if (dependency.dep.class == typeof(RelationalDependencyImpl)) {
				cons = writeRelationalDependency(dependency.dep as RelationalDependency, true)
			} else if (dependency.dep.class == typeof(ArithmeticDependencyImpl)) {
				cons = writeArithmeticDependency(dependency.dep as ArithmeticDependency, true)
			} else if (dependency.dep.class == typeof(GeneralPredefinedDependencyImpl)) {
				cons = writePredefinedDependency(dependency.dep as GeneralPredefinedDependency)
			} else {
				throw new Exception("The dependency must be a conditional, an " + 
					"arithmetic, a relational or a predefined one")
			}
			
			if(cons !== null){
				model.post(cons)
			}
		}
		
		
        return new Response(stringIntMapping, model)
	}
	
	/**
	 * Surround double with brackets if it's negative, and remove decimals (MiniZinc does not support floats)
	 */
	def private String parseDouble(String doubleValue) {
		val doubleWithoutDec = doubleValue.replaceAll("\\.\\d+", "")
		return doubleWithoutDec
	}
	
	/**
	 * Remove and replace special characters from paramName
	 */
	def private String parseIDLParamName(String paramName) {
		var String parsedParamName = paramName.replaceAll("^\\[|\\]$", "").replaceAll("[\\.\\-\\/\\:\\[\\]]", "_")
		if (RESERVED_WORDS.contains(parsedParamName))
			parsedParamName += "_R"
		return parsedParamName
	}

	def private Integer stringToInt(String stringValue) {
		val Integer intMapping = stringIntMapping.get(stringValue)
		if (intMapping !== null) {
			return intMapping
		} else {
			var int size = stringIntMapping.entrySet.size
			stringIntMapping.put(stringValue, size)
			return size
		}
	}
	
	def private getVariable(String name, Class<? extends Variable> type, int... domain) {
		var paramVar = map.get(name)
		if (paramVar !== null) {
			return paramVar
		} else {
			if(type == typeof(BoolVar)){
				map.put(name, model.boolVar(name))
			} else if (type == typeof(IntVar)){
				map.put(name, model.intVar(name, domain.size == 2 ? domain.get(0) : MIN_INTEGER, domain.size==2 ? domain.get(1) : MAX_INTEGER))
			}
			return map.get(name)
		}
	}
	
	/**
	 * Returns true if param is actually a ParamValueRelation. False if it is a Param
	 */
	def private boolean isParamValueRelation(Param param) {
		return param.stringValues.size !== 0 || param.patternString !== null || param.booleanValue !== null || param.doubleValue !== null
	}
	
	def private Constraint writePredicate(GeneralPredicate predicate) {
		var Constraint constraint = writeClause(predicate.firstClause)
		
		if (predicate.clauseContinuation !== null) {
			// Solve second element, which is a clause continuation containing a predicate
			if (predicate.clauseContinuation.logicalOp == "AND") {
				constraint = model.and(constraint, writePredicate(predicate.clauseContinuation.additionalElements))
			} else if (predicate.clauseContinuation.logicalOp == "OR") {
				constraint = model.or(constraint, writePredicate(predicate.clauseContinuation.additionalElements))
			} else {
				throw new Exception("The logical operator can only be AND or OR")
			}
		}
		return constraint		
	}
	
	def private Constraint writeClause(GeneralClause clause) {
		var Constraint constraint = null
		var Constraint temp = null
		
		if (clause.predicate !== null) {
			constraint = writePredicate(clause.predicate)
			if (clause.not !== null){
				constraint = model.not(constraint)		
			}
		}
		
		// Solve firstElement, which can be a term, arithmetic dep, relational dep or predefined dep
		if (clause.firstElement !== null) {
			if (clause.firstElement.class == typeof(GeneralTermImpl)) { // param or param assignment
				val GeneralTerm term = (clause.firstElement as GeneralTerm)
				val Param param = (term.param as Param)

				var name = parseIDLParamName(param.name)
				var BoolVar paramSet = getVariable(name + "Set", BoolVar).asBoolVar
				var paramSetVar = model.arithm(paramSet, "=", 1)
				var Constraint paramVar = null
				
				if (isParamValueRelation(param)) {
					if (param.booleanValue !== null) {
						var BoolVar boolVar = getVariable(name, BoolVar).asBoolVar
						paramVar = model.arithm(boolVar, "=", Boolean.parseBoolean(param.booleanValue) ? 1 : 0)
					} else if (param.doubleValue !== null) {
						var IntVar intVar = getVariable(name, IntVar).asIntVar
						paramVar = model.arithm(intVar, param.relationalOp.equals("==")? "=" : param.relationalOp, Integer.parseInt(parseDouble(param.doubleValue)))
					} else if (param.stringValues.size !== 0) {
						var IntVar intVar = getVariable(name, IntVar).asIntVar
						var List<Constraint> constraints = newArrayList
						
						for (string: param.stringValues) {
							constraints.add(model.arithm(intVar, "=", stringToInt(string)))
						}
						paramVar = constraints.size > 1 ? model.or(constraints) : constraints.get(0)
					} else if (param.patternString !== null) {
						// TODO: Implement CSP mapping (none for now)
					}
				}
				
				if(paramVar !== null){
					temp = model.and(paramSetVar, paramVar)
				} else{
					temp = paramSetVar
				}
				if (term.not !== null){
					temp = model.not(temp)
				}
				
			
			} else if(clause.firstElement.class == typeof(RelationalDependencyImpl)) {
				temp = writeRelationalDependency(clause.firstElement as RelationalDependency, false)
			} else if(clause.firstElement.class == typeof(ArithmeticDependencyImpl)) {
				temp = writeArithmeticDependency(clause.firstElement as ArithmeticDependency, false)
			} else if(clause.firstElement.class == typeof(GeneralPredefinedDependencyImpl)) {
				temp = writePredefinedDependency(clause.firstElement as GeneralPredefinedDependency)
			} else {
				throw new Exception("The first element of a clause must be a term, an " + 
					"arithmetic dependency, a relational dependency or a predefined dependency")
			}
		}
		
		if(constraint !== null && temp !== null) {
			constraint = model.and(constraint, temp)
		} else if (temp !== null) {
			return temp
		} else{
			return constraint
		}
	}
	
	def private void writeConditionalDependency(ConditionalDependency dep) {
		var condition = writePredicate(dep.condition)
		var consecuence = writePredicate(dep.consequence)
		model.ifThen(condition, consecuence);
	}

	def private Constraint writeRelationalDependency(RelationalDependency dep, boolean alone) {
		var nameParam1 = parseIDLParamName(dep.param1.name)
		var nameParam2 = parseIDLParamName(dep.param2.name)
		var param1SetVar = getVariable(nameParam1 + "Set", BoolVar).asBoolVar
		var param2SetVar = getVariable(nameParam2 + "Set", BoolVar).asBoolVar
		var ifParamsSet = model.and(param1SetVar, param2SetVar)
		var IntVar intVar1 = getVariable(nameParam1, IntVar).asIntVar
		var IntVar intVar2 = getVariable(nameParam2, IntVar).asIntVar
		var relationalOperation = model.arithm(intVar1, dep.relationalOp.equals("==") ? "=" : dep.relationalOp, intVar2)
		
		if (alone){
			model.ifThen(ifParamsSet, relationalOperation)
			return null
		} else {
			return model.and(ifParamsSet, relationalOperation)
		}
	}
	
	def private Constraint writeArithmeticDependency(ArithmeticDependency dep, boolean alone) {
		var List<BoolVar> paramsSet = newArrayList
		for (param: dep.eAllContents.filter(Param).toIterable) {
			paramsSet.add(getVariable(parseIDLParamName(param.name) + "Set", BoolVar).asBoolVar)
		}
		var constaint = model.and(paramsSet)
		
		var IntVar intVar = writeOperation(dep.operation)
		var continuation = model.arithm(intVar, dep.relationalOp, Integer.parseInt(parseDouble(dep.result)))
		
		if (alone) {
			model.ifThen(constaint, continuation)
			return null 
		} else {
			return model.and(constaint, continuation)
		}
	}
	
	def private IntVar writeOperation(Operation operation) {
		if (operation.openingParenthesis === null) {
			var IntVar intVar = getVariable(parseIDLParamName(operation.firstParam.name), IntVar).asIntVar
			if(operation.operationContinuation.arithOp!==null){
				return getArithmOperation(intVar, operation.operationContinuation.arithOp, writeOperationContinuation(operation.operationContinuation))
			} else{
				return writeOperationContinuation(operation.operationContinuation)
			}
		} else { // Alternative 2 of Operation
			var IntVar intVar = writeOperation(operation.operation)
			if (operation.operationContinuation !== null) {
				return getArithmOperation(intVar, operation.operationContinuation.arithOp, writeOperationContinuation(operation.operationContinuation))
			}
			return intVar
		}
	}
	
	def private IntVar getArithmOperation(IntVar intVar1, String arithmOp, IntVar intVar2){
		switch (arithmOp) {
			case "+":
				return intVar1.add(intVar2).intVar
			case "-":
				return intVar1.sub(intVar2).intVar
			case "*":
				return intVar1.mul(intVar2).intVar
			case "/":
				return intVar1.div(intVar2).intVar
			default:
				throw new Exception("Arithmetic operation not supported: " + arithmOp)
		}
	}
	
	def private IntVar writeOperationContinuation(OperationContinuation opCont) {
		var IntVar intVar = null
		if (opCont.additionalParams.class == typeof(ParamImpl)) {
			intVar = getVariable(parseIDLParamName((opCont.additionalParams as Param).name), IntVar).asIntVar
		} else {
			intVar = writeOperation(opCont.additionalParams as Operation)
		}
		return intVar
	}
	
	def private Constraint writePredefinedDependency(GeneralPredefinedDependency dep) {		
		var Constraint cons = null
		
		var List<BoolVar> contraintsList = newArrayList
		for (depElement: dep.predefDepElements) {
			 contraintsList.add(writePredicate(depElement).reify)
		}
		
		switch dep.predefDepType {
			case "Or": {
				cons = model.or(contraintsList)			
			} case "OnlyOne": {
				cons = model.sum(contraintsList, "=", 1)
			} case "AllOrNone": {
				cons = model.allEqual(contraintsList)
			} case "ZeroOrOne": {
				cons = model.or(model.sum(contraintsList, "=", 1), model.sum(contraintsList, "=", 0))
			} default:
				throw new Exception("The predefined dependency can only be 'Or', " + 
					"'OnlyOne', 'AllOrNone' or 'ZeroOrOne'")
		}

		if (dep.not !== null){
			cons = model.not(cons)
		} 
		return cons		
	}
		
}

