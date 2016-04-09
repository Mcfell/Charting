<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

<s:form action="UserRegister" method="post" >
	<s:label value="登录系统"></s:label>
	<s:textfield name="nickname" lable="帐号"></s:textfield>
	<s:password name="password" type="password" lable="密码"></s:password>
	<s:textfield name="email" type="email" lable="email"></s:textfield>
	<s:submit value="登录"></s:submit>
</s:form>
</body>
</html>