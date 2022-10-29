<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<head>
    <title> Each Tag Example&</title>
</head>

<body>
<c:forEach var = "i" begin = "1" end = "5">
Item <c:out value = "${i}"/><p>
    </c:forEach>
</body>
</html>