package jpql;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long Id;
    private String userName;
    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
