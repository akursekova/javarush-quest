<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />">

</head>
<body>
<div class="container">
    <img src="<c:url value='/img/ufo.jpeg'/>" class="img-fluid" alt="Responsive image">
    <div class="top-left">
        You are standing in a spaceport and ready to board your ship.<br>
        Isn't that what you've been dreaming about?<br>
        Become the captain of a galactic ship with a crew that will perform feats under your command.<br>
        So go ahead!
    </div>
    <div class="centered">
        Getting to know the crew<br>
        When you boarded the ship, you were greeted by a girl with a black folder in her hands:<br>
        - Hello, commander! I am Zinaida - your helper. Do you see? <br>
        There, in the corner, our navigator, Sergeant Peregarny Shleif, is drinking coffee, our flight mechanic, Cherny Bogdan,
        is sleeping under the helm, and Sergey Stalnaya Pyatka, our navigator, is photographing him.<br>
        And what is your name?<br>

        <form action="quest" method="post">
            Username: <input type="text" id="username" name="name" />
            <input type="submit" id="submit" value="Introduce yourself" />
        </form>
    </div>
</div>
</body>
</html>