package com.app.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Element;
import com.app.service.AppService;

@RestController
public class AppController {

	// Allo start dell'applicazione viene creato l'oggetto appService, quindi viene
	// effettuato il download e il parsing
	@Autowired
	AppService appService;
	/*
	 * @GetMapping("/stampa/{indice}") public String stampa(@PathVariable int
	 * indice) { String s = AppService.printElement(indice); return s; }/*
	 * 
	 * @GetMapping("/hello") public String stampa() { //String s =
	 * AppService.printElement(); return "hello"; }
	 */

	@GetMapping("/stampa/{indice}")
	public Element stampa(@PathVariable int indice) {
		return appService.printElement(indice);
	}

	@GetMapping("/stampa")
	public ArrayList<Element> stampa() {
		return appService.printElement();
	}

	/*
	 * @GetMapping("/dataFiltered") public Vector<Element>
	 * dataFilter(@RequestParam(name="field", defaultValue="") String fielname) {
	 * return appService.getFilteredData(fieldname, "", ""); }
	 */

	// esempio di filtro prova con numero 2006, ritorna tutti i dati con
	// time_period=2006
	@GetMapping("/filtro/anno/{numero}")
	public ArrayList<Element> data1(@PathVariable int numero) {
		return appService.getFilteredData(numero);
	}

	@GetMapping("/filtro/area/{parameter}")
	public ArrayList<Element> data2(@PathVariable String parameter) {
		return appService.getFilteredData("area", parameter);
	}

	@GetMapping("/filtro/indicator/{parameter}")
	public ArrayList<Element> data3(@PathVariable String parameter) {
		return appService.getFilteredData("indicator", parameter);
	}

	@GetMapping("/filtro/min/{value}")
	public ArrayList<Element> data4(@PathVariable float value) {
		return appService.getFilteredData("min", value);
	}

	@GetMapping("/filtro/max/{value}")
	public ArrayList<Element> data5(@PathVariable float value) {
		return appService.getFilteredData("max", value);
	}

	@GetMapping("/sum")
	public float data6() {
		return appService.Sum();
	}

	@GetMapping("/avg")
	public float data7() {
		return appService.Avg();
	}

	@GetMapping("/min")
	public float data8() {
		return appService.Min();
	}

	@GetMapping("/max")
	public float data9() {
		return appService.Max();
	}

	@GetMapping("/stddev")
	public float data10() {
		return appService.StdDev();
	}
}
