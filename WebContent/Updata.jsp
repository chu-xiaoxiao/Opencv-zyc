<%@page import="com.zyc.opencv.DetectFaceDemo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>ShiYanLou Chat</title>
<!-- Set render engine for 360 browser -->
<meta name="renderer" content="webkit">

<!-- No Baidu Siteapp-->
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="alternate icon" href="assets/i/favicon.ico">
<link rel="stylesheet" href="assets/css/amazeui.min.css">
<link rel="stylesheet" href="assets/css/app.css">

<!-- umeditor css -->
<link href="umeditor/themes/default/css/umeditor.css" rel="stylesheet">
</head>
<body>
	<form  action="UploadServlet" enctype="multipart/form-data" method="post">
		<div class="am-u-sm-6"><input type="hidden" name = "file" id="file1" value="<%=  request.getParameter("file")%>">
		<input type="submit" name="uopLoad" value="上传"></div>
	</form>
</body>
</html>