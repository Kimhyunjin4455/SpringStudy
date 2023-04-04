package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepositoty();
    //1. 가입하고 회원을 찾으려면 구현 객체(공간)가 필요, 생성한 MemoryMemberRepository 를 지정, 지정하지 않을시 널포인터 예외 발생
    private final MemberRepository memberRepository;

    @Autowired // 컴포넌트 방식 이용하면 autowired 통해 DI 함, 자동 의존관계 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }//생성자를 통해 구현체를 넣음

    @Override
    public void join(Member member) {
        memberRepository.save(member);
        //2. join에서 save를 호출하면, 다형성에 의해 MemberRepository 인터페이스가 아닌 MemoryMemberRepository의 save가(오버라이드 한것) 호출됨
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
