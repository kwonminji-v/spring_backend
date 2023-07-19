package Spring.core.lifecycle;

public class NetworkClient{

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기 연결 메세지");
        // 생성 시 생성자가 호출되며 , 연결(connect())이 실행되고 call(message)가 전달됩니다.
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스를 시작할 때 호출하는 메서드 작성 (시작 시 연결 , 실제 네트워크가 연결될 때지만 여기선 텍스트만 출력)
    public void connect() {
        System.out.println("connect : " + url);
    }

    //네트워크가 연결됐다는 가정하에 메세지를 호출
    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    //서비스 종료 시 호출될 메서드 (안전한 서비스 종료)
    public void disconnect() {
        System.out.println("disconnect 호출 시 close : " + url);
    }

    //싱글톤 빈이기 때문에 스프링 컨테이너에 등록될 때 , 생성이 됨
    //의존관계주입이 끝나면 (afterPropertiesSet이 의미) connect와 Call을 호출

    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기 연결 메세지");
    }


    public void close() {
        System.out.println("NetworkClient.close" );
        disconnect();
    }
}
