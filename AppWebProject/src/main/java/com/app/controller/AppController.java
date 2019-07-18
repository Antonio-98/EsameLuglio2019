package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Element;
import com.app.model.Metadata;
import com.app.model.Stats;
import com.app.service.AppService;

@RestController
public class AppController {

	// Allo start dell'applicazione viene creato l'oggetto appService, quindi viene
	// effettuato il download e il parsing
	@Autowired
	AppService appService;


	@GetMapping("/data/{indice}")
	public Element data(@PathVariable int indice) {
		return appService.printElement(indice);
	}

	@GetMapping("/data")
	public ArrayList<Element> dataAll() {
		return appService.printElement();
	}

	@GetMapping("/sum")
	public Stats sum() {
		return appService.sum();
	}

	@GetMapping("/avg")
	public Stats avg() {
		return appService.avg();
	}

	@GetMapping("/min")
	public Stats min() {
		return appService.min();
	}

	@GetMapping("/max")
	public Stats max() {
		return appService.max();
	}

	@GetMapping("/stddev")
	public Stats stddev() {
		return appService.stdDev();
	}

	@GetMapping("/metadata")
	public ArrayList<Metadata> metadata() {
		return appService.printMetadata();
	}

	@GetMapping("/stats")
	public ArrayList<Stats> stats() {
		return appService.Stats();
	}

	@GetMapping("/filtroStringa/{field}/{value}")
	public ArrayList<Element> stringFilter(@PathVariable("field") String field, @PathVariable("value") String value) {
		return appService.filter(field, "eq", value);
	}

	@GetMapping("/filtroValore/{field}/{operator}/{value}")
	public ArrayList<Element> valueFilter(@PathVariable("field") String field, @PathVariable("operator") String operator,
			@PathVariable("value") float value) {
		return appService.filter(field, operator, value);
	}
	@GetMapping("/filtroValore/{logicOperator}/{operator1}/{value1}/{operator2}/{value2}")
	public ArrayList<Element> valueFilter( @PathVariable("operator1") String operator1,
			@PathVariable("value1") float value1,@PathVariable("logicOperator") String logicOperator, @PathVariable("operator2") String operator2,
			@PathVariable("value2") float value2) {
		return appService.multifilter(logicOperator,operator1, value1, operator2, value2);
	}
	
	@GetMapping("/filtro/{value1}/{operator2}/{value2}")
	public ArrayList<Element> filter(@PathVariable("value1") String value1,@PathVariable("operator2") String operator2,
			@PathVariable("value2") float value2) {
		return appService.multifilter(value1, operator2, value2);
	}
	
	@GetMapping("/elementiunici/{fieldName}")
	public HashMap<String,Integer> uniqueElem(@PathVariable String fieldName) throws NoSuchMethodException, IllegalAccessException, RuntimeException, ReflectiveOperationException{
		return appService.counter(fieldName);
	}
	
	
}
