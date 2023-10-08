package jpashop;

import jpashop.domain.Member;
import jpashop.domain.Movie;
import jpashop.domain.Order;
import jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class ShopMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloJpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        try {
            /*// 객체지향과 거리가 먼 코드
            Order order = em.find(Order.class, 1L);
            Long memberId = order.getMemberId();

            Member member = em.find(Member.class, memberId);

            // 객체 지향적 코드
            Order order = em.find(Order.class, 1L);
            Member findMember = order.getMember();*/

            /*Order order = new Order();
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            em.persist(orderItem);*/

            /*
            상속관계 매핑
            Movie movie = new Movie();
            movie.setDirector("directorA");
            movie.setActor("actorA");
            movie.setName("Inception");
            movie.setPrice(20000);
            // item, movie 테이블에 insert
            // 두 칼럼은 동일한 id 를 가지며, movie 테이블이 외래키를 가짐

            em.persist(movie);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId()); // join
            System.out.println("findMovie = " + findMovie);*/

            Member member = new Member();
            member.setName("Kim");
            member.setCreatedBy("Lee");
            member.setCreatedAt(LocalDateTime.now());

            em.persist(member);

            em.flush();
            em.clear();

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
