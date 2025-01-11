package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            // Створюємо SessionFactory з конфігурації
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml") // читаємо налаштування
                    .buildSessionFactory();

            // Ініціалізуємо базу даних (створюємо таблиці)
            initDatabase();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    private static void initDatabase() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Тут можна додати початкові дані якщо потрібно
            // Наприклад, створити тестових гравців:
            /*
            session.save(new Player("Roger Federer"));
            session.save(new Player("Rafael Nadal"));
            */

            session.getTransaction().commit();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Закриваємо кеші та connection pool
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}