package service.newMatch;

public class GameRegularScore extends GameScore<GameRegularPlayerPoints> {
    @Override
    protected GameRegularPlayerPoints getZeroScore() {
        return GameRegularPlayerPoints.ZERO;
    }

    @Override
    State pointWon(int playerNumber) {
        GameRegularPlayerPoints playerScore = getPlayerScores(playerNumber);
        if (playerScore.ordinal() <= GameRegularPlayerPoints.THIRTY.ordinal()) {
            setPlayerScores(playerNumber, playerScore.next());
        } else if (playerScore == GameRegularPlayerPoints.FORTY) {
            GameRegularPlayerPoints opponentPlayerScore = getOppositePlayerScores(playerNumber);
            if (opponentPlayerScore == GameRegularPlayerPoints.ADVANTAGE) {
                setPlayerScores(playerNumber, GameRegularPlayerPoints.FORTY);
            } else if (opponentPlayerScore == GameRegularPlayerPoints.FORTY) {
                setPlayerScores(playerNumber, GameRegularPlayerPoints.ADVANTAGE);

            } else {
                if (playerNumber == 0) return State.PLAYER_ONE_WON;
                return State.PLAYER_TWO_WON;
            }
        } else if (playerScore == GameRegularPlayerPoints.ADVANTAGE) {
            if (playerNumber == 0) return State.PLAYER_ONE_WON;
            return State.PLAYER_TWO_WON;
        } else {
            throw new IllegalStateException("Can't call point won an ADVANTAGE");
        }
        return State.ONGOING;
    }
}
