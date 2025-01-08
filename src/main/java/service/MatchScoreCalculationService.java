package service;

import entities.MatchScore;

public class MatchScoreCalculationService {

    public void updateScore(MatchScore score, boolean player1WonPoint ) {
        if (score.isFinished()){
            return;
        }
        if (score.isTiebreak()){
            updateTiebreakScore(score, player1WonPoint);
        }else {
            updateRegularGameScore(score, player1WonPoint);
        }
    }

   private void updateRegularGameScore(MatchScore score, boolean player1WonPoint) {
        if (player1WonPoint){
            score.setCurrentGamePlayer1Points(score.getCurrentGamePlayer1Points() + 1);
        }else {
            score.setCurrentGamePlayer2Points(score.getCurrentGamePlayer2Points() + 1);
        }
        checkGameWin(score);
   }

    private void updateTiebreakScore(MatchScore score, boolean player1WonPoint) {
        if (player1WonPoint){
            score.setTiebreakPlayer1Points(score.getTiebreakPlayer1Points() + 1);
        }else {
            score.setTiebreakPlayer2Points(score.getTiebreakPlayer2Points() + 1);
        }
        checkTiebreakWin(score);
    }

    private void checkTiebreakWin(MatchScore score) {
        int player1Points = score.getTiebreakPlayer1Points();
        int player2Points = score.getTiebreakPlayer2Points();
        if ((player1Points >= 7 && player1Points >= player2Points + 2) ||
                (player2Points >= 7 && player2Points >= player1Points + 2)) {
            if ((player1Points > player2Points)){
                score.setCurrentSetPlayer1Games(score.getCurrentSetPlayer1Games() + 1);
                score.setPrayer1Sets(score.getPrayer1Sets() + 1);
            }
            else {
                score.setCurrentSetPlayer2Games(score.getCurrentSetPlayer2Games() + 1);
                score.setPrayer2Sets(score.getPrayer2Sets() + 1);
            }
            score.setTiebreakPlayer1Points(0);
            score.setTiebreakPlayer2Points(0);
            score.setTiebreak(false);
            checkMatchWin(score);
        }
    }
    private void checkGameWin(MatchScore score) {
        int player1Points = score.getCurrentGamePlayer1Points();
        int player2Points = score.getCurrentGamePlayer2Points();
        if ((player1Points >= 4  && player1Points >= player2Points + 2)||
        (player2Points >= 4  && player2Points >= player1Points + 2)) {
            if ((player1Points > player2Points)){
                score.setCurrentSetPlayer1Games(score.getCurrentSetPlayer1Games() + 1);
            }else {
                score.setCurrentSetPlayer2Games(score.getCurrentSetPlayer2Games() + 1);
            }
            score.setCurrentGamePlayer1Points(0);
            score.setCurrentGamePlayer2Points(0);
        }
    }

    private void checkSetProgress(MatchScore score) {
      int p1Games = score.getCurrentSetPlayer1Games();
      int p2Games = score.getCurrentSetPlayer2Games();

      if (p1Games == 6 && p2Games == 6){
          score.setTiebreak(true);
          return;
      }
      if ((p1Games >= 6 && p1Games >= p2Games + 2) ||
              (p2Games >= 6 && p2Games >= p1Games + 2)) {
          if (p1Games > p2Games){
              score.setPrayer1Sets(score.getPrayer1Sets() + 1);
          }else {
              score.setPrayer2Sets(score.getPrayer2Sets() + 1);
          }
          score.setCurrentSetPlayer1Games(0);
          score.setCurrentSetPlayer2Games(0);
          checkMatchWin(score);
      }
    }

    private void checkMatchWin(MatchScore score) {
        if (score.getPrayer1Sets() == 2 || score.getPrayer2Sets() == 2) {
            score.setFinished(true);
        }
    }
}
