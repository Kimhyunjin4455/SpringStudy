package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class  StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자가 10000원 주문
        int userPriceA = statefulService1.order("userA", 10000);
        // int userPriceA 를 통해 지역변수 공유가 아닌, 새 필드에 값 할당
        // ThreadB: B사용자가 20000원 주문
        int userPriceB = statefulService2.order("userB", 20000);

        // ThreadA: A사용자가 주문 금액 조회, A가 주문하고 조회하는 사이에 B쓰레드가 끼어든 상황
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userPriceA);

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}