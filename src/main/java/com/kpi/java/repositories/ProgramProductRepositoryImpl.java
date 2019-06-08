package com.kpi.java.repositories;

import com.kpi.java.entities.ProgramProduct;
import org.hibernate.Session;

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
    public ProgramProduct findByNameAndVersion(String name, Long version) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProgramProduct findChild(Long id) {
        throw new UnsupportedOperationException();
    }
}
