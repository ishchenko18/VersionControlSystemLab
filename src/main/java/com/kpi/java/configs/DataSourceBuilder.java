package com.kpi.java.configs;

import com.kpi.java.entities.File;
import com.kpi.java.entities.ProgramProduct;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class DataSourceBuilder {

    private static Properties properties;
    private static volatile SessionFactory sessionFactory;

    static {
        properties = new Properties();
        InputStream inputStream = DataSourceBuilder.class.getClassLoader().getResourceAsStream("hibernate.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Error in loading hibernate configs.");
        }
    }

    public static SessionFactory buildEntityManager() {
        if (sessionFactory == null) {
            synchronized (SessionFactory.class) {
                if (sessionFactory == null) {
                    sessionFactory = new Configuration().addAnnotatedClass(ProgramProduct.class)
                            .addAnnotatedClass(File.class)
                            .addProperties(properties)
                            .configure()
                            .buildSessionFactory();
                }
            }
        }

        return sessionFactory;
    }
}
