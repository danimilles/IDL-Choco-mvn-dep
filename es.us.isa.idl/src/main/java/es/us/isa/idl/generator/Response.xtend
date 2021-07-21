package es.us.isa.idl.generator

import java.util.Map
import org.chocosolver.solver.Model
import org.chocosolver.solver.variables.Variable

class Response {
	Map<String, Integer> stringToIntMap
	Model chocoModel
	Map<String, Variable> variablesMap
	
	new(Map<String, Integer> map, Model model, Map<String, Variable> variablesMap) {
		this.stringToIntMap = map
		this.chocoModel = model
		this.variablesMap = variablesMap
	}
	
	def setVariablesMap(Map<String, Variable> variablesMap){
		this.variablesMap = variablesMap
	}
	
	def getVariablesMap(){
		return variablesMap
	}
	
	def setStringToIntMap(Map<String, Integer> map){
		this.stringToIntMap = map
	}
	
	def getStringToIntMap(){
		return stringToIntMap
	}
	
	def setChocoModel(Model model){
		this.chocoModel = model
	}
	
	def getChocoModel(){
		return chocoModel
	}
}