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
			Class.forName("com.mysql.jdbc.Driver"); // 加载数据库驱动，注册到驱动管理器
			String url = "jdbc:mysql://localhost:3306/db_database11";// 数据库连接字符串
			String username = "root"; // 数据库用户名
			String password = "root"; // 数据库密码
			// 创建Connection连接
			Connection conn = DriverManager.getConnection(url, username,
					password);
			String sql = "select * from tb_book where id=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);// 获取PreparedStatement
			ps.setInt(1, id); // 对SQL语句中的第一个参数赋值
			//Statement stmt = conn.createStatement();
			ResultSet rs = ps.executeQuery();			// 执行查询	
			//List<BookBean> list = new ArrayList<>();		// 实例化List对象	
			BookBean book = new BookBean();	
			while(rs.next()){								// 光标向后移动，并判断是否有效
								// 实例化Book对象
				book.setId(rs.getInt("id"));				// 对id属性赋值
				book.setName(rs.getString("name"));		// 对name属性赋值
				book.setPrice(rs.getDouble("price"));		// 对price属性赋值
				book.setBookCount(rs.getInt("bookCount"));	// 对bookCount属性赋值
				book.setAuthor(rs.getString("author"));		// 对author属性赋值
				//list.add(book); 							// 将图书对象添加到集合中
			}
			request.setAttribute("book", book);		
			ps.close(); // 关闭PreparedStatement
			conn.close(); // 关闭Connection
			request.getRequestDispatcher("update.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//response.setContentType("text/html;charset=utf-8");
		//PrintWriter out = response.getWriter();
		//out.print("<script language='JavaScript'>alert('修改成功');window.location.href='index.jsp';</script>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
