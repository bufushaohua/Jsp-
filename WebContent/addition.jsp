<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function skip(){
	url = 'BookServlet';
	window.location.href = url;
}
function check(){
	if(form1.id.value==""){
		alert("提示：编号不能为空哦！");
		form1.id.focus();
		return;
	}
	if(form1.name.value==""){
		alert("提示：图书名称不能为空哦！");
		form1.name.focus();
		return;
	}
	form1.submit();
}
</script>
<style type="text/css">
	td{
		width: 112px;
		height: 25px;
	}
	input{
		width: 150px;
		height: 25px;
	}
	a{
		width: 50px;
		height: 25px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加数据</title>
</head>
<body background="bgImages/B.jpg">
<form action="AdditionServlet" method="post" name="form1" >
	<table  border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#333333" >
	<tr>
		<td align="center" height="30" colspan="6" bgcolor="#EFEFEF">新增图书信息</td>
	</tr>
	<tr>
		<td align="center" bgcolor="#FFFFFF">编号</td>
		<td align="center" bgcolor="#FFFFFF">图书名称</td>
		<td align="center" bgcolor="#FFFFFF">单价</td>
		<td align="center" bgcolor="#FFFFFF">数量</td>
		<td align="center" bgcolor="#FFFFFF">作者</td>
		<td align="center" bgcolor="#FFFFFF">操作</td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF" >
			<input type="text" name="id"  value="${book.id}" />
		</td>
		<td bgcolor="#FFFFFF" >
			<input type="text" name="name"  value="${book.name}" />
		</td>
		<td bgcolor="#FFFFFF" >
			<input type="text" name="price"  value="${book.price}" />
		</td>
		<td bgcolor="#FFFFFF" >
			<input type="text" name="bookCount"  value="${book.bookCount}" />
		</td>
		<td bgcolor="#FFFFFF" >
			<input type="text" name="author"  value="${book.author}" />
		</td>
		<td bgcolor="#FFFFFF">
			<input style="width: 50px" type="button" value="确   定" onclick="check()" >&nbsp;
			<input style="width: 50px" type="button" value="返   回" onclick="skip()">
		</td>
	</tr>
</table>
</form>
</body>
</html>