import entities.Town;

import javax.persistence.*;
import java.util.List;

public class _02_ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        Query query = em
                .createQuery("SELECT t FROM Town t", Town.class);
        List<Town> resultList = query.getResultList();

        for (Town town : resultList) {
            String name = town.getName();

            if (name.length() <= 5){
                town.setName(name.toUpperCase());
                em.persist(town);
            }
        }

        em.getTransaction().commit();
        em.close();
    }
}
