package com.kpi.java.repositories;

import com.kpi.java.entities.File;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FileRepositoryImpl extends RepositoryAbstract<File> {

    @Override
    public List<File> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public File findById(Long id) {
        Session session = sessionFactory.openSession();
        File file = session.get(File.class, id);
        session.close();

        return file;
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

    @Override
    public File findByNameAndVersion(String name, Long version) {
        Session session = sessionFactory.openSession();

        Query<File> query = session.createQuery("from File where name = :name and version = :version", File.class);
        query.setParameter("name", name);
        query.setParameter("version", version);

        File file = query.uniqueResult();

        session.close();

        return file;
    }
}
