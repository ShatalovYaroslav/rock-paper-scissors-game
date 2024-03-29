<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/js/service.js"></script>

</head>

<body style="background-image: url('/img/image_back.jpg');  width: 100%; height: 100%; background-size: 100% 100%;background-repeat: no-repeat;">
<div align='center' style=color:white>

    <form action="/game/playWithPC/" method="post" name="gameForm" id="form_id" class="form_class">

        <h1>Welcome to Rock, Paper, Scissors game</h1>

        <h2>To play with Computer submit your choice</h2>
        <br>
        Enter your 'player ID':
        <input type="text" SIZE=20 name="player_id" id="player_id" value="<%= request.getAttribute("playerId")%>"/>
        <br>
        <br>
        Select your move:
        <select name="move">
            <c:forEach items="${moves}" var="move">
                <option value="${move}">${move}</option>
            </c:forEach>
        </select>

        <br>
        <p id="Errors" style=color:red>
            <%= request.getAttribute("errorMessage")%>
        </p>
        <br>

        <input value="Play" type="button" onclick="submitform()" style="width: 7%; height: 15%;"/>
    </form>

    <div id="GeneralDiv" style=color:red>
         <div id="Result"/>
    </div>
     <div id="OpponentResult"/>
</div>
</body>
</html>

