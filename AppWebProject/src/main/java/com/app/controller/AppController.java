package com.app.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.app.model.Element;
import com.app.model.Metadata;
import com.app.model.Stats;
import com.app.service.AppService;

/**
 * Classe che gestisce le richieste HTTP da parte dell'utente.
 * 
 * @author Giulia Temperini, Paolo Cacciatore
 * @version 1.0
 */
@RestController
public class AppController {

	@Autowired
	AppService appService;

	/**
	 * Restituisce l'i-esimo elemento/record del dataset.
	 * 
	 * @param indice Numero elemento/record da restituire
	 * @return {@link Element}
	 */
	@GetMapping("/data/{indice}")
	public Element data(@PathVariable int indice) {
		return appService.printElement(indice);
	}

	/**
	 * Restituisce l'intero dataset rappresentato da un ArrayList di oggetti
	 * {@link Element}.
	 * 
	 * @return Tutti gli elementi/record del dataset
	 */
	@GetMapping("/data")
	public ArrayList<Element> dataAll() {
		return appService.printElement();
	}

	/**
	 * Restituisce la somma di tutti i valori assunti dall'attributo pc_schools.
	 * 
	 * @return Indicatore matematico calcolato
	 * @see com.app.model.Stats
	 */
	@GetMapping("/sum")
	public Stats sum() {
		return appService.sum();
	}

	/**
	 * Restituisce il valore medio calcolato su tutti i valori assunti
	 * dall'attributo pc_schools.
	 * 
	 * @return Valore medio di pc_schools
	 * @see com.app.model.Stats
	 */
	@GetMapping("/avg")
	public Stats avg() {
		return appService.avg();
	}

	/**
	 * Restituisce il valore minimo calcolato su tutti i valori assunti
	 * dall'attributo pc_schools nel dataset
	 * 
	 * @return Valore minimo di pc_schools
	 * @see com.app.model.Stats
	 * @see com.app.utils.Calculator
	 */
	@GetMapping("/min")
	public Stats min() {
		return appService.min();
	}

	/**
	 * Restituisce il valore massimo calcolato su tutti i valori assunti
	 * dall'attributo pc_schools.
	 * 
	 * @return Valore massimo di pc_schools
	 * @see com.app.model.Stats
	 * @see com.app.utils.Calculator
	 */
	@GetMapping("/max")
	public Stats max() {
		return appService.max();
	}

	/**
	 * Restituisce la deviazione standard calcolata su tutti i valori assunti
	 * dall'attributo pc_schools.
	 * 
	 * @return Deviazione standard di pc_schools
	 * @see com.app.model.Stats
	 * @see com.app.utils.Calculator
	 */
	@GetMapping("/stddev")
	public Stats stddev() {
		return appService.stdDev();
	}

	/**
	 * Restituisce l'elenco dei metadati del dataset.
	 * 
	 * @return Elenco di tutti i metadati.
	 * @see com.app.model.Metadata
	 */
	@GetMapping("/metadata")
	public ArrayList<Metadata> metadata() {
		return appService.printMetadata();
	}

	/**
	 * Restituisce l'elenco dei indicatori matematici/statistici relativi al
	 * dataset.
	 * 
	 * @return Elenco di tutti gli indicatori matematici/statistici
	 * @see com.app.model.Stats
	 */
	@GetMapping("/stats")
	public ArrayList<Stats> stats() {
		return appService.Stats();
	}

	/**
	 * Effettua il filtraggio del dataset i cui criteri sono descritti dai parametri
	 * variabili immessi dal client dati in ingresso e restituisce tutti elementi
	 * filtrati. Il parametro field identifica l'attributo di tipo Stringa su cui
	 * applicare il filtro. Se un elemento del dataset ha come valore dell'attributo
	 * considerato lo stesso di value allora tale elemento viene aggiunto
	 * all'ArrayList contenente gli elementi filtrati.
	 * 
	 * @param field Identificatore dell'attributo su cui applicare il filtro
	 * @param value Valore di soglia/confronto
	 * @return Elenco degli elementi filtrati
	 */
	@GetMapping("/filtroStringa/{field}/{value}")
	public ArrayList<Element> stringFilter(@PathVariable("field") String field, @PathVariable("value") String value) {
		return appService.filter(field, "eq", value);
	}

