package service.newMatch;

public class GameTieBreakScore extends GameScore<Integer> {
    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    State pointWon(int playerNumber) {
        setPlayerScores(playerNumber, getPlayerScores(playerNumber) + 1);

        if (getPlayerScores(playerNumber) > 6 && (getPlayerScores(playerNumber) - getOppositePlayerScores(playerNumber)) > 1) {
            if (playerNumber == 0) {
                return State.PLAYER_ONE_WON;
            }
            if (playerNumber == 1) {
                return State.PLAYER_TWO_WON;
            }
        }

        return State.ONGOING;
    }
}

