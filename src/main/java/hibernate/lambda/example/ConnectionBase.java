package hibernate.lambda.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;

public abstract class ConnectionBase {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public ConnectionBase() {

        HashMap<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        properties.put("javax.persistence.jdbc.url", System.getenv("connection"));
        properties.put("javax.persistence.jdbc.user", System.getenv("username"));
        properties.put("javax.persistence.jdbc.password", System.getenv("password"));

        this.entityManagerFactory = Persistence.
                createEntityManagerFactory("hibernate.lambda.example.model", properties);

        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
