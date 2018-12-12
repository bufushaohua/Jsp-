package com.jxlg;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

public class BookDao {
	public Connection getConnection(){
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/db_database11";
			String username = "root"; // ���ݿ��û���
			String password = "root"; // ���ݿ�����
			conn = DriverManager.getConnection(url, username, password);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<BookBean> find(int page){
		List<BookBean> list = new ArrayList<>();
		Connection conn = getConnection();
		String sql = "select * from tb_book order by id desc limit ?,?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*BookBean.PAGE_SIZE);
			ps.setInt(2, BookBean.PAGE_SIZE);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				BookBean b = new BookBean();
				b.setId(rs.getInt("id"));				// ��id���Ը�ֵ
				b.setName(rs.getString("name"));		// ��name���Ը�ֵ
				b.setPrice(rs.getDouble("price"));		// ��price���Ը�ֵ
				b.setBookCount(rs.getInt("bookCount"));	// ��bookCount���Ը�ֵ
				b.setAuthor(rs.getString("author"));		// ��author���Ը�ֵ
				list.add(b); 							// ��ͼ�������ӵ�������
			}
			rs.close();
			ps.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int findCount(){
		int Count=0;
		Connection conn = getConnection();
		String sql = "select count(*) from tb_book";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				Count = rs.getInt(1);
			}
			rs.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return Count;
	}
	
	
}
