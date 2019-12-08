<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

</head>

<body>
<div align='center'>

    <form action="game.form" method="post">

        <h1>Welcome to Rock, paper, scissors game</h1>

        <h2>To play with Computer submit your choice</h2>
        <br>
        Enter your 'player_id':
        <br>
        <input type="text" SIZE=20 name="player_id" value="<%= request.getAttribute("playerId")%>"/>
        <br>

        Select your move:
        <select name="moveSelected">
            <c:forEach items="${moves}" var="move">
                <option value="${moves}">${move}</option>
            </c:forEach>
        </select>


        <br>

        <p style=color:red>
            <%= request.getAttribute("errorMessage")%>
        </p>
        <br>

        <INPUT TYPE=submit value="Play">
    </form>
</div>
</body>
</html>

