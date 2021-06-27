package es.us.isa.idl.generator

import java.util.Map
import org.chocosolver.solver.Model

class Response {
	Map<String, Integer> stringToIntMap
	Model chocoModel
	
	new(Map<String, Integer> map, Model model) {
		this.stringToIntMap = map
		this.chocoModel = model
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