package com.yc.db;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//开始封装
public class DBHelper {
	//静态块，加载驱动，只需要执行一次，而且，在最前面执行
	static{
		try {
			Class.forName(MyProperties.getInstance().getProperty("driverClass"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//有没有返回值 ，如果有，那么值是什么类型的
	//有没有必要传参数，如果有，那么，参数是什么类型的
	public Connection getCon(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(MyProperties.getInstance().getProperty("url"),
					MyProperties.getInstance());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭连接
	public void closeAll(Connection con,PreparedStatement ps,Statement st,ResultSet rs){
		try {
			if(con!=null){
				con.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(st!=null){
				st.close();
			}
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//由于增删改返回值都是一样的，步骤一样，结果一样，因此我们将这三个封装到一起去
	//					sql语句要         
	//  长度，长度不是固定的，据我们所学，只有集合          String int booleanxxxx   Object
	public int doUpdate(String sql,List<Object> params){
		int result=-1;
		
		try {
			//获取连接
			Connection con=getCon();
			//预处理
			PreparedStatement ps=con.prepareStatement(sql);
			//参数是问题最大的，1、可能没有参数     2、有，但是数量不固定    但是类型不固定  类型导致方法不同
			doParams(ps,params);
			//开始运行
			result=ps.executeUpdate();
			con.setAutoCommit(true);
			closeAll(con,ps,null,null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//设置参数，先不管
	private void doParams(PreparedStatement ps, List<Object> params) {
		//如果参数为null，不循环
		try {
			if( params!=null && params.size()>0 ){
				//循环
				for(int i=0;i<params.size();i++){
					//开始设置    比较类型
					Object o=params.get(i);
					//判断o 是否是String类型的一个实例
					if(  o instanceof String  ){
						ps.setString(i+1, o.toString());
					}else if(   o instanceof Integer ){
						ps.setInt(i+1, Integer.parseInt( o.toString() ));
					}else if(  o instanceof Double ){
						ps.setDouble(i+1, Double.parseDouble( o.toString() ));
					}else if(o instanceof Boolean ) {
						ps.setBoolean(i+1, (Boolean)o);
					}else {
						ps.setBytes(i+1, (byte[])o);
					}
					//   boolean   blob大数据类型  一般用于存放图片，视频等等
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ResultSet    勉强可以，但肯定不是最优
	//优化：1、List有序，所以可以直接通过size来得到长度
	//    2、Map键值对，一一对应
	public List<Map<String,String>> findAll(String sql,List<Object> params){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		try {
			//获取连接
			Connection con=getCon();
			//预处理
			PreparedStatement ps=con.prepareStatement(sql);
			//参数是问题最大的，1、可能没有参数     2、有，但是数量不固定    但是类型不固定  类型导致方法不同
			doParams(ps,params);
			//开始运行
			ResultSet rs=ps.executeQuery();
			//现在的最关键的问题，就是如何将resultSet里面的值，转换为List<Map<String,String>>
			
			//第一个，数据库里面的列，对应我们Map里面的键
			ResultSetMetaData rsmd=rs.getMetaData();		//获取到这个数据库相关的元数据
			String[] columnName=new String[ rsmd.getColumnCount()  ];
			for(int i=0;i<columnName.length;i++){
				columnName[i]=rsmd.getColumnName(i+1); 
			}
			//现在，键已经有了，下一步，得到值
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				//根据列名来取值
				for(int i=0;i<columnName.length;i++){
					String key=columnName[i];
					//注意，特别注意，这里，我们将所有的类型的值，都转换成为了String
					String value=rs.getString(key);
					map.put(key, value);
				}
				//将Map添加到List中
				list.add(map);
			}
			closeAll(con,ps,null,null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//基于对象的查询
	//你能不能确定，到时候传进来的是哪一个对象？
	//sorry 不确定，但是，不管你是哪一个对象，我都知道，你是一个对象，就是不知道具体的类型
	// T 泛型               规定了，传进来的是一个对象，至于具体的对象，OK，又传进来的那个人决定
	//返回类型？   也是由泛型决定，你传进来什么，我返回什么          返回的对象不止一个，可能是多个，所以用集合
	//    声明泛型
	public <T> List<T> findAll(String sql,List<Object> params,Class<T> c){
		//就把这个T看成是一个对象
		List<T> list=new ArrayList<T>();
		try {
			//获取连接
			Connection con=getCon();
			//预处理
			PreparedStatement ps=con.prepareStatement(sql);
			//参数是问题最大的，1、可能没有参数     2、有，但是数量不固定    但是类型不固定  类型导致方法不同
			doParams(ps,params);
			//开始运行
			ResultSet rs=ps.executeQuery();
			
			//第一个，数据库里面的列，对应我们Map里面的键
			ResultSetMetaData rsmd=rs.getMetaData();		//获取到这个数据库相关的元数据
			String[] columnName=new String[ rsmd.getColumnCount()  ];
			for(int i=0;i<columnName.length;i++){
				columnName[i]=rsmd.getColumnName(i+1); 
				//System.out.println(columnName[i]);
			}
			
			//System.out.println("--------------");
			
			//1、我们先得到这个对象里面的所有的方法
			Method[] ms=c.getMethods();
			//2、得到对象的事例，然后一个一个去匹配  
			//列是MONEY   我们想要的方法是：   setMoney();
			//  "set"+M+oney ->   setMoney
			T t;		//对象的事例    ReaderInfo ri;
			String mname="";		//方法名
			String cname="";		//列明
			String ctypeName="";	//类型名
			while( rs.next() ){
				t=c.newInstance();		//ri=new ReaderInfo();
				//循环方法名，去一个一个的查找
				for(int i=0;i<columnName.length;i++){
					//得到列名字
					cname=columnName[i];
					//去比较方法名   money     ->   setMoney()
					//         rid    ->   setRid()
					cname="set"+cname.substring(0,1).toUpperCase()+cname.substring(1).toLowerCase();
					for(Method m : ms){
						//得到方法名
						mname=m.getName();
						//找到方法名，但是，同时，值不能为空
						if( cname.equals(mname)  ){
							//int   getInt      double   getDouble    String   getString
							//一定要判断类型
							
							ctypeName=rs.getObject(columnName[i]).getClass().getName();
							//System.out.println(ctypeName+"="+mname);
							if("java.lang.Integer".equals(ctypeName)){
								m.invoke(t, rs.getInt(columnName[i]));
							}else if("java.math.BigDecimal".equals(ctypeName)){
								try{
									java.math.BigDecimal b= (BigDecimal) rs.getObject(columnName[i]);
									m.invoke(t,Integer.parseInt(b.toString()));
								}catch (Exception e){
									java.math.BigDecimal b=(java.math.BigDecimal) rs.getObject(columnName[i].toUpperCase());
									double doub= Double.parseDouble(b.toString());
									m.invoke(t,doub);
								}
							}else if("java.lang.String ".equals(ctypeName)){
								m.invoke(t, rs.getString(columnName[i]));
							}else if("java.sql.Timestamp".equals(ctypeName)){
								m.invoke(t, rs.getString(columnName[i]));
							}else if("java.sql.Date".equals(ctypeName)){
								m.invoke(t, rs.getDate(columnName[i]));
							}else if("image".equals(ctypeName)){
								m.invoke(t, rs.getBlob(columnName[i]));
							}else if("oracle.sql.BLOB".equals(ctypeName)){
								BufferedInputStream is=null;
								byte[] bytes=null;
								//因为在oracle里面存的是blob，它是无法直接转换为字节数组
								Blob blob=rs.getBlob(columnName[i]);
								is=new BufferedInputStream(blob.getBinaryStream());
								bytes=new byte[(int)blob.length()];//数组的长度
								//把io流里面的所有的数据全部读取到bytes里面去
								is.read(bytes);
								m.invoke(t, bytes);
								
							}else{
								m.invoke(t, rs.getString(columnName[i]));
								
							}
						}
					}
				}
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	 public static String string2MD5(String inStr){  
	        MessageDigest md5 = null;  
	        try{  
	            md5 = MessageDigest.getInstance("MD5");  
	        }catch (Exception e){  
	            //System.out.println(e.toString());  
	            e.printStackTrace();  
	            return "";  
	        }  
			
			//字符串 -字符数组-字节数组
	        char[] charArray = inStr.toCharArray();  
	        byte[] byteArray = new byte[charArray.length];  
	  
	        for (int i = 0; i < charArray.length; i++)  
	            byteArray[i] = (byte) charArray[i];  
	        byte[] md5Bytes = md5.digest(byteArray);  
	        StringBuffer hexValue = new StringBuffer();  
	        for (int i = 0; i < md5Bytes.length; i++){  
	            int val = ((int) md5Bytes[i]) & 0xff;  
				//16进制  &判断符
					//dos 里面一个int占两个字节
	            if (val < 16)  
	                hexValue.append("0");  
	            hexValue.append(Integer.toHexString(val));  
	        }  
	        return hexValue.toString();  	  
	    }  	  
	    /** 
	     * 加密解密算法 执行一次加密，两次解密 
	     */   //可以逆的加密  
	    public static String convertMD5(String inStr){  
	  
	        char[] a = inStr.toCharArray();  
	        for (int i = 0; i < a.length; i++){  
	            a[i] = (char) (a[i] ^ 't');  
				//异或运算  两次异或运算 
	        }  
	        String s = new String(a);  
	        return s;  
	    }    
	   
}

				 				
								
