package config;

import model.TableOne;
import model.TableTwo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    static String URL = "jdbc:mysql://localhost:3306/spring_h";

    public static SessionFactory sessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        configuration.setProperty("hibernate.connection.url", URL);
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "shema1");
        configuration.setProperty("hibernate.connection.driver", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.isolation", "4");
        configuration.addAnnotatedClass(TableOne.class);
        configuration.addAnnotatedClass(TableTwo.class);
        return configuration.buildSessionFactory();
    }
}
