package com.yc.db;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties extends Properties{
	private static MyProperties myproperties;
	private MyProperties() {
		InputStream is=MyProperties.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			this.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static MyProperties getInstance() {
		if(myproperties==null) {
			myproperties=new MyProperties();
		}
		return myproperties;
	}
}
