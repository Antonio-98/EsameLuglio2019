package com.app.service;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Element;
import com.app.utils.Utils;

@Service
public class AppService {

	static Vector<Element> v = new Vector<Element>();
	
	
	//Costruttore di AppService
	@Autowired
	public AppService() {
		Utils.jsonDecode("http://data.europa.eu/euodp/data/api/3/action/package_show?id=yGVKnIzbkC2ZHpT6jQouDg");			
		Utils.csvParse(v, "dataset.csv");
	}

	
	public Vector<Element> getFilteredData(int value){
		int i=0;
		Vector<Element> newVector = new Vector<Element>();
		for(i=0; i<v.size(); i++)
		{if (v.get(i).getTime_period()==value)
			newVector.add(v.get(i));
		}		
		return newVector;
	}
	


	public Element printElement(int i) {
		return v.get(i);
	}

	public Vector<Element> printElement() {
		return v;
	}
}
