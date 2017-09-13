<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		//读取一级分类js数据
		$.getJSON("js/json/class_1.js",function(data){
			$("#spu_publish_class_1_select").append("<option>请选择</option>");
			$("#spu_publish_class_2_select").append("<option>请选择</option>");
			$("#spu_publish_tm_select").append("<option>请选择</option>");
			$(data).each(function(i,json){
				$("#spu_publish_class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");
			});
		});
		
		//加载到一级分类的下拉列表中
	});
	
	function spu_publish_select_class_2_by_class_1_id(class_1_id){
		// 获得被选中的一级分类id
		// var class_1_id = $("#spu_publish_class_1_select option:selected").val();
		
		// 根据一级分类id加载二级分类和商品信息
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#spu_publish_class_2_select").empty();
			$("#spu_publish_class_2_select").append("<option>请选择</option>")
			$(data).each(function(i,json){
				$("#spu_publish_class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");
			});
		});
		
		$.getJSON("js/json/trade_mark_"+class_1_id+".js",function(data){
			$("#spu_publish_tm_select").empty();
			$("#spu_publish_tm_select").append("<option>请选择</option>")
			$(data).each(function(i,json){
				$("#spu_publish_tm_select").append("<option value="+json.id+">"+json.ppmch+"</option>");
			});
		});
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new jsp</title>
</head>
<body>
	<form action="spu_publish.do" method="post"  enctype="multipart/form-data">
		<select name="flbh1" id="spu_publish_class_1_select" onChange="spu_publish_select_class_2_by_class_1_id(this.value)">
		</select>
		<br>
		<select name="flbh2" id="spu_publish_class_2_select" >
		</select>
		<br>
		<select name="pp_id" id="spu_publish_tm_select" >
		</select>
		<br>
	
		商品名称:<input type="text" name="shp_mch"/><br>
		商品描述:<input type="text" name="shp_msh"/><br>
		商品图片:<input type="file" name="files"/><br>
			    <input type="file" name="files"/><br>
			    <input type="file" name="files"/><br>
			    <input type="file" name="files"/><br>
			    <input type="file" name="files"/><br>
		<input type="submit" value="提交发布"/>
	</form>
</body>
</html>