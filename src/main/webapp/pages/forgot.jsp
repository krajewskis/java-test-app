<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<style>
	form label {display:inline-block; width: 150px; text-align: right;}
	form span {color:red;}
	form * {padding: 0;}
</style>


<h1>Password reminder</h1>
<form:form method="POST" commandName="account">
	<ul>
		<ol>
			<label>Name:</label>
			<form:input path="name" />
			<form:errors path="name" cssClass="error" />
		</ol>
		<ol>
			<label>Email:</label>
			<form:input path="email" />
			<form:errors path="email" cssClass="error" />
		</ol>
		<ol>
			<label></label>
			<td colspan="3"><input type="submit" />
		</ol>
	</ul>
</form:form>

<style>
	ul.menu {padding: 0;}
	ul.menu ol {display: inline-block; padding: 0;}
</style>
<ul class="menu">
	<ol>
		<a href="<c:url value="/login" />">Login</a>
	</ol>
</ul>

