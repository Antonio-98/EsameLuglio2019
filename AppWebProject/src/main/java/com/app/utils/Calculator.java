package com.app.utils;

import java.util.ArrayList;

import com.app.model.Element;
import com.app.model.Stats;

public class Calculator {
	/*
	 * metodi che calcolano la varianza, valor medio, massimo, minimo, somma,
	 * conteggio
	 */

	private static float value;

	public static float Sum(ArrayList<Element> data) {
		value=0;
		data.forEach(Element -> value += Element.getValue());
		return value;
	}
	
	public static float Min(ArrayList<Element> data) {
		int i = 0;
		value = 1;
		for (i=0; i<data.size();i++) {
			if (data.get(i).getValue()<value){
				value = data.get(i).getValue();
			}else {
			}
		}
		return value;
	}
	
	public static float Max(ArrayList<Element> data) {
		int i = 0;
		value=0;
		for (i=0; i<data.size();i++) {
			if (data.get(i).getValue()>value){
				value = data.get(i).getValue();
			}else {
			}
		}
		
		return value;
	}
}
