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

//��ʼ��װ
public class DBHelper {
	//��̬�飬����������ֻ��Ҫִ��һ�Σ����ң�����ǰ��ִ��
	static{
		try {
			Class.forName(MyProperties.getInstance().getProperty("driverClass"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//��û�з���ֵ ������У���ôֵ��ʲô���͵�
	//��û�б�Ҫ������������У���ô��������ʲô���͵�
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
	
	//�ر�����
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
	
	
	//������ɾ�ķ���ֵ����һ���ģ�����һ�������һ����������ǽ���������װ��һ��ȥ
	//					sql���Ҫ         
	//  ���ȣ����Ȳ��ǹ̶��ģ���������ѧ��ֻ�м���          String int booleanxxxx   Object
	public int doUpdate(String sql,List<Object> params){
		int result=-1;
		
		try {
			//��ȡ����
			Connection con=getCon();
			//Ԥ����
			PreparedStatement ps=con.prepareStatement(sql);
			//�������������ģ�1������û�в���     2���У������������̶�    �������Ͳ��̶�  ���͵��·�����ͬ
			doParams(ps,params);
			//��ʼ����
			result=ps.executeUpdate();
			con.setAutoCommit(true);
			closeAll(con,ps,null,null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//���ò������Ȳ���
	private void doParams(PreparedStatement ps, List<Object> params) {
		//�������Ϊnull����ѭ��
		try {
			if( params!=null && params.size()>0 ){
				//ѭ��
				for(int i=0;i<params.size();i++){
					//��ʼ����    �Ƚ�����
					Object o=params.get(i);
					//�ж�o �Ƿ���String���͵�һ��ʵ��
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
					//   boolean   blob����������  һ�����ڴ��ͼƬ����Ƶ�ȵ�
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ResultSet    ��ǿ���ԣ����϶���������
	//�Ż���1��List�������Կ���ֱ��ͨ��size���õ�����
	//    2��Map��ֵ�ԣ�һһ��Ӧ
	public List<Map<String,String>> findAll(String sql,List<Object> params){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		try {
			//��ȡ����
			Connection con=getCon();
			//Ԥ����
			PreparedStatement ps=con.prepareStatement(sql);
			//�������������ģ�1������û�в���     2���У������������̶�    �������Ͳ��̶�  ���͵��·�����ͬ
			doParams(ps,params);
			//��ʼ����
			ResultSet rs=ps.executeQuery();
			//���ڵ���ؼ������⣬������ν�resultSet�����ֵ��ת��ΪList<Map<String,String>>
			
			//��һ�������ݿ�������У���Ӧ����Map����ļ�
			ResultSetMetaData rsmd=rs.getMetaData();		//��ȡ��������ݿ���ص�Ԫ����
			String[] columnName=new String[ rsmd.getColumnCount()  ];
			for(int i=0;i<columnName.length;i++){
				columnName[i]=rsmd.getColumnName(i+1); 
			}
			//���ڣ����Ѿ����ˣ���һ�����õ�ֵ
			while(rs.next()){
				Map<String,String> map=new HashMap<String,String>();
				//����������ȡֵ
				for(int i=0;i<columnName.length;i++){
					String key=columnName[i];
					//ע�⣬�ر�ע�⣬������ǽ����е����͵�ֵ����ת����Ϊ��String
					String value=rs.getString(key);
					map.put(key, value);
				}
				//��Map��ӵ�List��
				list.add(map);
			}
			closeAll(con,ps,null,null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//���ڶ���Ĳ�ѯ
	//���ܲ���ȷ������ʱ�򴫽���������һ������
	//sorry ��ȷ�������ǣ�����������һ�������Ҷ�֪��������һ�����󣬾��ǲ�֪�����������
	// T ����               �涨�ˣ�����������һ���������ھ���Ķ���OK���ִ��������Ǹ��˾���
	//�������ͣ�   Ҳ���ɷ��;������㴫����ʲô���ҷ���ʲô          ���صĶ���ֹһ���������Ƕ���������ü���
	//    ��������
	public <T> List<T> findAll(String sql,List<Object> params,Class<T> c){
		//�Ͱ����T������һ������
		List<T> list=new ArrayList<T>();
		try {
			//��ȡ����
			Connection con=getCon();
			//Ԥ����
			PreparedStatement ps=con.prepareStatement(sql);
			//�������������ģ�1������û�в���     2���У������������̶�    �������Ͳ��̶�  ���͵��·�����ͬ
			doParams(ps,params);
			//��ʼ����
			ResultSet rs=ps.executeQuery();
			
			//��һ�������ݿ�������У���Ӧ����Map����ļ�
			ResultSetMetaData rsmd=rs.getMetaData();		//��ȡ��������ݿ���ص�Ԫ����
			String[] columnName=new String[ rsmd.getColumnCount()  ];
			for(int i=0;i<columnName.length;i++){
				columnName[i]=rsmd.getColumnName(i+1); 
				//System.out.println(columnName[i]);
			}
			
			//System.out.println("--------------");
			
			//1�������ȵõ����������������еķ���
			Method[] ms=c.getMethods();
			//2���õ������������Ȼ��һ��һ��ȥƥ��  
			//����MONEY   ������Ҫ�ķ����ǣ�   setMoney();
			//  "set"+M+oney ->   setMoney
			T t;		//���������    ReaderInfo ri;
			String mname="";		//������
			String cname="";		//����
			String ctypeName="";	//������
			while( rs.next() ){
				t=c.newInstance();		//ri=new ReaderInfo();
				//ѭ����������ȥһ��һ���Ĳ���
				for(int i=0;i<columnName.length;i++){
					//�õ�������
					cname=columnName[i];
					//ȥ�ȽϷ�����   money     ->   setMoney()
					//         rid    ->   setRid()
					cname="set"+cname.substring(0,1).toUpperCase()+cname.substring(1).toLowerCase();
					for(Method m : ms){
						//�õ�������
						mname=m.getName();
						//�ҵ������������ǣ�ͬʱ��ֵ����Ϊ��
						if( cname.equals(mname)  ){
							//int   getInt      double   getDouble    String   getString
							//һ��Ҫ�ж�����
							
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
								//��Ϊ��oracle��������blob�������޷�ֱ��ת��Ϊ�ֽ�����
								Blob blob=rs.getBlob(columnName[i]);
								is=new BufferedInputStream(blob.getBinaryStream());
								bytes=new byte[(int)blob.length()];//����ĳ���
								//��io����������е�����ȫ����ȡ��bytes����ȥ
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
			
			//�ַ��� -�ַ�����-�ֽ�����
	        char[] charArray = inStr.toCharArray();  
	        byte[] byteArray = new byte[charArray.length];  
	  
	        for (int i = 0; i < charArray.length; i++)  
	            byteArray[i] = (byte) charArray[i];  
	        byte[] md5Bytes = md5.digest(byteArray);  
	        StringBuffer hexValue = new StringBuffer();  
	        for (int i = 0; i < md5Bytes.length; i++){  
	            int val = ((int) md5Bytes[i]) & 0xff;  
				//16����  &�жϷ�
					//dos ����һ��intռ�����ֽ�
	            if (val < 16)  
	                hexValue.append("0");  
	            hexValue.append(Integer.toHexString(val));  
	        }  
	        return hexValue.toString();  	  
	    }  	  
	    /** 
	     * ���ܽ����㷨 ִ��һ�μ��ܣ����ν��� 
	     */   //������ļ���  
	    public static String convertMD5(String inStr){  
	  
	        char[] a = inStr.toCharArray();  
	        for (int i = 0; i < a.length; i++){  
	            a[i] = (char) (a[i] ^ 't');  
				//�������  ����������� 
	        }  
	        String s = new String(a);  
	        return s;  
	    }    
	   
}

				 				
								
