<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>AppTest Login</title>
<style>
	form label {display:inline-block; width: 150px; text-align: right;}
	form span {color:red;}
	form * {padding: 0;}
</style>
</head>
<body>
	<h1>Login</h1>
	<form action="<c:url value='j_spring_security_check' />" method='POST'>
		<ul>
			<ol>
				<label>User:</label>
				<input type='text' name='j_username' value=''>
				<c:if test="${not empty error}">
					<span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
				</c:if>
			</ol>
			<ol>
				<label>Password:</label>
				<input type='password' name='j_password' />
			</ol>
			<ol>
				<label></label>
				<input name="submit" type="submit" />
			</ol>
			<ol>
				<a href="<c:url value="/register" />" >Get Account!</a>
			</ol>
			<ol>
				<a href="<c:url value="/forgot" />" >Forgot password!</a>
			</ol>
		</ul>

	</form>
</body>
</html>