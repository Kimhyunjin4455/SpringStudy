package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient cilent = ac.getBean(NetworkClient.class);
        ac.close(); // close() 사용 위해 ConfigurableApplicationContext 이용
        // ConfigurableApplicationContext가 AnnotationConfigApplicationContext의 상위 인터페이스
    }

    @Configuration
    static class LifeCycleConfig{
        @Bean// (initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("https://hello-spring.dev");
            return networkClient;

        }
    }
}
