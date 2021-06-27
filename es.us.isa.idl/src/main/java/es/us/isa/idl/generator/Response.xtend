package es.us.isa.idl.generator

import java.util.Map
import org.chocosolver.solver.Model

class Response {
	Map<String, Integer> stingToInt
	Model chocoModel
	
	new(Map<String, Integer> map, Model model) {
		this.stingToInt = stingToInt
		this.chocoModel = chocoModel
	}
}