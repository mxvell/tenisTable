import org.junit.jupiter.api.Test;
import service.newMatch.MatchScore;
import service.newMatch.State;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchScoreTest {

    @Test
    public void winMatch() {
        //match form 3 sets, need win 2 for victory
        MatchScore match = new MatchScore(2);

        // first player won 1st set (24 games), and match ONGOING
        for (int i = 0; i < 23; i++) {
            match.pointWon(0);
        }
        assertThat(match.pointWon(0)).isEqualTo(State.ONGOING);


        // first player won 2nd set (next 24 games), and first player win match
        for (int i = 0; i < 23; i++) {
            match.pointWon(0);
        }
        assertThat(match.pointWon(0)).isEqualTo(State.PLAYER_ONE_WON);

    }
}
