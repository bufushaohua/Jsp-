package com.jxlg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AUpdateServlet
 */
@WebServlet("/AUpdateServlet")
public class AUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String ids = request.getParameter("id");
		int id = Integer.parseInt(ids);
		String name = request.getParameter("name");
		String prices = request.getParameter("price");
		float price =Float.parseFloat(prices);
		String bookCounts = request.getParameter("bookCount");
		int bookCount = Integer.parseInt(bookCounts);
		String author = request.getParameter("author");
		try {
			Class.forName("com.mysql.jdbc.Driver"); // �������ݿ�������ע�ᵽ����������
			String url = "jdbc:mysql://localhost:3306/db_database11";// ���ݿ������ַ���
			String username = "root"; // ���ݿ��û���
			String password = "root"; // ���ݿ�����
			// ����Connection����
			Connection conn = DriverManager.getConnection(url, username,
					password);
			String sql = "update tb_book set name=?,price=?, bookCount=?, author=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);// ��ȡPreparedStatement
			ps.setString(1, name); // ��SQL����еĵ�һ��������ֵ
			ps.setFloat(2, price); // ��SQL����еĵڶ���������ֵ
			ps.setInt(3, bookCount); // ��SQL����еĵ�����������ֵ
			ps.setString(4, author); // ��SQL����еĵ��ĸ�������ֵ
			ps.setInt(5, id); // ��SQL����еĵ����������ֵ
			 int executeUpdate = ps.executeUpdate();
			 ps.close(); // �ر�PreparedStatement
			conn.close(); // �ر�Connection 
			
			if(executeUpdate==1){
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script language='JavaScript'>alert('�޸ĳɹ�');window.location.href='BookServlet';</script>");
			}else{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script language='JavaScript'>alert('�޸�ʧ��');window.location.href='UpdateServlet?id="+id+"';</script>");
			}
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
