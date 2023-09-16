package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloJpa");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 회원 저장
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);

            // 회원 수정
            Member findMember = em.find(Member.class, 1L); // 회원 조회
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());
            findMember.setName("helloB");

            // 회원 삭제
//            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);

            // 회원 조회 - JPQL
            List<Member> findMembers = em.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member m : findMembers) {
                System.out.println("m.getName() = " + m.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
