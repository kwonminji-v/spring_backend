package Spring.core.singleton;

public class SingletonService {

    //자기 자신을 내부에 static private으로 선언하고 가지고 있게 됨
    private static final SingletonService instance = new SingletonService();




}
