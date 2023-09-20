package jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ShopMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloJpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        try {
            tx.commit();
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
