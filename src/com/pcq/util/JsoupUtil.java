package com.pcq.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupUtil {

	public static void saxCodeToExcel(String htmlCode) {
		Document doc = Jsoup.parse(htmlCode);
		System.out.println(doc.toString());
	}
}
