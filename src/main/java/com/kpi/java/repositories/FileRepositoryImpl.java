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
    public void saveOrUpdate(File file) {
        throw new UnsupportedOperationException();
    }

    @Override
    public File findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            File programProduct = session.get(File.class, id);
            session.delete(programProduct);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}
