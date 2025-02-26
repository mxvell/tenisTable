import org.junit.jupiter.api.Test;
import service.newMatch.SetScore;
import service.newMatch.State;

import static org.assertj.core.api.Assertions.assertThat;

public class SetScoreTest {
    @Test
    public void TwentyFourPointsWin() {
        SetScore setScore = new SetScore();

        // win 23 points without win Set
        for (int j = 1; j < 24; j++) {
            assertThat(setScore.pointWon(0)).isEqualTo(State.ONGOING);
        }

        // won 24th point
        assertThat(setScore.pointWon(0)).isEqualTo(State.PLAYER_ONE_WON);
    }
    @Test
    public void StartGameTieBreakAfterSixSixInSet_AndWonSet() {

        SetScore score = new SetScore();

        // score in set 6:6, that must start GameTieBreak
        score.setPlayerScores(0, 6);
        for (int i = 0; i < 23; i++) {
            score.pointWon(1);
        }

        assertThat(score.pointWon(1)).isEqualTo(State.ONGOING);


        // 13th game must be GameTieBreak
        assertThat(score.getPlayerScores(0).getClass()).isEqualTo(Integer.class);

        // check winning after 7 points won
        for (int i = 0; i < 6; i++) {
            score.pointWon(0);
        }
        assertThat(score.pointWon(0)).isEqualTo(State.PLAYER_ONE_WON);
        }
}
