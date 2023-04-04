package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 스프링에서 구성정보를 담당하는 어노테이션
public class AppConfig {
    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository() + new RateDiscountPolicy()
    // new MemoryMemberRepository()가 2번 호출됨 > 싱글톤이 깨지는것 아닌가?
    @Bean
    // 스프링 컨테이너에 등록
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());//AppConfig에서 MemoryMemberRepository를 생성해서 주입, 생성자 주입
//new MemoryMemberRepositoty()를 memoryRepository 로 리팩터링(cmd+option+m)
        //리팩터링을 통해 어플리케이션 전체 구성을 파악할 수 있도록 함
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //return null;
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}

