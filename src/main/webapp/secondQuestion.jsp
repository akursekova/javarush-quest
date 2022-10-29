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
  <title>Second question</title>
</head>
<body>
<form action="secondQuestion" method="POST">
  <h3>You've accepted the challenge. Will you come over the captain and will go up the bridge to him?</h3>

  <td><input type="radio" name="decision" value="accept" />Accept to go up to the bridge<br>
    <input type="radio" name="decision" value="reject" />Reject to go up to the bridge</td><br>

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
