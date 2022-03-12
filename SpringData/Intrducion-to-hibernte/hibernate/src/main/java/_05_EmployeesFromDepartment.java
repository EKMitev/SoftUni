import entities.Employee;

import javax.persistence.*;
import java.util.List;

public class _05_EmployeesFromDepartment{
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT e FROM Employee e" +
                " Where e.department.name = 'Research and Development'" +
                " ORDER BY e.salary ASC, e.id ASC", Employee.class);

        List<Employee> resultList = query.getResultList();

        resultList.forEach(e -> System.out.printf("%s %s from %s - $%.2f%n",
                e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
