package com.yc.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.yc.bean.Renter;
import com.yc.util.DBUtils;

/**
 * 用户管理业务类
 * @author Administrator
 */
public class Userbiz {

	public String login(String rname, String rpwd) {

		String sql = "select * from renter where rname = ? and rpwd = ?";

		try {
			Map<String, Object> map = DBUtils.selectOne(sql, rname, rpwd);
			if (map == null) {
				// 通知用户
				return "用户名或密码错误";
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "系统繁忙，请稍后重试";
		}
	}

	public String register(Renter renter, String repwd) {

		if (renter.getRname().length() < 2 || renter.getRname().length() > 20) {
			return "请输入正确的用户名";
		}

		if (renter.getRpwd().length() < 6 || renter.getRpwd().length() > 12) {
			return "密码长度不能小于6或大于12";
		}

		if (renter.getRpwd().equals(repwd)) {

		} else {
			return "两次输入的密码不一致，请重新输入";
		}

		String sql = "insert into renter values (BOOK_SEQ.nextval,?,?)";
		try {
			DBUtils.update(sql, renter.getRname(), renter.getRpwd());
			return null;

		} catch (SQLException e) {

			return "系统繁忙，请稍后重试";
		}
	}

	/**
	 * 1、如果没有条件，则查所有的记录 
	 * 2、如果输入用户名或电话，则根据用户名或电话查询
	 * 3、如果同时输入用户名和电话，则两个条件同时应用
	 * 
	 * @param uname
	 * @param phone
	 * @param address
	 * @param email
	 * @return
	 */
	public List<Map<String, Object>> find(String hname, String area, String monthMoney, String rcash) {
		String sql = "select * from houseres where 1=1";

		ArrayList<Object> paramList = new ArrayList<Object>();

		if (hname != null && hname.trim().isEmpty() == false) {
			sql += "and uname like ?";
			paramList.add("%" + hname + "%");
		}

		if (area != null && area.trim().isEmpty() == false) {
			sql += "and phone like ?";
			paramList.add("%" + area + "%");
		}

		if (monthMoney != null && monthMoney.trim().isEmpty() == false) {
			sql += "and address like ?";
			paramList.add("%" + monthMoney + "%");
		}

		if (rcash != null && rcash.trim().isEmpty() == false) {
			sql += "and email like ?";
			paramList.add("%" + rcash + "%");
		}
		sql += "order by userid asc";
		try {
			// paramList.toArray()将集合转成数组
			return DBUtils.query(sql, paramList.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void remove(long userid) throws SQLException {
		/**
		 * 删除的验证 如果有外键关联用户ID 则删除会失败，会抛出SQL异常 如果有借阅记录 应该要阻止删除 在删除前要查询借阅记录 如果有则阻止删除，如何阻止删除
		 */

		String sql = "delete from users where userid = ?";
		DBUtils.update(sql, userid);
	}

	public void modify(Vector<Object> users) throws SQLException {
		String sql = "update users set uname = ?,address = ?,phone = ?,idcard = ?,email = ? where userid = ?";
		DBUtils.update(sql, users.get(1), users.get(2), users.get(3), users.get(4), users.get(5), users.get(0));
	}

}
