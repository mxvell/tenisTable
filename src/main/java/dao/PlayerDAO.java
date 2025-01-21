package dao;

import entities.Match;
import entities.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.SessionFactoryUtil;

import java.util.List;
import java.util.Optional;


public class PlayerDAO implements Dao<Player> {
    @Override
    public Player create(Player player) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
        } catch (Exception e) {
            if (transaction == null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return player;
    }

    @Override
    public Optional<Player> getById(int id) {
        Player player = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            player = session.get(Player.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(player);
    }

    @Override
    public List<Player> getAll() {
        List<Player> players = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            players = session.createQuery("from Player order by id DESC ", Player.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public Optional<Player> getByName(String name) {
        Player player = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Player where name = :name", Player.class);
            query.setParameter("name", name);
            player = (Player) query.getSingleResult();
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(player);
    }

}
