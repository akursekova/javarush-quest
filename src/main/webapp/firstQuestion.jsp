<%@ page import="dev.akursekova.app.GameCounter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alinakursekova
  Date: 19/10/22
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>First question</title>
</head>
<body>
<form action="firstQuestion" method="POST">
    <h3>You've lost your memory. Accept the UFO challenge?</h3>

        <td><input type="radio" name="decision" value="accept" />Accept challenge<br>
            <input type="radio" name="decision" value="reject" />Reject challenge</td><br>

<%--    <select name="decision">--%>
<%--        <option>Accept challenge</option>--%>
<%--        <option>Reject challenge</option>--%>
<%--    </select>--%>

    <button>Answer</button><br>

    <%
        String name=(String)session.getAttribute("userName");
        String ipAddress=(String)session.getAttribute("ipAddress");
        Integer numberOfGames = (Integer) session.getAttribute("numberOfGames");

    %>
    Entered Name is <b> <%=name %></b> <br>
    IP is <b> <%=ipAddress %></b> <br>
    Played games <b> <%=numberOfGames %></b> <br>


</form>
<script>
</script>

</body>
</html>
