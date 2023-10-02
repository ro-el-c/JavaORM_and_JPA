package mapping_ex;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MappingMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloJpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
//            member.setTeamId(team.getId()); // 객체지향스럽지 않은 부분 -> setTeam 이 되어야 하지 않을까!?
            em.persist(member); // 영속성 컨텍스트에 포함 -> em.flush() 로 DB 에 반영 가능 / em.clear() 로 영속성 컨텍스트 초기화 가능

            // 연관관계 수정
            Team teamB = new Team();
            teamB.setName("TeamB");
            em.persist(teamB);
            member.setTeam(teamB);

            Member findMember = em.find(Member.class, member.getId()); // 1차 캐시에서 가져옴
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam.getName() = " + findTeam.getName());
            /*Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId); // 객체지향적이지 못한 코드*/

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
