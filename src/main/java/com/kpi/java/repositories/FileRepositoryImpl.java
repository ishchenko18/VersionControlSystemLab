package com.kpi.java.repositories;

import com.kpi.java.configs.DataSourceBuilder;
import com.kpi.java.entities.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FileRepositoryImpl implements Repository<File> {

    private SessionFactory sessionFactory;

    public FileRepositoryImpl() {
        sessionFactory = DataSourceBuilder.buildEntityManager();
    }

    @Override
    public List<File> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(File file) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("delete from File where id= :id");
            query.setParameter("id", id);

            query.executeUpdate();

            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        }
    }
}
