import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class _10_IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> resultList = entityManager.createQuery("FROM Employee e" +
                        " WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList();

        resultList
                .stream()
                .peek(e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))))
                .forEach(e -> System.out.printf("%s %s - $%.2f%n", e.getFirstName(), e.getLastName(), e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
