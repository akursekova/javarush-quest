<%--
  Created by IntelliJ IDEA.
  User: alinakursekova
  Date: 26/10/22
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Third question</title>
</head>
<body>
<form action="thirdQuestion" method="POST">
    <h3>You went up to the bridge. Who are you?</h3>

    <td><input type="radio" name="decision" value="truth" />Tell the truth<br>
        <input type="radio" name="decision" value="lie" />Tell a lie</td><br>

    <%--    <select name="decision">--%>
    <%--        <option>Accept challenge</option>--%>
    <%--        <option>Reject challenge</option>--%>
    <%--    </select>--%>

    <button>Answer</button>

    <%
        String name=(String)session.getAttribute("userName");
        String ipAddress=(String)session.getAttribute("ipAddress");
        Integer numberOfGames = (Integer) session.getAttribute("numberOfGames");

    %>
    <br>
    <br>
    <br>
    <br>
    <br>
    Statistics:<br>
    IP address: <b> <%=ipAddress %></b> <br>
    Entered name: <b> <%=name %></b> <br>
    Played games: <b> <%=numberOfGames %></b> <br>


</form>
<script>
</script>

</body>
</html>
