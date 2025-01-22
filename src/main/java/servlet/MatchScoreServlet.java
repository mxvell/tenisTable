package servlet;

import entities.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CurrentMatch;
import service.FinishedMatchesService;
import service.OngoingMatchesService;
import service.newMatch.State;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getOngoingMatchesService();
    private final FinishedMatchesService finishedMatchesService = new FinishedMatchesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentMatch currentMatch = ongoingMatchesService.getCurrentMatch(getUuid(req));
        req.setAttribute("currentMatch", currentMatch);
        req.getRequestDispatcher("view/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        String win1 = req.getParameter("player-1");
        String win2 = req.getParameter("player-2");
        UUID uuidRow = UUID.fromString(uuid);

        CurrentMatch currentMatch = ongoingMatchesService.getCurrentMatch(uuidRow);

        if (win1 != null) {
            if (currentMatch.getMatchScore().pointWon(0) == State.PLAYER_ONE_WON){
                currentMatch.setWinner(currentMatch.getFirstPlayer());
                finishedMatchesService.persist(currentMatch);
            }
        }else if (win2 != null) {
            if (currentMatch.getMatchScore().pointWon(1) == State.PLAYER_TWO_WON){
                currentMatch.setWinner(currentMatch.getSecondPlayer());
                finishedMatchesService.persist(currentMatch);

            }
        }
        resp.sendRedirect("match-score?uuid=" + uuid);
    }

    private static UUID getUuid(HttpServletRequest req) {
        return UUID.fromString(req.getParameter("uuid"));
    }
}


