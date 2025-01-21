package service;

import dao.MatchDAO;
import dao.PlayerDAO;
import entities.Match;
import entities.Player;

import java.util.Optional;

public class FinishedMatchesService {
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getOngoingMatchesService();
    private final MatchDAO matchDAO = new MatchDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();

    public void persist(CurrentMatch currentMatch) {
        Player firstPlayer = null;
        try {
            firstPlayer = playerDAO.create(currentMatch.getFirstPlayer());
        } catch (Exception e) {
            Optional<Player> player = playerDAO.getByName(currentMatch.getFirstPlayer().getName());
            if (player.isPresent()) {
                firstPlayer = player.get();
            }
        }


        Player secondPlayer = null;
        try {
            secondPlayer = playerDAO.create(currentMatch.getSecondPlayer());
        } catch (Exception e) {
            Optional<Player> player = playerDAO.getByName(currentMatch.getSecondPlayer().getName());
            if (player.isPresent()) {
                secondPlayer = player.get();
            }
        }


        Player winner;
        if (currentMatch.getWinner().getName().equalsIgnoreCase(firstPlayer.getName())) {
            winner = firstPlayer;
        } else {
            winner = secondPlayer;
        }

        matchDAO.create(new Match(firstPlayer, secondPlayer, winner));
    }

}
