package entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchScore {
    private int prayer1Sets = 0;
    private int prayer2Sets = 0;
    private int currentSetPlayer1Games = 0;
    private int currentSetPlayer2Games = 0;
    private int currentGamePlayer1Points = 0;
    private int currentGamePlayer2Points = 0;
    private boolean isTiebreak = false;
    private int tiebreakPlayer1Points = 0;
    private int tiebreakPlayer2Points = 0;
    private boolean isFinished = false;
    private Player winner = null;

    public String getCurrentGameScore(){
        if(isTiebreak){
            return  tiebreakPlayer1Points + "/" + tiebreakPlayer2Points;
        }
        String [] pointNames = { "0", "15", "30" , "40"};
        if (currentGamePlayer1Points >= 3 && currentGamePlayer2Points >= 3){
            if (currentGamePlayer1Points == currentGamePlayer2Points){
                return "Equals";
            } else if (currentGamePlayer1Points > currentGamePlayer2Points) {
                return "Higher (Player1)";
            }else {
                return "Lower (Player2)";
            }
        }
        if (currentGamePlayer1Points < 4 && currentGamePlayer2Points < 4){
            return pointNames[currentGamePlayer1Points] + "-" + pointNames[currentGamePlayer2Points];
        }
        return "Error";
    }
}
