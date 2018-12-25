package com.yc.biz;

import java.util.List;
import java.util.Map;

public class Statistics {

	
	public List<Map<String, Object>> find() {
		String sql = "select bname,isbn,count(*) "
				+ "from book,borrow order by count(bookid) asc";
		return null;
	}
}
