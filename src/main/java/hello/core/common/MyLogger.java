package hello.core.common;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
//@Scope(value = "request") // 파라미터 1개일때는 value 생략 가능
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // MyLogger 클래스의 프록시 생성
// No Thread bound 오류는 request scope 인데 주입 받으려 하는 것이 없어서 일어나는 것
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("["+ uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("["+ uuid + "] request scope bean create: "+this);
    }

    @PreDestroy
    public void close(){
        System.out.println("["+ uuid + "] request scope bean close: "+this);
    }


}
