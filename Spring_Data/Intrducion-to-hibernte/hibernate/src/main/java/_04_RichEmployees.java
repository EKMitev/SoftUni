import entities.Employee;

import javax.persistence.*;
import java.util.List;

public class _04_RichEmployees {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager
                .createQuery("SELECT e.firstName FROM Employee e" +
                        " WHERE e.salary > 50000", String.class);
        List<String> resultList = query.getResultList();

        resultList.forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
