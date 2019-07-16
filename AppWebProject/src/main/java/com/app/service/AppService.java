package com.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Element;
import com.app.utils.Calculator;
import com.app.utils.Utils;

@Service
public class AppService {

	static ArrayList<Element> v = new ArrayList<Element>();

	// Costruttore di AppService
	@Autowired
	public AppService() {
		Utils.jsonDecode("http://data.europa.eu/euodp/data/api/3/action/package_show?id=yGVKnIzbkC2ZHpT6jQouDg");
		Utils.csvParse(v, "dataset.csv");
	}

	public ArrayList<Element> getFilteredData(int value) {
		int i = 0;
		ArrayList<Element> newArrayList = new ArrayList<Element>();
		for (i = 0; i < v.size(); i++) {
			if (v.get(i).getTime_period() == value)
				newArrayList.add(v.get(i));
		}
		return newArrayList;
	}

	public ArrayList<Element> getFilteredData(String par, String value) {
		int i = 0;
		ArrayList<Element> newArrayList = new ArrayList<Element>();
		switch (par) {
		case "area":
			for (i = 0; i < v.size(); i++) {
				if (v.get(i).getRef_area().equals(value))
					newArrayList.add(v.get(i));
			}
			break;
		case "indicator":
			for (i = 0; i < v.size(); i++) {
				if (v.get(i).getIndicator().equals(value))
					newArrayList.add(v.get(i));
			}
			break;
		}
		return newArrayList;
	}

	public ArrayList<Element> getFilteredData(String par, float value) {
		int i = 0;
		ArrayList<Element> newArrayList = new ArrayList<Element>();
		switch (par) {
		case "min":
			for (i = 0; i < v.size(); i++) {
				if (v.get(i).getValue() > value)
					newArrayList.add(v.get(i));
			}
			break;
		case "max":
			for (i = 0; i < v.size(); i++) {
				if (v.get(i).getValue() < value)
					newArrayList.add(v.get(i));
			}
			break;
		}
		return newArrayList;
	}

	public Element printElement(int i) {
		return v.get(i);
	}

	public ArrayList<Element> printElement() {
		return v;
	}

	public float Sum() {
		return Calculator.Sum(v);
	}

	public float Avg() {
		return Calculator.Sum(v) / v.size();
	}

	public float Min() {
		return Calculator.Min(v);
	}

	public float Max() {
		return Calculator.Max(v);
	}

	public float StdDev() {
		float value = 0;
		int i;
		double avg = Avg();
		for (i = 0; i < v.size(); i++) {
			value += Math.pow(v.get(i).getValue() - avg, 2);
		}
		value /= v.size();
		return value;
	}
}
