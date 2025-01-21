package service.newMatch;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> {
    private final List<T> playerScores = new ArrayList<T>();

    protected abstract T getZeroScore();

    public Score() {
        playerScores.add(getZeroScore());
        playerScores.add(getZeroScore());
    }
    public T getPlayerScores(int playerNumber) {
        return playerScores.get(playerNumber);
    }

    public T getOppositePlayerScores(int playerNumber) {
        if (playerNumber == 0) return playerScores.get(1);
        return playerScores.get(playerNumber);
    }

    public void setPlayerScores(int playerNumber, T playerScore) {
        playerScores.set(playerNumber, playerScore);
    }

    public void setOppositePlayerScores(int playerNumber, T playerScore) {
        if (playerNumber == 0) playerScores.set(1, playerScore);
        else playerScores.set(0, playerScore);
    }

    abstract State pointWon (int playerNumber);
}
