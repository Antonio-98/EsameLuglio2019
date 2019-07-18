package com.app.service;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.model.Element;
import com.app.model.Metadata;
import com.app.model.Stats;
import com.app.utils.Calculator;
import com.app.utils.Utils;

@Service
public class AppService {

	static ArrayList<Element> v = new ArrayList<Element>();
	static ArrayList<Metadata> header = new ArrayList<Metadata>();
	private FilterUtils<Element> filteredData = new FilterUtils<Element>();

	// Costruttore di AppService
	@Autowired
	public AppService() {
		File f = new File("dataset.csv");
		if (!f.exists()) {
			Utils.jsonDecode("http://data.europa.eu/euodp/data/api/3/action/package_show?id=yGVKnIzbkC2ZHpT6jQouDg",
					"dataset.csv");
		}
		Utils.csvParse(v, header, "dataset.csv");
	}

	public Element printElement(int i) {
		if (i > v.size() || i < 0) {
			throw new RuntimeException("Indice non valido");
		}
		return v.get(i);
	}

	public ArrayList<Element> printElement() {
		return v;
	}

	public Stats sum() {
		return new Stats("Sum", "Somma di tutti i valori", Calculator.sum(v));
	}

	public Stats avg() {
		return new Stats("Avg", "Valore medio per pc_schools", Calculator.sum(v) / v.size());
	}

	public Stats min() {
		return new Stats("Min", "Valore minimo per pc_schools", Calculator.min(v));
	}

	public Stats max() {
		return new Stats("Max", "Valore massimo per pc_schools", Calculator.max(v));
	}

	public Stats stdDev() {
		float value = 0;
		int i;
		double avg = avg().getValue();
		for (i = 0; i < v.size(); i++) {
			value += Math.pow(v.get(i).getValue() - avg, 2);
		}
		value /= v.size();
		return new Stats("StdDev", "Deviazione standard dei valori per pc_schools", value);
	}

	public ArrayList<Metadata> printMetadata() {
		return header;
	}

	public ArrayList<Stats> Stats() {
		ArrayList<Stats> statistics = new ArrayList<Stats>();
		statistics.add(sum());
		statistics.add(avg());
		statistics.add(min());
		statistics.add(max());
		statistics.add(stdDev());
		return statistics;
	}

	public ArrayList<Element> filter(String fieldName, String operator, Object value) {
		return (ArrayList<Element>) filteredData.select(v, fieldName, operator, value);

	}

	public ArrayList<Element> multifilter(String logicOperator, String operator1, Object value1, String operator2,
			Object value2) {
		ArrayList<Element> list1 = new ArrayList<Element>();
		list1 = (ArrayList<Element>) filteredData.select(v, "value", operator1, value1);
		if (logicOperator.equals("and")) {
			return (ArrayList<Element>) filteredData.select(list1, "value", operator2, value2);
		} else if (logicOperator.equals("or")) {
			ArrayList<Element> list2 = new ArrayList<Element>();
			list2 = (ArrayList<Element>) filteredData.select(v, "value", operator2, value2);
			return filteredData.merge(list1, list2);
		} else {
			throw new RuntimeException("Operatore logico non valido");
		}
	}

	public ArrayList<Element> multifilter(Object value1, String operator2, Object value2) {
		ArrayList<Element> list1 = new ArrayList<Element>();
		list1 = (ArrayList<Element>) filteredData.select(v, "ref_area", "eq", value1);
		ArrayList<Element> list2 = new ArrayList<Element>();
		list2 = (ArrayList<Element>) filteredData.select(list1, "value", operator2, value2);
		return list2;
	}

	public HashMap<String, Integer> counter(String fieldName)
			throws NoSuchMethodException, RuntimeException, IllegalAccessException, ReflectiveOperationException {
		ArrayList<String> inputColumn = new ArrayList<String>();
		int i;
		for (i = 0; i < v.size(); i++) {
			Method m = v.get(i).getClass()
					.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), null);
			inputColumn.add((String) m.invoke(v.get(i)));
		}
		return Calculator.counter(inputColumn);
	}

}
