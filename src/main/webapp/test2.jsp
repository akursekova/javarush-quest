<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<head>
    <title> JSTL Example</title>
</head>

<body>
<c:set var = "salary" scope = "session" value = "${2000*5}"/>
<c:if test = "${ salary > 2000}">
<p>My salary is: <c:out value = "${salary}"/><p>
    </c:if>
</body>
</html>