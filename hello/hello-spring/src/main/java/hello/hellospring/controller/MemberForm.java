package hello.hellospring.controller;

//여기에 적힌 name과 createMemberForm여기에 적혀있는 name과 매칭이 되면서 값이 전달되게 됩니다.
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
