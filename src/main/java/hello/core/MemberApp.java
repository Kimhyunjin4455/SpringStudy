package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService(); //MemberServiceImpl이 들어가 있음
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 스프링이 AppConfig 의 설정정보를 컨테이너에 집어 넣음
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);//name이 메서드, 그 다음이 반환타입
   //     MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member =" + findMember.getName());


    }
}
