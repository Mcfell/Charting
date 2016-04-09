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
SUCCESS: <s:property value="model.nickname"/>
<script>
	function GetRequest() {
	   var url = location.search; //获取url中"?"符后的字串
	   if (url.indexOf("?") != -1) {    //判断是否有参数
	      var str = url.substr(1); //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
	      strs = str.split("=");   //用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
	      alert(strs[1]);          //直接弹出第一个参数 （如果有多个参数 还要进行循环的）
	   }
	}
	GetRequest();
</script>
</body>
</html>