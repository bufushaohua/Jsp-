package com.jxlg;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BookServlet")      //Ä¬ÈÏÖ´ÐÐÒ³
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currPage = 1; 
		if(request.getParameter("page") != null){
			currPage = Integer.parseInt(request.getParameter("page"));
		}
		BookDao dao = new BookDao();
		List<BookBean> list = dao.find(currPage);
		request.setAttribute("bookList", list);
		int pages;
		int count = dao.findCount();
		if(count % BookBean.PAGE_SIZE == 0){
			pages = count / BookBean.PAGE_SIZE;
		}else{
			pages = count / BookBean.PAGE_SIZE + 1;
		}
		StringBuffer sb = new StringBuffer();
		for(int i=1; i<=pages; i++){
			if(i == currPage){
				sb.append("¡º"+i+"¡»");
			}else{
				sb.append("<a href='BookServlet?page="+i+"'>"+i+"</a>");
			}
			sb.append("  ");
		}
		request.setAttribute("bar", sb.toString());
		request.getRequestDispatcher("booklist.jsp").forward(request, response);
	}

	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
	}
	
}
