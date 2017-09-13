package com.atguigu.manager.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test01 {

	public static void main(String[] args) throws IOException {
		Properties properties = new Properties();

		InputStream resourceAsStream = Test01.class.getClassLoader().getResourceAsStream("dbConfig.properties");

		properties.load(resourceAsStream);

		String property = properties.getProperty("imgPath");

		System.out.println(property);

	}

}
