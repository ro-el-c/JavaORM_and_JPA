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
            // 비영속
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            // 영속
            em.persist(member); // 1차 캐시에 저장

            // 회원 수정
            // 회원 조회 - select Query X
            // -> 1차 캐시에서 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());
            findMember.setName("helloB");

            // 회원 조회 - select Query O
            // -> 1차 캐시에 없음
            // -> DB 에서 조회
            Member member2 = em.find(Member.class, 2L);
            System.out.println("member2.getName() = " + member2.getName());

            // 영속 엔티티의 동일성 보장
            Member member22 = em.find(Member.class, 2L);
            System.out.println("result = " + (member2 == member22));

            // 회원 삭제
//            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);

            // 회원 조회 - JPQL
            List<Member> findMembers = em.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member m : findMembers) {
                System.out.println("m.getName() = " + m.getName());
            }

            // 변경 감지
            Member member150 = em.find(Member.class, 150L); // DB 에서 조회 -> 영속 상태로 등록
            member150.setName("150Name");
            // 실행 후 DB 확인 -> 변경 감지 및 반영

            // 준영속 상태
            em.detach(member150);
            // 영속 상태였던 member150 을 준영속 상태로 만듦
            // 더 이상 JPA 가 관리하지 않음
            // 트랜잭션 커밋할 때 아무 일도 일어나지 않음

            // 트랜잭션 - 쓰기 지연
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
