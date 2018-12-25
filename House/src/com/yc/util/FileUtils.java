package com.yc.util;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
/**
 * 文件工具�?
 * @author Administrator
 *
 */
public class FileUtils {
	/**
	 * 搜索指定文件夹下的文件名数组
	 * @param dir		搜索的文件夹		d:/java
	 * @param suffixs	搜索的文件后�? 数组 {"exe","txt"}
	 * @return
	 */
	public static List<String> find(String dir,String[] suffixs){
		File f = new File(dir);
		String[] files = f.list(new FilenameFilter() {
			/**
			 * 判断文件是否返回的方�?
			 */
			@Override
			public boolean accept(File dir,String name) {
				//判断当前传入的文件名是否是exe结尾
				for(String suffix : suffixs) {
					if(name.endsWith(suffix)) {
						return true;
					}
				}
				return false;
			}
		});
		/**
		 * 数组转list集合
		 *
		ArrayList<String> list = new ArrayList<String>();
		for(String file : files) {
			list.add(file);
		}
		return list;*/
		
		//使用数据工具类Arrays 将数组转换成集合
		return Arrays.asList(files);
	}
	public static Date change(String str) {
		Date date=Date.valueOf(str);
		return date;
		
	}

}
	
	
	
	