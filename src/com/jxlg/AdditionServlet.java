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
 * Servlet implementation class AdditionServlet
 */
@WebServlet("/AdditionServlet")
public class AdditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdditionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		int id = Integer.valueOf(request.getParameter("id"));
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
			String isExist = "select * from tb_book where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(isExist) ;
			pstmt.setInt(1, id);
	        ResultSet rs = pstmt.executeQuery();
			if(!rs.next()){
				String sql = "insert into tb_book(id,name,price,bookCount,author) values(?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);// ��ȡPreparedStatement
				ps.setInt(1, id);
				ps.setString(2, name); // ��SQL����еĵ�һ��������ֵ
				ps.setFloat(3, price); // ��SQL����еĵڶ���������ֵ
				ps.setInt(4, bookCount); // ��SQL����еĵ�����������ֵ
				ps.setString(5, author); // ��SQL����еĵ��ĸ�������ֵ
				 int executeUpdate = ps.executeUpdate();
				 ps.close(); // �ر�PreparedStatement
				conn.close(); // �ر�Connection 
				//�жϲ����Ƿ�ִ�гɹ�
				if(executeUpdate==1){
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script language='JavaScript'>alert('�޸ĳɹ�');window.location.href='BookServlet';</script>");
				}else{
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script language='JavaScript'>alert('�޸�ʧ��');window.location.href='index.jsp';</script>");
				}
			}else{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script language='JavaScript'>alert('�������ݱ���Ѵ���Ŷ����������д��ţ�');window.location.href='addition.jsp';</script>");
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
