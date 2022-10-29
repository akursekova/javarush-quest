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


</form>
<script>
</script>

</body>
</html>
