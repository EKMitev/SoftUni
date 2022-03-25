import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _06_Address {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Address address = new Address();
        address.setText("Vitoshka 15");
        entityManager.persist(address);

        String lastName = new Scanner(System.in).nextLine();

        entityManager.createQuery("UPDATE Employee e " +
                "SET e.address = :address " +
                "WHERE e.lastName = :last_name")
                .setParameter("address", address)
                .setParameter("last_name", lastName)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
