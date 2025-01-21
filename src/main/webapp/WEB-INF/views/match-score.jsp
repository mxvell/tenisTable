<%--
  Created by IntelliJ IDEA.
  User: 123fe
  Date: 12.01.2025
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Match Score - Tennis Scoreboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <h1>Match Score</h1>

    <div class="score-board">
        <div class="player-info">
            <h2>${match.player1.name}</h2>
            <p>Sets: ${score.player1Sets}</p>
            <p>Games: ${score.currentSetPlayer1Games}</p>
            <p>Current Game: ${score.currentGameScore}</p>
        </div>

        <div class="player-info">
            <h2>${match.player2.name}</h2>
            <p>Sets: ${score.player2Sets}</p>
            <p>Games: ${score.currentSetPlayer2Games}</p>
        </div>
    </div>

    <% if (!score.isFinished()) { %>
    <div class="score-controls">
        <form method="post" action="${pageContext.request.contextPath}/match-score">
            <input type="hidden" name="uuid" value="${param.uuid}">
            <input type="hidden" name="winner" value="player1">
            <button type="submit" class="btn-primary">Player 1 Won Point</button>
        </form>

        <form method="post" action="${pageContext.request.contextPath}/match-score">
            <input type="hidden" name="uuid" value="${param.uuid}">
            <input type="hidden" name="winner" value="player2">
            <button type="submit" class="btn-primary">Player 2 Won Point</button>
        </form>
    </div>
    <% } else { %>
    <div class="match-finished">
        <h2>Match Finished!</h2>
        <p>Winner: ${score.winner.name}</p>
        <a href="${pageContext.request.contextPath}/new-match" class="btn-primary">Start New Match</a>
    </div>
    <% } %>

    <div class="back-link">
        <a href="${pageContext.request.contextPath}/">Back to Home</a>
    </div>
</div>
</body>
</html>