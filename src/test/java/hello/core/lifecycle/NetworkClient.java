package hello.core.lifecycle;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect(){
        System.out.println("Connect: "+ url);
    }

    public void call(String message){
        System.out.println("call: "+ url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("Close: " + url);
    }


    @PostConstruct
    public void init() throws Exception { // 의존관계 주입이 끝나 호출
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() throws Exception { // 빈 종료 타이밍에 실행
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
