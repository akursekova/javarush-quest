<%@ page import="dev.akursekova.app.QuestService" %>
<%@ page import="dev.akursekova.app.Question" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--
  Created by IntelliJ IDEA.
  User: alinakursekova
  Date: 19/10/22
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!doctype html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value='/styles2.css' />">

</head>
<body>

<div class="container">
    <img src="<c:url value='/img/ufos.jpeg'/>" class="img-fluid" alt="Responsive image">
    <div class="centered">
        <form action="quest" method="POST">
            <body>
            <% Question currentQuestion=(Question)session.getAttribute("currentQuestion"); %>
            <%=currentQuestion.getText() %><br>

            <ul>
                <c:forEach var="answer" items="${currentAnswers}" varStatus="loop">
                    <%--            <li><c:out value="${answer.getText()}" /></li>--%>
                    <input type="radio" name="decision" value="${loop.index}" />${answer.getText()}<br>
                </c:forEach>
            </ul>
            </body>


            <c:choose>
                <c:when test="${currentQuestion.isLoose() == 'true' || currentQuestion.isWin() == 'true'}">
                    <input type="submit" name="answer" value="Restart" />
                </c:when>
                <c:otherwise>
                    <input type="submit" onclick="checkButton()" name="answer" value="Answer" />
                </c:otherwise>
            </c:choose>


            <%--    <h4 id="error" style= "color:red"> </h4>--%>

            <%
                String user=(String)session.getAttribute("user");
                String ipAddress=(String)session.getAttribute("ipAddress");
                Integer numberOfGames = (Integer) session.getAttribute("numberOfGames");

            %>
            <br>
            Statistics:<br>
            IP address: <b> <%=ipAddress %></b> <br>
            Entered name: <b> <%=user %></b> <br>
            Completed games: <b> <%=numberOfGames %></b> <br>
            <%--    <c:out value="${numberOfGames.get()}"></c:out><br>--%>

        </form>
    </div>
</div>


</body>

<script>
    function checkButton() {
        var getSelectedValue = document.querySelector(
            'input[name="decision"]:checked');

        if(getSelectedValue == null) {
            alert(`Please make a decision`);
            // document.getElementById("error").innerHTML
            //     = "*Please take a decision";
        }
    }
</script>

</html>
