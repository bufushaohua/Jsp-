<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function mycheck() {
		if(form1.username.value==""){
			alert("请输入用户名！");
			form1.username.focus();
			return;
		}
		if(form1.pwd.value==""){
			alert("请输入密码！");
			form1.pwd.focus();
			return;
		}
		if(form1.yanzheng.value==""){
			alert("请输入验证码！");
			form1.yanzheng.focus();
			return;
		}
		if(form1.vcode.value!=form1.yanzheng.value){
			alert("请输入正确的验证码！");
			form1.yanzheng.focus();
			return;
		}
		form1.submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
</head>
<body background="bgImages/A.jpg">
<center >
        <h1 >登录</h1>
            <form id="indexform" name="form1" action="LoginCheck.jsp" method="post">
                <table border="0">
                    <tr >
                        <td>账号：</td>
                        <td><input type="text" name="username">
                        </td>
                    </tr>
                    <tr>
                        <td>密码：</td>
                        <td><input type="password" name="pwd">
                        </td>
                    </tr>
                    <tr>
                    	<td>验证码：</td>
                    	<td><input type="text" name="yanzheng">&nbsp;
                    	<%
							int intmethod=(int)((((Math.random())*11))-1);
							int intmethod2=(int)((((Math.random())*11))-1);
							int intmethod3=(int)((((Math.random())*11))-1);
							int intmethod4=(int)((((Math.random())*11))-1);
							String intsum=intmethod+""+intmethod2+intmethod3+intmethod4;
						%>
						<input type="hidden" name="vcode" value="<%=intsum %>">
						<img src="images/<%=intmethod %>.gif">		<img src="images/<%=intmethod2 %>.gif">
						<img src="images/<%=intmethod3 %>.gif">		<img src="images/<%=intmethod4 %>.gif">
                    	</td>
                    </tr>
                </table>
            <br>
                <input type="button" value="登录" onclick="mycheck()">&nbsp;
                <input type="reset" value="重置" >
            </form>
    </center>
</body>
</html>