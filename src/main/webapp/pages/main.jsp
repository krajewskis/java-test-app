<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>AppTest</title>
<style>
	ul.menu {padding: 0;}
	ul.menu ol {display: inline-block; padding: 0;}
</style>
</head>
<body>
	<ul class="menu">
		<ol>
			<a href="<c:url value="/" />">Home</a>
		</ol>
		<ol>
			<a href="<c:url value="/users" />">Users</a>
		</ol>
		<ol>
			<a href="<c:url value="/users/edit/${pageContext.request.userPrincipal.name}" />">Account</a>
		</ol>
		<ol>
			<a href="<c:url value="/j_spring_security_logout" />">Logout</a>
		</ol>
	</ul>
	<hr />
	<decorator:body />
</body>
</html>