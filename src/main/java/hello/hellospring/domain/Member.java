package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa가 관리하는 entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name="name")
    private String name;

    //getter, setter 자동생성 단축키 : Alt + Insert
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
