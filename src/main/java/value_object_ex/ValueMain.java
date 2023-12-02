package value_object_ex;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ValueMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloJpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 값 타입
            /*Member member = new Member();
            member.setName("값 타입");
            member.setAddress(new Address("city", "street", "zipcode"));
            member.setWorkPeriod(new Period());*/

            /*Address address = new Address("city", "street", "10000");

            Member member = new Member();
            member.setName("member1");
            member.setAddress(address);
            em.persist(member);

            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setName("member2");
            member2.setAddress(address);
            em.persist(member2);

            member.getAddress().setCity("newCity");*/

            tx.commit();
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();


        //-------------------------------
        int a = 10;
        int b = 10;

        System.out.println("(a==b) = " + (a==b));

        Address address1 = new Address("city", "street", "1000");
        Address address2 = new Address("city", "street", "1000");

        System.out.println("(address1==address2) = " + (address1==address2));
        System.out.println("(address1.equals(address2)) = " + (address1.equals(address2)));
    }
}
