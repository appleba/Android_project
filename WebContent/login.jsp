<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"> 
	   传习教育网上书城
	</div>
    <div style="width:380px; height:50px; float:right; position:absolute; left:60%; top:2px; line-height:50px">
	    <img src="images/cart.gif" style="margin-bottom:-4px" width="26" height="23">
		  欢迎您： madan &nbsp;
                  <a href="myAccount.jsp">我的帐户</a>
				| <a href="productCategory.jsp">商品分类</a> 
				| <a href="#">帮助中心</a>  
		</div>
	<div id="navbar">
		<form method="get" name="search" action="">
			搜索：<input class="input-text" type="text" name="keywords" /><input class="input-btn" type="submit" name="submit" value="" />
		</form>
	</div>
</div>
<div id="login">
	<h2>用户登陆  ${message}</h2>
	<form onsubmit="return yanzhan()" method="post" 
	action="http://127.0.0.1:8080/book6c2/logingServlet.do">
		<dl>
			<dt>用户名：</dt>
			<dd><input class="input-text" type="text" name="userName" id="uid" />
            <span id="utip"></span></dd>
			<dt>密　码：</dt>
			<dd><input class="input-text" type="password" name="passWord" id="pid" />
            <span id="ptip"></span></dd>
            <dt>记住密码:</dt>
            <dd>
              <input type="radio" name="autologin" checked="checked" 
                                              value="${60*60*24*7 }" />7天
			  <input type="radio" name="autologin" 
                                             value="${60*60*24*15 }" />15天
			  <input type="radio" name="autologin" 
                                             value="${60*60*24*30 }" />30天
            </dd>
			<dt></dt>
			<dd class="button"><input class="input-btn" type="submit" name="submit" value="" /><input class="input-reg" type="button" name="register" value="" onclick="window.location='register.html';" /></dd>
		</dl>
	</form>
</div>
<div id="footer" class="wrap">
	传习教育网上书城 &copy; 版权所有
</div>
<script language="javascript" type="text/javascript">
  function yanzhan(){
	  document.getElementById("utip").innerHTML ="";
	  document.getElementById("ptip").innerHTML ="";
	  //alert("表单验证信息...");
	var uobj=document.getElementById("uid");
	if(uobj.value==""){
	   document.getElementById("utip").innerHTML = "用户名不能为空";
	   return false;
	}
	
	var pobj=document.getElementById("pid");
	if(pobj.value==""){
	   document.getElementById("ptip").innerHTML = "密码不能为空";
	   return false;
	}
	return true;
  }
</script>
</body>
</html>
    