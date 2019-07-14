package net.DAO;

import net.model.Category;

import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@Repository
public class CategoryDao implements DAO<Category> {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(category);
    }

    public void update(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(category);
    }

    public void remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category category = (Category) session.load(Category.class, id);

        if (category != null) {
            session.delete(category);
        }
    }

    public Category getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Category) session.load(Category.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Category> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Category> categoryList = session.createQuery("FROM Category").list();

        return categoryList;
    }

    @SuppressWarnings("unchecked")
    public List<Category> getByQuery(String query) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Category> categoryList = session.createQuery(query).list();

        return categoryList;
    }

}
