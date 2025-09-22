package lk.ijse.drivingschool.config;

import lk.ijse.drivingschool.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        try {
            // Load hibernate.properties from classpath
            Properties properties = new Properties();
            try (InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("hibernate.properties")) {

                if (inputStream == null) {
                    throw new RuntimeException("hibernate.properties not found in classpath");
                }

                properties.load(inputStream);
            }

            Configuration configuration = new Configuration();
            configuration.addProperties(properties);

            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Instructor.class);
            configuration.addAnnotatedClass(Payment.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Lesson.class);

            sessionFactory = configuration.buildSessionFactory();

        } catch (IOException e) {
            throw new RuntimeException("Failed to load hibernate.properties", e);
        }
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}