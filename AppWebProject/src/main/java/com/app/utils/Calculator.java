package com.app.utils;

import java.util.ArrayList;
import java.util.HashMap;

import com.app.model.Element;

/**
 * Libreria di metodi statici ciascuno dei quali calcola un determinato
 * indicatore matematico relativo al dataset. Ciascun metodo ha come unico
 * parametro L'ArrayList rappresentante il dataset.
 * <p>
 * Indicatori implementati: somma, valore minimo, valore massimo.
 * 
 * @author Giulia Temperini, Paolo Cacciatore
 * @version 1.0
 */
public class Calculator {
	private static float value;

	public static float sum(ArrayList<Element> data) {
		value = 0;
		data.forEach(Element -> value += Element.getValue());
		return value;
	}

	public static float min(ArrayList<Element> data) {
		int i = 0;
		value = 1;
		for (i = 0; i < data.size(); i++) {
			if (data.get(i).getValue() < value) {
				value = data.get(i).getValue();
			} else {
			}
		}
		return value;
	}

	public static float max(ArrayList<Element> data) {
		int i = 0;
		value = 0;
		for (i = 0; i < data.size(); i++) {
			if (data.get(i).getValue() > value) {
				value = data.get(i).getValue();
			} else {
			}
		}

		return value;
	}

	public static HashMap<String, Integer> counter(ArrayList<String> column) {
		int i;
		HashMap<String, Integer> uniqueElements = new HashMap<String, Integer>();
		for (i = 0; i < column.size(); i++) {
			if (!uniqueElements.containsKey(column.get(i))) {
				uniqueElements.put(column.get(i), 1);
			} else {
				uniqueElements.replace(column.get(i), uniqueElements.get(column.get(i)),
						uniqueElements.get(column.get(i)) + 1);
			}
		}
		return uniqueElements;
	}
}
