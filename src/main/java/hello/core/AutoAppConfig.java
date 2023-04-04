package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan(
        // basePackages = "hello.core.member" // 이러면 member 패키지만 컴포넌트 스캔의 대상이 , 지정 안하면 디폴트 값인 찻째줄의 패키지가 기준이 됨
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)// 컴포넌트 스캔을 통해 스프링 빈으로 자동 등록하는데 그중에 거를것을 지정
public class AutoAppConfig {
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
