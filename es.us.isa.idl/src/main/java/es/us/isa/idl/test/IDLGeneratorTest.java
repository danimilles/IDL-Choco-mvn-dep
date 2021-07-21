package es.us.isa.idl.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.Variable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.google.common.collect.HashBiMap;
import com.google.inject.Injector;

import es.us.isa.idl.IDLStandaloneSetupGenerated;
import es.us.isa.idl.generator.IDLGenerator;
import es.us.isa.idl.generator.Response;


public class IDLGeneratorTest {
    private static final String DUMMY_URI = "dummy:/dummy.idl";

	private Map<String, Integer> stringToIntMap;
	private Map<String, Variable> variablesMap;
	private Model chocoModel;
	
	@BeforeEach
	private void setUp(TestInfo info) {
		this.stringToIntMap = new HashMap<>();
   	 	this.variablesMap = new HashMap<>();
   	 	this.chocoModel = new Model(info.getDisplayName());
	}
	
	private void getModelFromIdl (String idl, boolean inverse) throws Exception {
	   	Map<String, Integer> stringToIntMap = new HashMap<>();
	   	Map<String, Variable> variablesMap = new HashMap<>();
	   	IDLGenerator idlGenerator = new IDLGenerator(stringToIntMap, variablesMap, chocoModel);
        Injector injector = new IDLStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        Resource resource = resourceSet.createResource(URI.createURI(DUMMY_URI));
        InputStream in = new ByteArrayInputStream(idl.getBytes());
        
        resource.load(in, resourceSet.getLoadOptions());
        Response response = idlGenerator.doGenerateChocoModel(resource, inverse, null);
        this.stringToIntMap = HashBiMap.create(response.getStringToIntMap());
        this.chocoModel = response.getChocoModel();
        this.variablesMap = response.getVariablesMap();
	}
	        
	
    @Test
    public void no_dependencies(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.NO_DEPENDENCIES, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(chocoModel.getCstrs().length == 0 && variablesMap.size() == 0, "Model should have one constraints and two variables");
    	System.out.println("Test passed: no_dependencies.");
    }
    
