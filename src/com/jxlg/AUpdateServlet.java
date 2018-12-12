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
			Class.forName("com.mysql.jdbc.Driver"); // 加载数据库驱动，注册到驱动管理器
			String url = "jdbc:mysql://localhost:3306/db_database11";// 数据库连接字符串
			String username = "root"; // 数据库用户名
			String password = "root"; // 数据库密码
			// 创建Connection连接
			Connection conn = DriverManager.getConnection(url, username,
					password);
			String sql = "update tb_book set name=?,price=?, bookCount=?, author=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);// 获取PreparedStatement
			ps.setString(1, name); // 对SQL语句中的第一个参数赋值
			ps.setFloat(2, price); // 对SQL语句中的第二个参数赋值
			ps.setInt(3, bookCount); // 对SQL语句中的第三个参数赋值
			ps.setString(4, author); // 对SQL语句中的第四个参数赋值
			ps.setInt(5, id); // 对SQL语句中的第五个参数赋值
			 int executeUpdate = ps.executeUpdate();
			 ps.close(); // 关闭PreparedStatement
			conn.close(); // 关闭Connection 
			
			if(executeUpdate==1){
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script language='JavaScript'>alert('修改成功');window.location.href='BookServlet';</script>");
			}else{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script language='JavaScript'>alert('修改失败');window.location.href='UpdateServlet?id="+id+"';</script>");
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
