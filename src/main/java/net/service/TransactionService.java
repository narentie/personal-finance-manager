package net.service;

import net.DAO.TransactionDao;
import net.model.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("transactionService")
public class TransactionService implements ItemService<Transaction> {

    private TransactionDao transactionDao;

    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Transactional
    public void add(Transaction transaction) {
        this.transactionDao.add(transaction);
    }

    @Transactional
    public void update(Transaction transaction) {
        this.transactionDao.update(transaction);
    }

    @Transactional
    public void remove(int id) {
        this.transactionDao.remove(id);
    }

    @Transactional
    public Transaction getById(int id) {
        return this.transactionDao.getById(id);
    }

    @Transactional
    public List<Transaction> list() {
        return this.transactionDao.getAllJoin();
    }

    public List<Transaction> listByQuery(String query) {
        return this.transactionDao.getByQuery(query);
    }

}
