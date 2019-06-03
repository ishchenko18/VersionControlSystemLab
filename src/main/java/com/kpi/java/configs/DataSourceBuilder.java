package com.kpi.java.configs;

import com.kpi.java.entities.File;
import com.kpi.java.entities.ProgramProduct;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataSourceBuilder {

    private static volatile SessionFactory sessionFactory;


    public static SessionFactory buildEntityManager() {
        if (sessionFactory == null) {
            synchronized (SessionFactory.class) {
                if (sessionFactory == null) {
                    Configuration configuration = new Configuration()
                            .addAnnotatedClass(ProgramProduct.class)
                            .addAnnotatedClass(File.class)
                            .configure();

                    sessionFactory = configuration.buildSessionFactory();
                }
            }
        }

        return sessionFactory;
    }
}
