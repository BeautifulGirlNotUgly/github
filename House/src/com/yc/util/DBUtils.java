package com.yc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 对数据库操作工具�?
 * 
 * @author Administrator
 */
public class DBUtils {
	/**
	 * 类的组成�?1、属�? 2、方�? 3、内部类 4、块：动态块，静态块
	 */
	{
		// 动�?�块
	}

	static {
		/**
		 * 这是�?个静态块，在类加载jvm的时候被执行�?�? 加载驱动 驱动程序类路径，类的完全限定�? ：包�?+类名
		 */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// 异常转型，转为运行期异常抛出，终止程序的运行
			throw new RuntimeException(e);
		}
	}

	/**
	 * 用于执行 增删改sql的方�?
	 * 
	 * @param sql
	 *            表示要执行的sql语句 例如 insert into dept values(???)
	 * @param paramList
	 *            可变参数数组：表示的是执行该sql要用到的参数
	 * @return
	 * @throws SQLException
	 */
	public static int update(String sql, Object... paramArray) throws SQLException {
		// 获取连接
		Connection conn = getConnection();
		// 构建语句
		PreparedStatement stat = conn.prepareStatement(sql);
		System.out.println("SQL:" + sql);
		for (int i = 0; i < paramArray.length; i++) {
			// jdbc的参数索引从1�?�?
			stat.setObject(i + 1, paramArray[i]);
			System.out.println("参数" + (i + 1) + ":" + paramArray[i]);
		}
		int count = stat.executeUpdate();
		System.out.println("成功更新�?" + count + "条记�?");
		conn.close();
		return count;
	}

	public static List<Map<String, Object>> query(String sql, Object... paramArray) throws SQLException {
		// 获取连接
		Connection conn = getConnection();

		PreparedStatement stat = conn.prepareStatement(sql);

		System.out.println("SQL:" + sql);
		for (int i = 0; i < paramArray.length; i++) {
			// jdbc的参数索引从1�?�?
			stat.setObject(i + 1, paramArray[i]);
			System.out.println("参数" + (i + 1) + ":" + paramArray[i]);
		}
		System.out.println(sql);
		/**
		 * 以下是查询方法的代码
		 */
		// 结果集对�?
		ResultSet rs = stat.executeQuery();
		/**
		 * 通过访问结果集元数据获取列名
		 */
		ResultSetMetaData md = rs.getMetaData();
		// 获取总列�?
		int columnCount = md.getColumnCount();
		/**
		 * 结果集转集合
		 */
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			/**
			 * 将所有的列�?�，添加到map�?
			 */
			for (int i = 1; i <= columnCount; i++) {
				// 获取列名
				String columnName = md.getColumnName(i);
				// 获取列�??
				Object columnValue = rs.getObject(columnName);
				// put 到map �?
				map.put(columnName, columnValue);
			}
			/**
			 * 将结果集中的字段值添加到map�?
			 */
			list.add(map);
		}
		conn.close();
		return list;
	}

	/**
	 * 将获取连接的代码 封装成一个方�?
	 * 
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@//127.0.0.1:1521/orcl";
		String user = "scott";
		String password = "a";
		Connection conn = DriverManager.getConnection(url, user, password);

		return conn;
	}

	/**
	 * 返回指定sql 的结果数�?
	 * 
	 * @param sql
	 *            select * from emp where ename like?
	 * @param paramArray
	 * @return
	 * @throws SQLException
	 */
	public static long count(String sql, Object... paramArray) throws SQLException {
		sql = "select count(*) cnt from ( " + sql + " ) ";
		// 可能的返回类�? Integer Long BigDecimal(大实�?)
		List<Map<String, Object>> list = query(sql, paramArray);
		// 将数�?(int long bigdecimal)转成字符�?
		String snumber = "" + list.get(0).get("cnt");
		return Long.valueOf(snumber);
	}

	/**
	 * 使用分页查询方式，查询结�?
	 * 
	 * @param sql
	 *            select * from emp
	 * @param page
	 *            查询的页数，也就是要查的第几页，页数�?1�?始，即第�?页，page = 1
	 * @param rows
	 *            查询的行数，也就是查出的页面的行数，如：每页20行数�?
	 * @param paramArray
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> queryByPage(String sql, int page, int rows, Object... paramArray)
			throws SQLException {
		int begin = (page - 1) * rows + 1;
		int end = begin + rows - 1;
		String pageSql = "select * from (select rownum rw,a.* from(" + sql + ") a " + "where rownum <= " + end
				+ ") where rw >= " + begin;
		return query(pageSql, paramArray);
	}

	/**
	 * 根据指定的sql 查询�?条记�? 如：根据主键�?
	 * 
	 * @param sql
	 * @param paramArray
	 * @return 如果查到�?条记录，则返回转换后的map对象，如果没查到，则返回null，如果查出多条记录，则抛出一个运行时异常
	 * @throws SQLException
	 */
	public static Map<String, Object> selectOne(String sql, Object... paramArray) throws SQLException {
		sql = "select rownum,a.* from (" + sql + ") a where rownum <= 2";
		List<Map<String, Object>> list = query(sql, paramArray);
		if (list.size() == 0) {
			return null;
		} else if (list.size() > 1) {
			throw new RuntimeException("查询的结果数量大�?1");
		} else {
			return list.get(0);
		}

	}

	public static void main(String[] args) throws SQLException {
		List<Map<String, Object>> list = query("select * from emp where ename like ?", "%S%");
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

}