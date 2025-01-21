<%--
  Created by IntelliJ IDEA.
  User: 123fe
  Date: 11.01.2025
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>New Match - Tennis Scoreboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <h1>Start New Match</h1>

    <% if (request.getAttribute("error") != null) { %>
    <div class="error-message">
        <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <form method="post" action="${pageContext.request.contextPath}/new-match" class="match-form">
        <div class="form-group">
            <label for="player1">Player 1:</label>
            <input type="text" id="player1" name="player1" required
                   value="${param.player1}" placeholder="Enter player 1 name">
        </div>

        <div class="form-group">
            <label for="player2">Player 2:</label>
            <input type="text" id="player2" name="player2" required
                   value="${param.player2}" placeholder="Enter player 2 name">
        </div>

        <button type="submit" class="btn-primary">Start Match</button>
    </form>

    <div class="back-link">
        <a href="${pageContext.request.contextPath}/">Back to Home</a>
    </div>
</div>
</body>
</html>