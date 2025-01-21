package service.newMatch;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class MatchScore extends Score<Integer> {

    private final Map<Integer, List<Integer>> gameResultsInSet;
    @Getter
    private SetScore currentSet;
    private final int setsForWin;
    @Getter
    private int server;

    public MatchScore(int setsForWin) {
        this.gameResultsInSet = new HashMap<>();
        this.setsForWin = setsForWin;
        this.currentSet = new SetScore();
        this.server = new Random().nextInt(2);
    }


    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    State pointWon(int playerNumber) {
        State setState = currentSet.pointWon(playerNumber);

        if (setState == State.PLAYER_ONE_WON) {
            return setWon(playerNumber);
        }
        if (setState == State.PLAYER_TWO_WON) {
            return setWon(playerNumber);
        }

        return State.ONGOING;
    }

    private State setWon(int playerNumber) {
        setPlayerScores(playerNumber, getPlayerScores(playerNumber) + 1);
        List<Integer> gameScore = new ArrayList<>();
        gameScore.add(currentSet.getPlayerScores(0));
        gameScore.add(currentSet.getPlayerScores(1));
        gameResultsInSet.put((getPlayerScores(0) + getPlayerScores(1)), gameScore);

        if (getPlayerScores(playerNumber) == setsForWin) {
            if (playerNumber == 0) {
                return State.PLAYER_ONE_WON;
            }
            if (playerNumber == 1) {
                return State.PLAYER_TWO_WON;
            }
        }
        this.currentSet = new SetScore();
        return State.ONGOING;
    }

    public Integer getGameResultsInSet(int setNumber, int playerNumber) {
        try {
            return gameResultsInSet.get(setNumber).get(playerNumber);
        } catch (NullPointerException e) {
            return -1;
        }
    }
}