	/**
	 * Effettua il filtraggio del dataset i cui criteri sono descritti dai parametri
	 * dati in ingresso e restituisce tutti elementi filtrati. Il parametro field di
	 * tipo numerico identifica l'attributo su cui applicare il filtro. Per ciascun
	 * elemento del dataset viene effettuato il confronto, definito da operator, tra
	 * il valore dell'attributo considerato e value. Se l'esito del confronto
	 * &egrave; positivo l'elemento viene aggiunto all'ArrayList contenente gli
	 * elementi filtrati.
	 * 
	 * @param field    Identificatore dell'attributo su cui applicare il filtro
	 * @param operator Operatore di confronto
	 * @param value    Valore di soglia/confronto
	 * @return elenco degli elementi filtrati
	 */
	@GetMapping("/filtroValore/{field}/{operator}/{value}")
	public ArrayList<Element> valueFilter(@PathVariable("field") String field,
			@PathVariable("operator") String operator, @PathVariable("value") float value) {
		return appService.filter(field, operator, value);
	}

	/**
	 * Effettua la combinazione di due filtri applicati al dataset i cui criteri
	 * sono descritti dai parametri dati in ingresso e restituisce tutti elementi
	 * filtrati. L'attributo consideerato è <strong>value</strong>.
	 * <p>
	 * Il parametro logicOperator sta ad indicare se deve essere effettuata l'unione
	 * o l'intersezione dei 2 singoli filtri.
	 * <p>
	 * I singoli filtri applicano, per ogni elemento del dataset, un semplice
	 * confronto definito da operatorX tra il valore dell'attributo considerato e
	 * valueX. Se l'esito del confronto &egrave; positivo l'elemento viene aggiunto
	 * all'ArrayList contenente gli elementi filtrati.
	 * <p>
	 * Per l'operazione di unione si applicano i filtri separatamente e
	 * successivamente si uniscono gli ArrayList risultanti. Per l'operazione di
	 * intersezione il secondo filtro viene applicato all'ArrayList risultante dal
	 * primo filtro e non all'intero dataset.
	 * 
	 * @param logicOperator Operatore di insieme
	 * @param value1        Valore di soglia/confronto per il primo filtro
	 * @param value2        Valore di soglia/confronto per il secondo filtro
	 * @param operator1     Operatore di confronto per il primo filtro
	 * @param operator2     Operatore di confronto per il secondo filtro
	 * @return Elenco degli elementi filtrati
	 */
	@GetMapping("/filtroValore/{logicOperator}/{operator1}/{value1}/{operator2}/{value2}")
	public ArrayList<Element> valueFilter(@PathVariable("operator1") String operator1,
			@PathVariable("value1") float value1, @PathVariable("logicOperator") String logicOperator,
			@PathVariable("operator2") String operator2, @PathVariable("value2") float value2) {
		return appService.multifilter(logicOperator, operator1, value1, operator2, value2);
	}

	/**
	 * Effettua un filtro combinato dato dalla serie di due filtri. Il risultato del
	 * primo è un elenco di {@link Element} caratterizzati da ref_area=value1. A
	 * tale elenco viene applicato un secondo filtro sull'attributo value. Il
	 * criterio del filtro è definito da operator2.
	 * 
	 * @param value1    Valore di confronto per il primo filtro
	 * @param operator2 Operatore condizionale
	 * @param value2    Valore di soglia/confronto per il secondo filtro
	 * @return Elenco degli elementi filtrati
	 * @throws RuntimeException se l'operatore condizionale inserito &egrave; errato
	 */
	@GetMapping("/filtro/{value1}/{operator2}/{value2}")
	public ArrayList<Element> filter(@PathVariable("value1") String value1, @PathVariable("operator2") String operator2,
			@PathVariable("value2") float value2) {
		return appService.multifilter(value1, operator2, value2);
	}

	/**
	 * Conteggia i valori unici assunti da un determinato attributo, per ogni valore
	 * unico indica il numero di occorrenze.
	 * 
	 * @param fieldName Attributo da considerare
	 * @return Elenco dei valori unici con le rispettive occorrenze
	 * @throws NoSuchMethodException
	 * @throws RuntimeException
	 * @throws IllegalAccessException
	 * @throws ReflectiveOperationException
	 */
	@GetMapping("/elementiunici/{fieldName}")
	public HashMap<String, Integer> uniqueElem(@PathVariable String fieldName)
			throws NoSuchMethodException, IllegalAccessException, RuntimeException, ReflectiveOperationException {
		return appService.counter(fieldName);
	}

}
