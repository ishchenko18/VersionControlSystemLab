package com.kpi.java.repositories;

import com.kpi.java.configs.DataSourceBuilder;
import com.kpi.java.entities.ProgramProduct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProgramProductRepositoryImpl implements Repository<ProgramProduct> {

    private SessionFactory sessionFactory;

    public ProgramProductRepositoryImpl() {
        sessionFactory = DataSourceBuilder.buildEntityManager();
    }

    @Override
    public List<ProgramProduct> findAll() {
        Session session = sessionFactory.openSession();
        List<ProgramProduct> programProducts = session.createQuery("from ProgramProduct", ProgramProduct.class).list();
        session.close();

        return programProducts;
    }

    @Override
    public void saveOrUpdate(ProgramProduct programProduct) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            session.saveOrUpdate(programProduct);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw e;
        }
    }

    @Override
    public ProgramProduct findById(Long id) {
        Session session = sessionFactory.openSession();
        ProgramProduct programProduct = session.get(ProgramProduct.class, id);
        session.close();

        return programProduct;
    }

    @Override
    public void delete(Long id) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            ProgramProduct programProduct = session.get(ProgramProduct.class, id);
            session.delete(programProduct);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw e;
        }
    }
}
