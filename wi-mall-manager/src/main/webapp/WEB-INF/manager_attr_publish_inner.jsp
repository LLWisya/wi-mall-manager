<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 查出的字段为全部，但是有些不需要显示，如创建日期，所以指定显示的字段 -->
	<c:forEach items="${list_object_attr_value}" var="object_attr_value">
			&nbsp;属性名名称：${object_attr_value.shxm_mch }<br/>
			<hr/>
			<!-- 一个属性名名称下有多个属性值 -->
			<c:forEach items="${object_attr_value.list_value}" var="val">
				&nbsp;${val.shxzh }; &nbsp;&nbsp;&nbsp;&nbsp;
			</c:forEach>
			<br/>
	</c:forEach>
	
	
</body>
</html>