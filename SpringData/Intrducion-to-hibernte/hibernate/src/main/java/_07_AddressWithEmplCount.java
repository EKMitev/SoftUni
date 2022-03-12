import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _07_AddressWithEmplCount {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager
                .createQuery("FROM Address a" +
                                " ORDER BY a.employees.size DESC",
                        Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(a -> System.out.println(a.getText()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
