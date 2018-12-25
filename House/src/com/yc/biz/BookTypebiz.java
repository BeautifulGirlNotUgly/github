package com.yc.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.util.DBUtils;

public class BookTypebiz {

	public List<Map<String, Object>> find(String name, String info) {
		String sql = "select * from booktype where 1=1";

		ArrayList<Object> paramList = new ArrayList<Object>();

		if (name != null && name.trim().isEmpty() == false) {
			sql += "and name like ?";
			paramList.add("%" + name + "%");
		}

		if (info != null && info.trim().isEmpty() == false) {
			sql += "and info like ?";
			paramList.add("%" + info + "%");
		}
		sql += "order by id asc";
		try {
			return DBUtils.query(sql, paramList.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void remove(Long id) throws SQLException{
		String sql1 = "select * from book where typid = ?";
		
		long count = DBUtils.count(sql1, id);
		if(count > 0) {
			throw new SQLException("该分类下面已经添加了图书");
		}
		String sql2 = "delete booktype where id = ?";
		DBUtils.update(sql2, id);
	}
	
	public String add(String name,String info,String pid) {
		String sql = "insert into booktype values(BOOK_SEQ.nextval,?,?,?)";
		
		if(pid != null && pid.trim().isEmpty()) {
			pid = null;
		}
		try {
			DBUtils.update(sql, name,info,pid);
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "系统繁忙，请稍后重试";
		}
		
	}

}
