<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<style>
	form label {display:inline-block; width: 150px; text-align: right;}
	form span {color:red;}
	form * {padding: 0;}
</style>


<h1>Account</h1>
<form:form method="POST" commandName="account">
	<ul>
		<ol>
			<label>Name:</label>
			<form:input path="name" disabled="${pageContext.request.userPrincipal.name!=null}" />
			<form:errors path="name" cssClass="error" />
		</ol>
		<ol>
			<label>Email:</label>
			<form:input path="email" disabled="${pageContext.request.userPrincipal.name!=null}" />
			<form:errors path="email" cssClass="error" />
		</ol>
		<ol>
			<label>Password:</label>
			<form:password path="password" />
			<form:errors path="password" cssClass="error" />
		</ol>
		<ol>
			<label>Confirm password:</label>
			<form:password path="confirmPassword" />
			<form:errors path="confirmPassword" cssClass="error" />
		</ol>

		<ol>
			<label></label>
			<td colspan="3"><input type="submit" />
		</ol>
	</ul>
</form:form>



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

