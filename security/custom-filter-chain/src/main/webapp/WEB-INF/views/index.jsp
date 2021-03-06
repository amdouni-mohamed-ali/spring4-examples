<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<h1>Hello ${pageContext.request.userPrincipal.name} ...</h1>
<hr />
<p>
	<c:if test="${not empty messageAdmin}">
		<h3>${messageAdmin}</h3>
	</c:if>
	<c:if test="${not empty messageUser}">
		<h3>${messageUser}</h3>
	</c:if>
	<hr>
	<c:if test="${not empty passedByFilter}">
		<h3>${passedByFilter}</h3>
	</c:if>
</p>
<hr />
<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>
<hr />
<img src="<c:url value='/static/images/Capture.PNG' />" alt="Not Found">
<p>it looks like this is not the final list of filters. may this is the groups of filters. who Knows ?</p>
<img src="<c:url value='/static/images/Capture2.PNG' />" alt="Not Found">
</html>