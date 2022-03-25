import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class _11_FindBy1stName {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        String param = new Scanner(System.in).nextLine();

        List<Employee> resultList = entityManager.createQuery("FROM Employee e" +
                        " WHERE e.firstName LIKE :param", Employee.class)
                .setParameter("param", param + "%")
                .getResultList();

        resultList.forEach(e -> System.out.printf("%s %s - $%.2f%n", e.getFirstName(), e.getLastName(), e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
