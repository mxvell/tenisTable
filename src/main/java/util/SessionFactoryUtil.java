package util;

import dao.MatchDAO;
import dao.PlayerDAO;
import entities.Match;
import entities.Player;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private static final MatchDAO matchDao = new MatchDAO();
    private static final PlayerDAO playerDao = new PlayerDAO();

    static {
        Player player1 = new Player("R. FEDERER");
        Player player2 = new Player("A. MURRAY");
        playerDao.create(player1);
        playerDao.create(player2);
        matchDao.create(new Match(player1, player2, player1));

        Player player3 = new Player("P. SAMPRAS");
        Player player4 = new Player("A. AGASSI");
        playerDao.create(player3);
        playerDao.create(player4);
        matchDao.create(new Match(player3, player4, player4));

        Player player5 = new Player("B. BORG");
        Player player6 = new Player("J. MCENROE");
        playerDao.create(player5);
        playerDao.create(player6);
        matchDao.create(new Match(player5, player6, player5));

        Player player7 = new Player("S. EDBERG");
        playerDao.create(player7);
        matchDao.create(new Match(player7, player5, player5));

        Player player8 = new Player("M. WILANDER");
        Player player9 = new Player("I. LENDL");
        playerDao.create(player8);
        playerDao.create(player9);
        matchDao.create(new Match(player8, player9, player9));

        Player player10 = new Player("J. CONNORS");
        playerDao.create(player10);
        matchDao.create(new Match(player3, player10, player3));
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Math.class);
                configuration.addAnnotatedClass(Player.class);
                sessionFactory = configuration.buildSessionFactory();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}