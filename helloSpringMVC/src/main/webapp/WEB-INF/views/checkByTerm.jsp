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
			<td class="label">이수학점</td>
			<td class="label">상세보기</td>
		</tr>
	</thead>
	<c:forEach var="list" items="${creditByYearTerms}">
	<tr>
	<td><c:out value="${list.year}"/></td>
	<td><c:out value="${list.term}"/></td>
	<td><c:out value="${list.credit}"/></td>
	<td><a href="${pageContext.request.contextPath}/credit_detail?year=${list.year}&term=${list.term}">링크</a></td>
	<tr>
	</c:forEach>
	</table>
</form>
</body>
</html>