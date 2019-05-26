package com.kpi.java.repositories;

import com.kpi.java.configs.DataSourceBuilder;
import com.kpi.java.entities.ProgramProduct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProgramProductRepositoryImpl implements ProgramProductRepository {

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


}
