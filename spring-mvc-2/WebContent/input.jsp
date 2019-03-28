<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<form:form action="${pageContext.request.contextPath }/emp" method="post" modelAttribute="employee">
	<br>
	<c:if test="${empty employee.id}">
	lastName <form:input  path="lastName" /><form:errors path="lastName"></form:errors>
	</c:if>
	<c:if test="${!empty employee.id}">
	id:<form:input path="id"/>
	<input type="hidden" name="_method" value="PUT">
	</c:if>
	<br>
	email<form:input path="email"/>	 <form:errors path="email"></form:errors>
	<br>
		<%		
		Map<String,String > genders =new HashMap<>();
		genders.put("1", "Male");
		genders.put("0", "Female"); 
		request.setAttribute("genders", genders);
		%>
	gender <form:radiobuttons path="gender" items="${genders}"/>
	<br>
	department:<form:select path="department.id" items="${department }" itemLabel="departmentName" itemValue="id"></form:select>
	<br>
	birth<form:input path="birth"/><form:errors path="birth"></form:errors>
	<br>
	salary<form:input path="salary"/>
	<input type="submit" value="submit">
	</form:form>
</body>
</html>