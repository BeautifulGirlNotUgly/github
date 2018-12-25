package com.yc.biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.yc.bean.Renter;
import com.yc.util.DBUtils;

/**
 * �û�����ҵ����
 * @author Administrator
 */
public class Userbiz {

	public String login(String rname, String rpwd) {

		String sql = "select * from renter where rname = ? and rpwd = ?";

		try {
			Map<String, Object> map = DBUtils.selectOne(sql, rname, rpwd);
			if (map == null) {
				// ֪ͨ�û�
				return "�û������������";
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ϵͳ��æ�����Ժ�����";
		}
	}

	public String register(Renter renter, String repwd) {

		if (renter.getRname().length() < 2 || renter.getRname().length() > 20) {
			return "��������ȷ���û���";
		}

		if (renter.getRpwd().length() < 6 || renter.getRpwd().length() > 12) {
			return "���볤�Ȳ���С��6�����12";
		}

		if (renter.getRpwd().equals(repwd)) {

		} else {
			return "������������벻һ�£�����������";
		}

		String sql = "insert into renter values (BOOK_SEQ.nextval,?,?)";
		try {
			DBUtils.update(sql, renter.getRname(), renter.getRpwd());
			return null;

		} catch (SQLException e) {

			return "ϵͳ��æ�����Ժ�����";
		}
	}

	/**
	 * 1�����û��������������еļ�¼ 
	 * 2����������û�����绰��������û�����绰��ѯ
	 * 3�����ͬʱ�����û����͵绰������������ͬʱӦ��
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
			// paramList.toArray()������ת������
			return DBUtils.query(sql, paramList.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void remove(long userid) throws SQLException {
		/**
		 * ɾ������֤ �������������û�ID ��ɾ����ʧ�ܣ����׳�SQL�쳣 ����н��ļ�¼ Ӧ��Ҫ��ֹɾ�� ��ɾ��ǰҪ��ѯ���ļ�¼ ���������ֹɾ���������ֹɾ��
		 */

		String sql = "delete from users where userid = ?";
		DBUtils.update(sql, userid);
	}

	public void modify(Vector<Object> users) throws SQLException {
		String sql = "update users set uname = ?,address = ?,phone = ?,idcard = ?,email = ? where userid = ?";
		DBUtils.update(sql, users.get(1), users.get(2), users.get(3), users.get(4), users.get(5), users.get(0));
	}

}
