package service.newMatch;

import lombok.Getter;

public class SetScore extends Score<Integer> {
    @Getter
    private GameScore<?> currentGame;

    public SetScore() {
        this.currentGame = new GameRegularScore();
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    State pointWon(int playerNumber) {
        State gameState = currentGame.pointWon(playerNumber);
        if (gameState == State.PLAYER_ONE_WON) {
            return gameWon(playerNumber);
        }else if (gameState == State.PLAYER_TWO_WON) {
            return gameWon(playerNumber);
        }
        return State.ONGOING;
    }

    private State gameWon(int playerNumber) {
        setPlayerScores(playerNumber, getPlayerScores(playerNumber) +  1);
        this.currentGame = new GameRegularScore();

        if (getPlayerScores(playerNumber)== 6 || getPlayerScores(playerNumber)== 7) {

            if (getPlayerScores(playerNumber) - getOppositePlayerScores(playerNumber) > 1){
                if (playerNumber == 0) {
                    return State.PLAYER_ONE_WON;
                }
                if (playerNumber == 1) {
                    return State.PLAYER_TWO_WON;
                }
            }

            if(getPlayerScores(playerNumber) == 6 && getOppositePlayerScores(playerNumber) == 6){
                this.currentGame = new GameTieBreakScore();
                return State.ONGOING;
            }

            if (getPlayerScores(playerNumber) == 7 && getOppositePlayerScores(playerNumber) == 6){
                this.currentGame = new GameTieBreakScore();
                if (playerNumber == 0) {
                    return State.PLAYER_ONE_WON;
                }
                if (playerNumber == 1) {
                    return State.PLAYER_TWO_WON;
                }
            }
        }
        return State.ONGOING;
    }
}
