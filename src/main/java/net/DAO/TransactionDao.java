package net.DAO;

import net.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionDao implements DAO<Transaction> {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Transaction transaction) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(transaction);
    }

    public void update(Transaction transaction) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(transaction);
    }

    public void remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction transaction = (Transaction) session.load(Transaction.class, id);
        if (transaction != null) {
            session.delete(transaction);
        }
    }

    public Transaction getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Transaction) session.load(Transaction.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("FROM Transaction").list();
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> getByQuery(String query) {
        Session session = this.sessionFactory.getCurrentSession();

        return session.createQuery(query).list();
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> getAllJoin() {
        Session session = this.sessionFactory.getCurrentSession();

        return session.createQuery("SELECT t FROM Transaction t inner join t.category").list();
    }

}
