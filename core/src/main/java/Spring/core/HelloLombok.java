package Spring.core;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Getter, Setter 어노테이션 사용
@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    //Getter , Setter를 직접 생성하여 작성하는 것이 아닌 롬복이 있다면 어노테이션을 이용하여
    //자동으로 사용할 수 있도록 생성해준다고 생각하면 됨

    public static void main(String[] args) {

        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("롬복으로 불러온 setName");
        
        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLombok = " + helloLombok);
        
    }
}
