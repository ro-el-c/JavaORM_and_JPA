package jpql_ex;

import jpql_ex.domain.Member;
import jpql_ex.domain.Team;
import jpql_ex.dto.MemberDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JPQLMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloJpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            for (int i = 1; i <= 3; i++) {
                Team team = new Team();
                team.setName("team" + i);
                em.persist(team);

                Member member = new Member();
                if (i == 2) {
                    member.setName("관리자");
                }
                if (i > 2) {
                    member.setName("member" + i);
                }
                member.setAge(i*3);
                member.setTeam(team);

                em.persist(member);
            }

            em.flush();
            em.clear();

            /*
            //Query 타입 조회
            List resultList = em.createQuery("select m.name, m.age from Member m")
                    .getResultList();
            Object o = resultList.get(0);
            Object[] result = (Object[]) o;
            System.out.println("username = " + result[0]);
            System.out.println("age = " + result[1]);

            //Object[] 타입 조회
            List<Object[]> resultList = em.createQuery("select m.name, m.age from Member m")
							                .getResultList();
            Object[] result = resultList.get(0);
            System.out.println("username = " + result[0]);
            System.out.println("age = " + result[1]);

            //new DTO
            List<MemberDTO> resultList = em.createQuery("select new jpql_ex.dto.MemberDTO(m.name, m.age) from Member m", MemberDTO.class)
                    .getResultList();

            MemberDTO memberDTO = resultList.get(0);
            System.out.println("username = " + memberDTO.getName());
            System.out.println("age = " + memberDTO.getAge());

            //페이징
            List<Member> result = em.createQuery("select m from Member m order by m.age desc ", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("result.size = " + result.size());
            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }

            //join - on 절
            String query = "select m from Member m left join m.team t on t.name='team3'";
            List<Member> result = em.createQuery(query, Member.class).getResultList();
            System.out.println("result size = " + result.size());

            //서브 쿼리 ex
            //teamA 소속인 회원
            String subQuery1 = "select m from Member m" +
                    "where exists (select t from m.team t where t.name='teamA')";
            //전체 상품 각각의 재고보다 주문량이 많은 주문들
            String subQuery2 = "select o from Order o" +
                    "where o.orderAmount > ALL(select p.stockAmount from Product p)";
            //어떤 티이든 팀에 소속된 회원
            String subQuery3 = "select m from Member m" +
                    "where m.team = ANY(select t from Team t)";

            //조건식 - coalesce
            //ex. m.name이 null 이면 '이름 없는 회원' 반환, null이 아니면 m.name 반환
            List<String> coalesceResult = em.createQuery("select coalesce(m.name, '이름 없는 회원') from Member m", String.class)
                    .getResultList();
            System.out.println("coalesceResult = " + coalesceResult.get(0));

            //조건식 - nullif
            //ex. 사용자 이름이 ‘관리자’면 null을 반환, 나머지는 본인 이름 반환
            List<String> nullifResult = em.createQuery("select nullif(m.name, '관리자') from Member m", String.class)
                    .getResultList();
            System.out.println("nullifResult 이름이 관리자인 경우 = " + nullifResult.get(1));
            System.out.println("nullifResult 이름이 관리자가 아닌 경우 = " + nullifResult.get(2));
            */

            //member 조회 후, team의 이름 참조시 지연 로딩
            String query = "select m from Member m";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getName() + ", " + member.getTeam().getName());
            }


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
