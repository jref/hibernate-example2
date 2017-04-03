package ua.codespace.hibaranate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Book("Harry Potter", "G.K.Rowling"));
        session.save(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        session.getTransaction().commit();


        Book book = session.get(Book.class, 1);
        System.out.println(book);
        session.close();
        sessionFactory.close();
    }

    private static SessionFactory getSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        return sessionFactory;
    }
}
