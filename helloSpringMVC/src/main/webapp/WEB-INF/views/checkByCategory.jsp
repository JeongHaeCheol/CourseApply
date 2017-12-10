<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form>
	<table class="test" border="2">
	<tr>
	<c:forEach var="list" items="${creditByCategory}">
	<td><c:out value="${list.category}"/></td>
	</c:forEach>
	<td>총학점</td>
	</tr>


	<tr>
	<c:forEach var="list" items="${creditByCategory}">
	<td><c:out value="${list.credit}"/></td>
	    <c:set var="sum" value="${sum + list.credit}"/>
	</c:forEach>
	<td><c:out value="${sum}"/></td>
	</tr>
	
	</table>
</form>

</body>
</html>