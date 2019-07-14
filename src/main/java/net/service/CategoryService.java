package net.service;

import net.model.Category;
import net.DAO.CategoryDao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
public class CategoryService implements ItemService<Category> {

    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional
    public void add(Category category) {
        this.categoryDao.add(category);
    }

    @Transactional
    public void update(Category category) {
        this.categoryDao.update(category);
    }

    @Transactional
    public void remove(int id) {
        this.categoryDao.remove(id);
    }

    @Transactional
    public Category getById(int id) {
        return this.categoryDao.getById(id);
    }

    @Transactional
    public List<Category> list() {
        return this.categoryDao.getAll();
    }

    public List<Category> listByQuery(String query) {
        return this.categoryDao.getByQuery(query);
    }
}