    @Test
    public void no_dependencies_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.NO_DEPENDENCIES, false);             
        assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
        assertTrue(chocoModel.getCstrs().length == 0 && variablesMap.size() == 0, "Model should have one constraints and two variables");
        System.out.println("Test passed: no_dependencies_inverse.");
    }
    

    @Test
    public void one_dep_requires(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_REQUIRES, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 2, "Model should have 2 variables");
        assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
        System.out.println("Test passed: one_dep_requires.");
    }

    @Test
    public void one_dep_requires_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_REQUIRES, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 2, "Model should have 2 variables");
        assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_requires_inverse.");
    }

    @Test
    public void one_dep_or(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_OR, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 2, "Model should have 2 variables");
        assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_or.");
    }
    
    @Test
    public void one_dep_or_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_OR, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 2, "Model should have 2 variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_or_inverse.");
    }

    @Test
    public void one_dep_onlyone(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_ONLY_ONE, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 2, "Model should have 2 variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_onlyone.");
    }
    
    @Test
    public void one_dep_onlyone_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_ONLY_ONE, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 2, "Model should have 2 variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_onlyone_inverse.");
    }

    @Test
    public void one_dep_allornone(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_ALL_OR_NONE, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 2, "Model should have 2 variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_allornone.");
    }
    
    @Test
    public void one_dep_allornone_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_ALL_OR_NONE, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 2, "Model should have 2 variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_allornone_inverse.");
    }

    @Test
    public void one_dep_zeroorone(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_ZERO_OR_ONE, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 2, "Model should have 2 variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_zeroorone.");
    }
    
    @Test
    public void one_dep_zeroorone_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_ZERO_OR_ONE, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 2, "Model should have 2 variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_zeroorone_inverse.");
    }

    @Test
    public void one_dep_arithrel(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_ARITHM_REL, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 4, "Model should have 4 variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_arithrel.");
    }
    
    @Test
    public void one_dep_arithrel_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_ARITHM_REL, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 4, "Model should have 4 variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_arithrel_inverse.");
    }

    @Test
    public void one_dep_complex(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_COMPLEX, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 3, "Model should have 3 variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_complex.");
    }
    
    @Test
    public void one_dep_complex_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.ONE_DEPENDENCY_COMPLEX, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 3, "Model should have 3 variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: one_dep_complex_inverse.");
    }

    @Test
    public void combinatorial1(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_1, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 10, "Model should have 10 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 5, "Model should have 5 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 5, "Model should have 5 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial1.");
    }
    
    @Test
    public void combinatorial1_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_1, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 10, "Model should have 10 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 5, "Model should have 5 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 5, "Model should have 5 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial1_inverse.");
    }

    @Test
    public void combinatorial2(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_2, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 16, "Model should have 16 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 10, "Model should have 10 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 6, "Model should have 6 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial2.");
    }
    
    @Test
    public void combinatorial2_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_2, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 16, "Model should have 18 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 10, "Model should have 9 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 6, "Model should have 9 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial2_inverse.");
    }

    @Test
    public void combinatorial3(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_3, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 10, "Model should have 10 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 5, "Model should have 5 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 5, "Model should have 5 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial3.");
    }
    
    @Test
    public void combinatorial3_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_3, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 10, "Model should have 10 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 5, "Model should have 5 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 5, "Model should have 5 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial3_inverse.");
    }

    @Test
    public void combinatorial4(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_4, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 17, "Model should have 17 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 9, "Model should have 9 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 8, "Model should have 8 value variables");
    	assertFalse(chocoModel.getSolver().solve(), "Model should NOT be solvable");
    	System.out.println("Test passed: combinatorial4.");
    }
    
    @Test
    public void combinatorial4_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_4, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 17, "Model should have 17 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 9, "Model should have 9 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 8, "Model should have 8 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial4_inverse.");
    }

    @Test
    public void combinatorial5(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_5, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 16, "Model should have 16 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 8, "Model should have 8 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 8, "Model should have 8 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial5.");
    }
    
    @Test
    public void combinatorial5_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_5, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 16, "Model should have 16 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 8, "Model should have 8 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 8, "Model should have 8 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial5_inverse.");
    }

    @Test
    public void combinatorial6(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_6, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 10, "Model should have 10 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 5, "Model should have 5 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 5, "Model should have 5 value variables");
    	assertFalse(chocoModel.getSolver().solve(), "Model should NOT be solvable");
    	System.out.println("Test passed: combinatorial6.");
    }
    
    @Test
    public void combinatorial6_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_6, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 10, "Model should have 10 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 5, "Model should have 5 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 5, "Model should have 5 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial6_inverse.");
    }

    @Test
    public void combinatorial7(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_7, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 10, "Model should have 10 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 5, "Model should have 5 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 5, "Model should have 5 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial7.");
    }
    
    @Test
    public void combinatorial7_inverso(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_7, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 10, "Model should have 10 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 5, "Model should have 5 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 5, "Model should have 5 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should  be solvable");
    	System.out.println("Test passed: combinatorial7_inverse.");
    }

    @Test
    public void combinatorial8(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_8, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 19, "Model should have 19 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 10, "Model should have 10 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 9, "Model should have 9 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial8.");
    }
    
    @Test
    public void combinatorial8_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_8, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 19, "Model should have 19 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 10, "Model should have 10 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 9, "Model should have 9 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial8_inverse.");
    }

    @Test
    public void combinatorial9(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_9, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 9, "Model should have 9 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 6, "Model should have 6 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 3, "Model should have 3 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial9.");
    }
    
    @Test
    public void combinatorial9_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_9, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 9, "Model should have 9 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 6, "Model should have 6 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 3, "Model should have 3 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial9_inverse.");
    }

    @Test
    public void combinatorial10(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_10, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 10, "Model should have 10 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 5, "Model should have 5 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 5, "Model should have 5 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial10.");
    }
    
    @Test
    public void combinatorial10_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.COMBINATORIAL_10, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 10, "Model should have 10 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 5, "Model should have 5 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 5, "Model should have 5 value variables");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: combinatorial10_inverse.");
    }
    
    @Test
    public void conflictiveParameterNames1(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.CONFLICTIVE_PARAMETER_NAMES_1, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 19, "Model should have 19 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 10, "Model should have 10 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 9, "Model should have 9 value variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.equals("something_one")).count() == 1, "Parameter '[something[one]' must be parsed to 'something_one'");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: conflictiveParameterNames1.");
    }
    
    @Test
    public void conflictiveParameterNames1_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.CONFLICTIVE_PARAMETER_NAMES_1, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 19, "Model should have 19 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 10, "Model should have 10 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 9, "Model should have 9 value variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.equals("something_one")).count() == 1, "Parameter '[something[one]' must be parsed to 'something_one'");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: conflictiveParameterNames1_inverse.");
    }
    
    @Test
    public void conflictiveParameterNames2(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.CONFLICTIVE_PARAMETER_NAMES_2, true);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 10, "Model should have 10 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 6, "Model should have 6 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 4, "Model should have 4 value variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.equals("something_one")).count() == 1, "Parameter '[something[one]' must be parsed to 'something_one'");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: conflictiveParameterNames2.");
    }
    
    @Test
    public void conflictiveParameterNames2_inverse(TestInfo info) throws Exception {
    	getModelFromIdl(IDLExamplesForTest.CONFLICTIVE_PARAMETER_NAMES_2, false);             
    	assertTrue(chocoModel.getName().equals(info.getDisplayName()), "Model should be not null and named " + info.getDisplayName());
    	assertTrue(variablesMap.size() == 10, "Model should have 10 variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.contains("Set")).count() == 6, "Model should have 6 'SET' variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->!x.contains("Set")).count() == 4, "Model should have 4 value variables");
    	assertTrue(variablesMap.keySet().stream().filter(x->x.equals("something_one")).count() == 1, "Parameter '[something[one]' must be parsed to 'something_one'");
    	assertTrue(chocoModel.getSolver().solve(), "Model should be solvable");
    	System.out.println("Test passed: conflictiveParameterNames2_inverse.");
    }
}
