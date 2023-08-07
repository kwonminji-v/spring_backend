package Spring.core.singleton;

public class SingletonService {

    //자기 자신을 내부에 static private으로 선언하고 가지고 있게 됨
    //(클래스 레벨에 올라가게 되면서 딱 하나만 존재)
    private static final SingletonService instance = new SingletonService();

    //JVM이 실행될 때, 바로 싱글톤 서비스의 스태틱 영역에 자기 자신의 객체를 생성하여 인스턴스를 담음
    public static SingletonService getInstance() {
        return instance;
    }


    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }


}
