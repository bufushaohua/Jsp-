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
			Class.forName("com.mysql.jdbc.Driver"); // 加载数据库驱动，注册到驱动管理器
			String url = "jdbc:mysql://localhost:3306/db_database11";// 数据库连接字符串
			String username = "root"; // 数据库用户名
			String password = "root"; // 数据库密码
			// 创建Connection连接
			Connection conn = DriverManager.getConnection(url, username,
					password);
			String isExist = "select * from tb_book where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(isExist) ;
			pstmt.setInt(1, id);
	        ResultSet rs = pstmt.executeQuery();
			if(!rs.next()){
				String sql = "insert into tb_book(id,name,price,bookCount,author) values(?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);// 获取PreparedStatement
				ps.setInt(1, id);
				ps.setString(2, name); // 对SQL语句中的第一个参数赋值
				ps.setFloat(3, price); // 对SQL语句中的第二个参数赋值
				ps.setInt(4, bookCount); // 对SQL语句中的第三个参数赋值
				ps.setString(5, author); // 对SQL语句中的第四个参数赋值
				 int executeUpdate = ps.executeUpdate();
				 ps.close(); // 关闭PreparedStatement
				conn.close(); // 关闭Connection 
				//判断操作是否执行成功
				if(executeUpdate==1){
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script language='JavaScript'>alert('修改成功');window.location.href='BookServlet';</script>");
				}else{
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("<script language='JavaScript'>alert('修改失败');window.location.href='index.jsp';</script>");
				}
			}else{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script language='JavaScript'>alert('该条数据编号已存在哦，请重新填写编号！');window.location.href='addition.jsp';</script>");
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
