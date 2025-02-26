package servlet;

import dao.MatchDAO;
import entities.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private final MatchDAO matchesDao = new MatchDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filterName = req.getParameter("filter_by_player_name");
        long page;
        try {
            page = Long.parseLong(req.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 0;
        }
        long pageSize = 5;

        long totalItems = 0;
        List<Match> matches;
        if (filterName == null || filterName.trim().isEmpty()) {
            matches = matchesDao.getAllPagination((int) pageSize, (int) (page * pageSize));
            totalItems = matchesDao.getAllUnique();
        } else {
            matches = matchesDao.getByPlayerNamePagination(filterName, (int) pageSize, (int) (page * pageSize));
            totalItems = matchesDao.getByPlayerNameUnique(filterName);
        }

        long totalPages = (totalItems / pageSize) + 1;

        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("matches", matches);
        req.setAttribute("totalItems", totalItems);
        req.getRequestDispatcher("/view/matches.jsp").forward(req, resp);
    }
}
