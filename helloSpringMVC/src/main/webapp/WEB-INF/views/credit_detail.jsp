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
	<thead>
		<tr>
			<td class="label">년도</td>
			<td class="label">학기</td>
			<td class="label">교과코드</td>
			<td class="label">교과목명</td>
			<td class="label">구분</td>
			<td class="label">학점</td>
		</tr>
	</thead>
	<c:forEach var="list" items="${courseByTermDetails}">
	<tr>
	<td><c:out value="${list.year}"/></td>
	<td><c:out value="${list.term}"/></td>
	<td><c:out value="${list.code}"/></td>
	<td><c:out value="${list.name}"/></td>
	<td><c:out value="${list.category}"/></td>
	<td><c:out value="${list.credit}"/></td>
	<tr>
	</c:forEach>
	</table>
</form>
</body>
</html>