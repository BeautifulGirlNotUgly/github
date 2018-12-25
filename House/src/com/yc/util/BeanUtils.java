package com.yc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class BeanUtils {

	/**
	 * 泛型方法 类型转换方法
	 * 
	 * 将字符串 转换8中基本数据类�?
	 * 
	 * 将字符串表示的日期�?�，转换成日期对�?  2018-01-01 => java.util.Date
	 * 
	 * 
	 * @param svalue	字符�?
	 * @param cls		转换的目标类�?
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T cast(String svalue,Class<T> cls) {
		//获取类名 getSimpleName 获取�?短名�?      getName 获取类全名（类的完全限定名）
		//String clsName = cls.getSimpleName();
		
		String clsName = cls.getName();
		//根据类名转换对应的类�?
		switch(clsName) {
		case "java.lang.Long":
			return (T) Long.valueOf(svalue);
		case "java.lang.Int":
			return (T) Integer.valueOf(svalue);
		case "java.lang.String":
			return (T) String.valueOf(svalue);
		case "java.lang.Byte":
			return (T) Byte.valueOf(svalue);
		case "java.lang.Short":
			return (T) Short.valueOf(svalue);
		case "java.lang.Boolean":
			return (T) Boolean.valueOf(svalue);
		case "java.lang.Double":
			return (T) Double.valueOf(svalue);
		case "java.lang.Float":
			return (T) Float.valueOf(svalue);
		case "java.lang.Character":
			char[] chars = svalue.toCharArray();
			Character c = chars[0];
			//装箱
			return (T)c;
		case "java.util.Date":
			//请完成日期的转换
			//日期格式化类，创建时要指定格式（掩码�?
			try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(svalue);
			return (T)date;
			}catch(ParseException e){
				e.printStackTrace();
			}
			return null;
			
		case "java.sql.Date":
			
			return (T) java.sql.Date.valueOf(svalue);
		}
		return null;
	}
	/**
	 * 将List<Map<String,Object>>转成Vector<Vector<Object>>
	 * 提供给Swing表格控件使用
	 * @param list
	 * @return
	 */
	public static Vector<Vector<Object>> toVector(List<Map<String,Object>> list){
		Vector<Vector<Object>> ret = new Vector<Vector<Object>>();
		
		for(Map<String,Object> map: list) {
			Vector<Object> v = new Vector<Object>();
			//遍历map中元�?
			for(Map.Entry<String, Object> e : map.entrySet()) {
				//将Map中字段�?�添加到Vector中去
				Object value = e.getValue();
				v.add(value);
			}
			//将行Vector存入结果集Vector
			ret.add(v);
		}
		return ret;
	}
	

 }
