<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function f1(v1){
	url='UpdateServlet?id='+v1;
	window.location.href = url;
}
function f2(v2){
    var x=confirm("是否删除该条数据？");   
    if(x){
    	//var gid =v;
    	//alert(v);
    	//var gid = $('input[name=id]').val();
    	url = 'DeleteServlet?id='+v2;
    	window.location.href = url;
    	//request.getRequestDispatcher(url).forward(request, response);
    	//response.sendRedirect(url);
    }else{
    	url = 'index.jsp';
    	window.location.href = url;
    }
    	
}
</script>
<style type="text/css">
button{
	float: left;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实验11:图书管理界面</title>
</head>
<body background="bgImages/B.jpg">
<table width="550" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333" >
	<tr>
		<td align="center" height="30" colspan="6" bgcolor="#EFEFEF">图书信息列表</td>
	</tr>
	<tr>
		<td align="center" width="36" height="27" bgcolor="#FFFFFF">编号</td>
		<td align="center" width="137" bgcolor="#FFFFFF">图书名称</td>
		<td align="center" width="85"  bgcolor="#FFFFFF">单价</td>
		<td align="center" width="36"  bgcolor="#FFFFFF">数量</td>
		<td align="center" width="150"  bgcolor="#FFFFFF">作者</td>
		<td align="center" width="100"  bgcolor="#FFFFFF">操作</td>
	</tr>
	<C:forEach var="book" items="${requestScope.bookList }">
	<tr>
		<td height="27" bgcolor="#FFFFFF" align="center">
		<C:out value="${book.id}" /></td>
		<td bgcolor="#FFFFFF" >&nbsp;
		<C:out value="${book.name}"></C:out></td>
		<td bgcolor="#FFFFFF" >&nbsp;
		<C:out value="${book.price}"></C:out></td>
		<td bgcolor="#FFFFFF" >&nbsp;
		<C:out value="${book.bookCount}"></C:out></td>
		<td bgcolor="#FFFFFF" >&nbsp;
		<C:out value="${book.author}"></C:out></td>
		<td bgcolor="#FFFFFF" >
			<%-- <form action="DeleteServlet" method="get" >
				<input type="hidden" name="id" value="${book.id}" />
				<input type="submit" value="修改" onclick="f1()">
				<input type="submit" value="删除" onclick="f2()">
			</form> --%>
				<button onclick="f1(${book.id})">修  改</button>
				<button onclick="f2(${book.id})">删  除</button>
		</td>
	</tr>
	</C:forEach>
	<tr>
		<td align="center" height="40" colspan="6" bgcolor="#EFEFEF">
			<form action="addition.jsp" method="post">
				<input type="submit" value="添  加" >
			</form>
		</td>
	</tr>
</table>
<div align="center" style="padding-top: 10px" width="98%">
	<%=request.getAttribute("bar") %>
</div>
</body>
</html>