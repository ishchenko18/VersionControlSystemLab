package com.kpi.java.repositories;

import com.kpi.java.entities.ProgramProduct;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProgramProductRepositoryImpl extends RepositoryAbstract<ProgramProduct> {

    @Override
    public List<ProgramProduct> findAll() {
        Session session = sessionFactory.openSession();
        List<ProgramProduct> programProducts = session.createQuery("from ProgramProduct", ProgramProduct.class).list();
        session.close();

        return programProducts;
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

    @Override
    public ProgramProduct findByNameAndVersion(String name, Long version) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProgramProduct findChild(Long id) {
        throw new UnsupportedOperationException();
    }
}
