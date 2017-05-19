package com.pcq.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownLoadUtil {

	public static void main(String[] args) {
		String content = downloadHtmlCode("http://www.sina.com.cn/");
		JsoupUtil.saxCodeToExcel(content);
	}
	
	/**
	 * 该方法通过给定的网址将该网址的源码以字符串的形式返回
	 * @param url 网页网址
	 * @return 网页源码的字符串形式
	 */
	public static String downloadHtmlCode(String url) {
		URLConnection connection = null;
		URL urlObj = null;
		BufferedReader reader = null;
		try {
			urlObj = new URL(url);
			connection = urlObj.openConnection();
			connection.setRequestProperty("User-Agent", "Java");//跳过防火墙
			String contentType = connection.getContentType();//获得头部contentType的值
			String encoding = "UTF-8";//默认设置成utf-8
			if(contentType.indexOf("=") != -1) {//说明头部添加了字符编码
				encoding = contentType.substring(contentType.indexOf("=") + 1);//获得源码设置的编码
			}
			reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), encoding)
					);
			StringBuffer sb = new StringBuffer();
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
