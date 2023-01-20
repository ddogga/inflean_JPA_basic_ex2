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

            Member member = new Member();
            member.setUserName("member1");
            member.setAge(10);
            em.persist(member);

//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            List<Member> resultList = query.getResultList();
//
//            for (Member mbmer1 : resultList) {
//                System.out.println("mbmer1 = " + mbmer1);
//            }

            //값이 하나만 있는 경우
            TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = 10L", Member.class);

            Member result = query.getSingleResult();
            System.out.println("result = " + result);

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
