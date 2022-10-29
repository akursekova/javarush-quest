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
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%--    <script src="/static/jquery-3.6.0.min.js"></script>--%>
<%--    <script src="/lib/jquery.plugin.js"></script>--%>

</head>
<body>



<form action="rejectChallenge" method="POST">
    <h3>You have rejected the challenge. You have lost.</h3>
</form>

<form action="restart" method="POST">
    <button name="restart">Start again</button>
</form>



<%--<button type="restart" name ="restart" id="restart" onClick="restart()"><b>Start again</b></button>--%>

<%--<button onclick="restart()" name="restart">Start again</button>--%>

<%--<form action="firstQuestion.jsp" method="POST">--%>
<%--    <button>Start again</button>--%>
<%--</form>--%>


<%--<c>--%>
<%--    <h1>IT'S A BUTTON</h1>--%>
<%--    <br>--%>
<%--    <button onclick="restart()">Start again</button>--%>
<%--</c>--%>

<script>
    // function restart() {
    //     console.log(this)
    //     $.ajax({
    //         url: '/restart',
    //         type: 'POST',
    //         contentType: 'application/json;charset=UTF-8',
    //         async: false,
    //         success: function () {
    //             location.reload();
    //             // var successUrl = "/restart";
    //             // window.location.href = successUrl;
    //         },
    //         error: function (httpRequest, textStatus, errorThrown) {  // detailed error messsage
    //             alert("Error: " + textStatus + " " + errorThrown + " " + httpRequest);
    //         }
    //     });
    // }

    // function restart() {
    //     //alert("Please enter Your Message..!");
    //
    //     $.ajax({
    //         type: 'post',
    //         url: '/restart',
    //         //success: alert("success ajax");
    //         if (success) {
    //             var pageToRedirectUrl = "/restart"; //URL of your page
    //             window.location.href = pageToRedirectUrl ;
    //         }
    //     });
    // }
</script>

</body>
</html>
