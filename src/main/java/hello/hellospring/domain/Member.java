package hello.hellospring.domain;

public class Member {
    private Long id;
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
