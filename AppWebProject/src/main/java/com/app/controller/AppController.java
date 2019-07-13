package com.app.controller;

import java.util.ArrayList;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Element;
import com.app.service.AppService;

@RestController
public class AppController {
		
	//Allo start dell'applicazione viene creato l'oggetto appService, quindi viene effettuato il download e il parsing
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
	
	/*@GetMapping("/dataFiltered")
	public Vector<Element> dataFilter(@RequestParam(name="field", defaultValue="") String fielname) {
		return appService.getFilteredData(fieldname, "", "");
	}*/
	
	//esempio di filtro prova con numero 2006, ritorna tutti i dati con time_period=2006
	@GetMapping("/filtro/{numero}")
	public ArrayList<Element> data(@PathVariable int numero)
		{
		return appService.getFilteredData(numero);
	}
}
