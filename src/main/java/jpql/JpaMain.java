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

            em.flush();
            em.clear();


            // Object[] 타입으로 변환
            List resultList = em.createQuery("select m.username, m.age from Member m")
                    .getResultList();

            Object o = resultList.get(0);
            Object[] result = (Object[]) o;
            System.out.println("username = " + result[0]);
            System.out.println("age = " + result[1]);

            // Object[] 타입으로 조회
            List<Object[]> resultList1= em.createQuery("select m.username, m.age from Member m")
                    .getResultList();

            Object[] result1 = resultList1.get(0);
            System.out.println("username = " + result1[0]);
            System.out.println("age = " + result1[1]);


            //new 명령어로 조회
            List<MemberDTO> resultList2= em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m, MemberDTO.class")
                    .getResultList();

            MemberDTO memberDTO = resultList2.get(0);
            System.out.println("memberDTO = " + memberDTO.getUsername());
            System.out.println("memberDTO = " + memberDTO.getAge());


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
