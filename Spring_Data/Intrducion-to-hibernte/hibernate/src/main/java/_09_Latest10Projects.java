import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;


public class _09_Latest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery("FROM Project p " +
                        "ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultStream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("Project name: %s%n" +
                        "   Project Description: %s%n" +
                        "   Project start date: %s%n" +
                        "   Project end date: %s%n", p.getName(), p.getDescription(), p.getStartDate(), p.getEndDate()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
