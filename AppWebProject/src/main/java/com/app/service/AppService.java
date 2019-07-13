package com.app.service;

import java.util.ArrayList;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Element;
import com.app.utils.Utils;

@Service
public class AppService {

	static ArrayList<Element> v = new ArrayList<Element>();
	//usare invece un'array list
	
	
	//Costruttore di AppService
	@Autowired
	public AppService() {
		Utils.jsonDecode("http://data.europa.eu/euodp/data/api/3/action/package_show?id=yGVKnIzbkC2ZHpT6jQouDg");			
		Utils.csvParse(v, "dataset.csv");
	}

	
	public ArrayList<Element> getFilteredData(int value){
		int i=0;
		ArrayList<Element> newArrayList = new ArrayList<Element>();
		for(i=0; i<v.size(); i++)
		{if (v.get(i).getTime_period()==value)
			newArrayList.add(v.get(i));
		}		
		return newArrayList;
	}
	


	public Element printElement(int i) {
		return v.get(i);
	}

	public ArrayList<Element> printElement() {
		return v;
	}
}
