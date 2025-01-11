package dao;

import entities.Match;
import entities.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class MatchDAO  {

    public Match save(Match match) {
        Transaction transaction = null;
        try(Session session = (Session) HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(match);
            transaction.commit();
            return match;
         //TODO CREATE CASTOM EXCEPTION
        }catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Match> findByPlayer(Player player, int page, int pageSize) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Match> query = session.createQuery("FROM Match  WHERE player1 = :player OR player2 = :player ORDER BY id DESC ", Match.class);
            query.setParameter("player", player);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }
    public Long countMatchesByPlayer(Player player) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT (*) FROM Match  WHERE player1 = :player OR player2 = :player", Long.class);
            query.setParameter("player", player);
            return query.uniqueResult();
        }
    }
    public List<Match> findAll(int page, int pageSize) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Match> query = session.createQuery(
                    "FROM Match ORDER BY id DESC", Match.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }

    public Long countAllMatches() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(*) FROM Match", Long.class);
            return query.uniqueResult();
        }
    }
}
