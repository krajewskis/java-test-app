<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tag" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<h1>Users</h1>
<table>
	<c:choose>
		<c:when test="${not empty users}">
			<tr>
				<th><tag:message code="users.no" /></th>
				<th><tag:message code="users.name" /></th>
				<th><tag:message code="users.password" /></th>
				<th><tag:message code="users.id" /></th>
				<th><tag:message code="users.actions" /></th>
			</tr>
			<c:forEach items="${users}" var="account" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${account.name}</td>
					<td>${account.password}</td>
					<td>${account.id}</td>
					<td>
						<security:authorize access="hasRole('ROLE_ADMIN')">
						<a href="<c:url value="/users/edit/${account.name}" />"><tag:message code="list.action.edit" /></a> |
						<a href="<c:url value="/users/remove/${account.name}" />"><tag:message code="list.action.remove" /></a>
						</security:authorize>
						<security:authorize access="hasRole('ROLE_USER')">
						-
						</security:authorize>
					</td>					
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="5"><tag:message code="list.empty" /></td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>
