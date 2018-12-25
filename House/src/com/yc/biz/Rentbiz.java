package com.yc.biz;

import java.sql.SQLException;
import java.util.Vector;

import com.yc.bean.Renting;
import com.yc.util.DBUtils;

public class Rentbiz {

	public String renthouse(Renting renting) {

		String sql = "insert into Renting values(BOOK_SEQ.nextval,sysdate,?,?,?,?,?,?)";

		try {
			DBUtils.update(sql, null,renting.getHid(),renting.getHname(),renting.getRid(),renting.getRname(),renting.getRarea(),renting.getRrent());
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return "系统繁忙，请稍后重试";
		}
	}

	public String returnbook(Vector<Object> borrow) {
		String sql = "update borrow set marker = ?,backtime = sysdate where userid = ? and bname = ?";

		try {
			DBUtils.update(sql, borrow.get(4), borrow.get(3), borrow.get(1));
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return "系统繁忙，请稍后重试";
		}
	}

}
