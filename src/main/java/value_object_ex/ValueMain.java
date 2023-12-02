package value_object_ex;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

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

            Member member = new Member();
            member.setName("member1");
            member.setAddress(new Address("homeCity", "street", "1000"));

            member.getFavoriteFoods().add("김치찌개");
            member.getFavoriteFoods().add("팟타이");
            member.getFavoriteFoods().add("파스타");

            member.getAddressHistory().add(new AddressEntity("workCity", "street2", "2000"));
            member.getAddressHistory().add(new AddressEntity("workCity2", "street5", "3000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=============== START ===============");
            Member findMember = em.find(Member.class, member.getId());

//            System.out.println("findMember.getAddressHistory().getClass() = " + findMember.getAddressHistory().getClass());
//            System.out.println("findMember.getFavoriteFoods().getClass() = " + findMember.getFavoriteFoods().getClass());

//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String food : favoriteFoods) {
//                System.out.println("food = " + food);
//            }

            // 김치찌개 제거, 한식 추가
//            findMember.getFavoriteFoods().remove("김치찌개");
//            findMember.getFavoriteFoods().add("한식");

            // 주소 컬렉션 값 변경
//            findMember.getAddressHistory().remove(new Address("workCity", "street2", "2000"));
//            findMember.getAddressHistory().add(new Address("newWorkCity", "street7", "7000"));

            tx.commit();
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

        /*int a = 10;
        int b = 10;

        System.out.println("(a==b) = " + (a==b));

        Address address1 = new Address("city", "street", "1000");
        Address address2 = new Address("city", "street", "1000");

        System.out.println("(address1==address2) = " + (address1==address2));
        System.out.println("(address1.equals(address2)) = " + (address1.equals(address2)));*/
    }
}
