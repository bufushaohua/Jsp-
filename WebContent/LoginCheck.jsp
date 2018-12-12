<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录验证</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String name=request.getParameter("username");
	String pwd=request.getParameter("pwd");
	String message;
	if(!request.getParameter("vcode").equals(request.getParameter("yanzheng"))){
		message="您输入的验证码不正确！";
	}else{
		if(name.equals("jxlg")){
			if(pwd.equals("123")){
				message="验证成功！，可正常登录系统";
				response.setContentType("text/html;charset=utf-8");
				PrintWriter Out = response.getWriter();
				Out.print("<script language='JavaScript'>alert('登录成功！');window.location.href='BookServlet';</script>");
			}
			else{
				message="用户密码输入错误！";
				response.setContentType("text/html;charset=utf-8");
				PrintWriter Out = response.getWriter();
				Out.print("<script language='JavaScript'>alert('登录失败！');window.location.href='Login.jsp';</script>");
			}
		}else{
			message="用户名输入错误！";
			response.setContentType("text/html;charset=utf-8");
			PrintWriter Out = response.getWriter();
			Out.print("<script language='JavaScript'>alert('登录失败！');window.location.href='Login.jsp';</script>");
		}
	}
%>
<script type="text/javascript">
	alert("<%=message%>")
</script>
</body>
</html>