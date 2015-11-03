<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<h1>Home</h1>
<%-- <h2>Welcome ${username}</h2> --%>


<security:authorize access="hasRole('ROLE_USER')">
	<h2>Hello User ${pageContext.request.userPrincipal.name}!</h2>
</security:authorize>

<security:authorize access="hasRole('ROLE_ADMIN')">
	<h2>Hello Admin ${pageContext.request.userPrincipal.name}!</h2>
</security:authorize>