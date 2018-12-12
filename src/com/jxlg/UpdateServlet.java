package com.jxlg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Class.forName("com.mysql.jdbc.Driver"); // �������ݿ�������ע�ᵽ����������
			String url = "jdbc:mysql://localhost:3306/db_database11";// ���ݿ������ַ���
			String username = "root"; // ���ݿ��û���
			String password = "root"; // ���ݿ�����
			// ����Connection����
			Connection conn = DriverManager.getConnection(url, username,
					password);
			String sql = "select * from tb_book where id=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);// ��ȡPreparedStatement
			ps.setInt(1, id); // ��SQL����еĵ�һ��������ֵ
			//Statement stmt = conn.createStatement();
			ResultSet rs = ps.executeQuery();			// ִ�в�ѯ	
			//List<BookBean> list = new ArrayList<>();		// ʵ����List����	
			BookBean book = new BookBean();	
			while(rs.next()){								// �������ƶ������ж��Ƿ���Ч
								// ʵ����Book����
				book.setId(rs.getInt("id"));				// ��id���Ը�ֵ
				book.setName(rs.getString("name"));		// ��name���Ը�ֵ
				book.setPrice(rs.getDouble("price"));		// ��price���Ը�ֵ
				book.setBookCount(rs.getInt("bookCount"));	// ��bookCount���Ը�ֵ
				book.setAuthor(rs.getString("author"));		// ��author���Ը�ֵ
				//list.add(book); 							// ��ͼ�������ӵ�������
			}
			request.setAttribute("book", book);		
			ps.close(); // �ر�PreparedStatement
			conn.close(); // �ر�Connection
			request.getRequestDispatcher("update.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//response.setContentType("text/html;charset=utf-8");
		//PrintWriter out = response.getWriter();
		//out.print("<script language='JavaScript'>alert('�޸ĳɹ�');window.location.href='index.jsp';</script>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
