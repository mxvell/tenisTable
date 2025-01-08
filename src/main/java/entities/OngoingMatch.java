package entities;

import lombok.Getter;


@Getter
public class OngoingMatch {
    private final Player player1;
    private final Player player2;
    private final MatchScore score;

    public OngoingMatch(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = new MatchScore();
    }
}

