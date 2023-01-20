package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setUserName("관리자1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUserName("관리자1");
            member2.setTeam(team);
            em.persist(member2);


            em.flush();
            em.clear();

            //상태 필드
            String query = "select m.username From Member m ";
            //단일 값 연관 경로
            String query2 = "select m.team.name From Member m ";
            //컬렉션 값 연관 경로
            String query3 = "select t.members.size From Team t ";

            Integer result = em.createQuery(query3, Integer.class)
                    .getSingleResult();

            System.out.println("result = " + result);
            
//            List<String> result = em.createQuery(query, String.class)
//                            .getResultList();
//
//            for (String s: result) {
//                System.out.println("s = " + s);
//            }

           tx.commit();


        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();

    }

}
