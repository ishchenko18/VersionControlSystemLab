package com.kpi.java.repositories;

import com.kpi.java.configs.DataSourceBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;

public abstract class RepositoryAbstract<T> implements Repository<T> {

    SessionFactory sessionFactory;
    private Class<T> persistentClass;

    RepositoryAbstract() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        sessionFactory = DataSourceBuilder.buildEntityManager();
    }

    @Override
    public void saveOrUpdate(T t) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            session.saveOrUpdate(t);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw e;
        }
    }

    @Override
    public T findById(Long id) {
        Session session = sessionFactory.openSession();
        T file = session.get(persistentClass, id);
        session.close();

        return file;
    }
}
