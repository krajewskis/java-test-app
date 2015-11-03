<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<h1>Success!</h1>
<table>
	<tr>
		<td>Name :</td>
		<td>${account.name}</td>
	</tr>
	<tr>
		<td>Email :</td>
		<td>${account.email}</td>
	</tr>
	<tr>
		<td>Password :</td>
		<td>${account.passwordSha}</td>
	</tr>
</table>

<security:authorize access="!isAuthenticated()">
<style>
	ul.menu {padding: 0;}
	ul.menu ol {display: inline-block; padding: 0;}
</style>
<ul class="menu">
	<ol>
		<a href="<c:url value="/login" />">Login</a>
	</ol>
</ul>
</security:authorize>