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

            Member member = new Member();
            member.setUserName("member1");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            member.setTeam(team);

            em.persist(member);


            em.flush();
            em.clear();

            // enum 타입을 조건으로 검색 -> 아래와 같이 enum명과 패키지까지 다 적어 줘야 함.
            String query = "select m from Member m " +
                    "where m.type = :userType";

            List<Object[]> result = em.createQuery(query)
                    .setParameter("userType", MemberType.ADMIN)
                    .getResultList();

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
