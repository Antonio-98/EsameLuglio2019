package com.app.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import com.app.model.Element;

public class Utils {

	public static void jsonDecode(String url) {
		// String url =
		// "http://data.europa.eu/euodp/data/api/3/action/package_show?id=yGVKnIzbkC2ZHpT6jQouDg";
		boolean a = true;

		try {

			URLConnection openConnection = new URL(url).openConnection();
			openConnection.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = openConnection.getInputStream();

			String data = "";
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) {
					data += line;
					// System.out.println( line );
				}
			} finally {
				in.close();
			}
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data);
			JSONObject objI = (JSONObject) (obj.get("result"));
			JSONArray objA = (JSONArray) (objI.get("resources"));

			for (Object o : objA) {
				if (o instanceof JSONObject) {
					JSONObject o1 = (JSONObject) o;
					String format = (String) o1.get("format");
					String urlD = (String) o1.get("url");
					// System.out.println(format + " | " + urlD);
					if (format.equals("http://publications.europa.eu/resource/authority/file-type/CSV") && a) {
						download(urlD, "dataset.csv");
						a = false;
					}
				}
			}

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void download(String url, String fileName) throws Exception {
		try (InputStream in = URI.create(url).toURL().openStream()) {
			Files.copy(in, Paths.get(fileName));
		}
	}

	public static void csvParse(ArrayList<Element> v, String filename) {
		boolean i = false;
		// List<List<String>> records = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			String line;

			while ((line = br.readLine()) != null) {
				if (i) {
					String[] values = line.split(",");
					// System.out.println(values.length);

					// records.add(Arrays.asList(values));

					v.add(new Element(Integer.parseInt(values[0].substring(1, values[0].length() - 1)),
							values[1].substring(1, values[1].length() - 1),
							values[2].substring(1, values[2].length() - 1),
							values[3].substring(1, values[3].length() - 1),
							values[4].substring(1, values[4].length() - 1),
							Float.parseFloat(values[5].substring(1, values[5].length() - 1))));
				}
				i = true;
			}

			System.out.println(v.get(0).toString());
			br.close();

		} catch (IOException j) {
			j.printStackTrace();
			return;
		}
	}
}
