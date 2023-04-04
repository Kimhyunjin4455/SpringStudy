package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    //실행하기 전 AppConfig 필드 생성 후 memberService 값 할, Test 갯수만큼 할당 됨

    //MemberService memberService = new MemberServiceImpl(memberRepository); // 메소드 사용 위한 필드 생성,
    //위 코드는 의존성 역전 원칙을 꺠트림
    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //then
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
