package com.kpi.java.repositories;

import com.kpi.java.entities.File;
import org.hibernate.Session;
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
    public File findByNameAndVersion(String name, Long version) {
        Session session = sessionFactory.openSession();

        Query<File> query = session.createQuery("from File where name = :name and version = :version", File.class);
        query.setParameter("name", name);
        query.setParameter("version", version);

        File file = query.uniqueResult();

        session.close();

        return file;
    }

    @Override
    public File findChild(Long id) {
        Session session = sessionFactory.openSession();

        Query<File> query = session.createQuery("from File where previous_file_fk = :previous", File.class);
        query.setParameter("previous", id);

        File file = query.uniqueResult();

        session.close();

        return file;
    }
}
