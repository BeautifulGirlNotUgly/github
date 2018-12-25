package com.yc.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.yc.bean.Book;
import com.yc.util.DBUtils;

public class Bookbiz {

	public String AddBook(Book book) {
		String sql = "insert into book values (BOOK_SEQ.nextval,?,?,?,?,?,?)";
		try {
			DBUtils.update(sql, book.getBname(), book.getIsbn(), book.getAuthor(), book.getPubdate(), book.getIndate(),
					book.getStatus());
			return null;
		} catch (SQLException e) {

			return "系统繁忙，请稍后重试";
		}
	}

	public List<Map<String, Object>> find(String bname, String author) {
		String sql = "select * from book where 1=1";

		ArrayList<Object> paramList = new ArrayList<Object>();

		if (bname != null && bname.trim().isEmpty() == false) {
			sql += "and bname like ?";
			paramList.add("%" + bname + "%");
		}

		if (author != null && author.trim().isEmpty() == false) {
			sql += "and author like ?";
			paramList.add("%" + author + "%");
		}
		sql += "order by bookid asc";
		try {
			return DBUtils.query(sql, paramList.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void remove(long bookid) throws SQLException {

		String sql = "delete from book where bookid = ?";
		DBUtils.update(sql, bookid);
	}

	public void modify(Vector<Object> book) throws SQLException {

		String sql = "update book set bname = ?,isbn = ?,author = ?," + "status = ? where bookid = ?";
		DBUtils.update(sql, book.get(1), book.get(2), book.get(3), book.get(4), book.get(0));

	}

}
