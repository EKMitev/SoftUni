import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _12_MaxSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> resultList = entityManager
                .createQuery("FROM  Employee e " +
                        "WHERE e.salary NOT BETWEEN 30000 AND 70000 " +
                        "GROUP BY e.department.id ", Employee.class)
                .getResultList();

        resultList.forEach(e -> System.out.printf("%s%n%.2f%n", e.getDepartment().getName(), e.getSalary().doubleValue()));


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
