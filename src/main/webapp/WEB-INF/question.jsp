<%@ page import="dev.akursekova.quest.subjects.Question" %>
<%@ page import="dev.akursekova.quest.subjects.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <img src="<c:url value='/img/ufo.jpeg'/>" class="img-fluid" alt="Responsive image">
    <div class="centered">
        <form action="quest" method="POST">
            <body>
            <% Question currentQuestion = (Question) session.getAttribute("currentQuestion"); %>
            <%=currentQuestion.getText() %><br>

            <ul>
                <c:forEach var="answer" items="${currentAnswers}" varStatus="loop">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="decision" id="flexRadioDefault1"
                               value="${loop.index}">
                        <label class="form-check-label" for="flexRadioDefault1">
                                ${answer.getText()}
                        </label>
                    </div>
                </c:forEach>
            </ul>
            </body>


            <c:choose>
                <c:when test="${currentQuestion.isLoose() == 'true' || currentQuestion.isWin() == 'true'}">
                    <input type="submit" name="answer" value="Restart"/>
                </c:when>
                <c:otherwise>
                    <input type="submit" onclick="checkButton()" name="answer" value="Answer"/>
                </c:otherwise>
            </c:choose>

            <%
                User user = (User) session.getAttribute("user");
                Integer numberOfGames = user.getNumberOfGames();
                String ipAddress = user.getIpAddress();
            %>

            <button type="button" class="btn btn-primary btn-md" id="myBtn">Statistics</button>


            <div class="modal fade" id="myModal" role="dialog" data-backdrop="false">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            Statistics
                            <button type="button" class="close" data-dismiss="modal">×</button>
                        </div>
                        <div class="modal-body">
                            <p>
                                Entered name: <b><%=user.getName() %>
                            </b> <br>
                                IP address: <b><%=ipAddress %>
                            </b> <br>
                                Completed games: <b><%=numberOfGames %>
                            </b> <br>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

</body>

<script>
    function checkButton() {
        let getSelectedValue = document.querySelector(
            'input[name="decision"]:checked');
        if (getSelectedValue == null) {
            alert(`Please make a decision`);
        }
    }

    $(document).ready(function () {
        $("#myBtn").click(function () {
            $("#myModal").modal('show');
        });
    });
</script>
</html>
