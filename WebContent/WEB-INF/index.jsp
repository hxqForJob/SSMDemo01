<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
function save(){
//	 ajax异步提交
//alert($("#itemForm").serialize());  
//alert($("#loginForm").serialize());

	 $.ajax({
		 url:'${pageContext.request.contextPath }/login',
		 type:'post',
		 data:$("#loginForm").serialize(),
		 dataType:'json',
		 success:function(data){
//			 data :{"success":true|false,"message":"操作成功"|“操作失败”}
			 if(data.success){
				location.href="${pageContext.request.contextPath }/list";
			 }else{
				alert(data.message); 
			 }
		 }
		 
		 
		 
	 })
	 
}
</script>
</head>
<body>
	<form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
		<table>
				<tr>
					<td>用户名:</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" onclick="save()" value="登录">
					</td>
				</tr>
		</table>
	</form>
</body>
</html>