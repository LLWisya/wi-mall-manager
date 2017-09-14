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
	$(function() {
		//一级分类
		$.getJSON("js/json/class_1.js", function(data) {
			$("#spu_publish_class_1_select").append("<option>请选择</option>");
			$("#spu_publish_class_2_select").append("<option>请选择</option>");
			$("#spu_publish_tm_select").append("<option>请选择</option>");
			
			$.each(data, function(i, json) {
				$("#spu_publish_class_1_select").append("<option value=" + json.id + ">" + json.flmch1 +"</option>");
			});
		});
	});
	
	function spu_publish_select_class_2_by_class_1_id (class_1_id) {
		//二级分类
		$.getJSON("js/json/class_2_" + class_1_id + ".js", function(data) {
			$("#spu_publish_class_2_select").empty();
			$("#spu_publish_class_2_select").append("<option>请选择</option>");
			$.each(data, function(i, json) {
				$("#spu_publish_class_2_select").append("<option value=" + json.id + ">" + json.flmch2 +"</option>");
			});
		});
		
	};
	
	//根据二级分类查询商品信息
	function spu_publish_select_attr_by_class_2_id(class_2_id) {
		<%-- 使用get请求时不会自动加载内嵌页，待解决bug.. --%>
// 		$.getJSON("get_attr_by_class_2_id/" + class_2_id + ".do", function(data) {
// 			//返回的是一个内嵌页，因此把该内嵌页添加到本页面中即可
// 			$("#attr_publish_inner").html(data);
// 		});
		$.post("get_attr_by_class_2_id.do",{"class_2_id":class_2_id}, function(data) {
			$("#attr_publish_inner").html(data);
		});
	};
	
	//添加分类属性
	function attr_publish_goto_attr_add(){
		//需要根据二级分类来添加
		var class_2_id = $("#spu_publish_class_2_select option:selected").val();
		var class_2_name = $("#spu_publish_class_2_select option:selected").html();
		
		if (class_2_id == "请选择") {
			alert("请选择二级分类");
			return false;
		}
		
		location.href = "goto_attr_add/" + class_2_id + "/" + class_2_name + ".do";
	}
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<select name="flbh1" id="spu_publish_class_1_select" onChange="spu_publish_select_class_2_by_class_1_id(this.value)">
		</select>
		<br>
		<select name="flbh2" id="spu_publish_class_2_select" onchange="spu_publish_select_attr_by_class_2_id(this.value)">
		</select>
		<br>
		<br>
		<div id = "attr_publish_inner"></div>


		<hr>
		<a href="javascript:;" onclick="attr_publish_goto_attr_add()">添加分类属性</a>
</body>
</html>