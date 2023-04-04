package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final 을 보고 알아서 생성자 생성함
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository; // 메모리 구현체
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 고정 할인 정책
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 비율 할인 정책
    //위 두 인터페이스 구현체 필드 생성 코드는 DIP(의존관계 역전)원칙을 위반, 추상(인터페이스)에만 의존해야 함
    private final DiscountPolicy discountPolicy; // 인터페이스(추상)에만 의존하게 됨

    @Autowired // 컴포넌트 스캔을 통해 OrderServiceImpl이 스프링 빈에 등록될 떄, 생성자 호출 시 autowired 통해 컨테이너에서 꺼내서 의존관계 주
    // 생성자가 1개일 때는 autowired 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    } // 생성자 통해 구현체 할당, AppConfig 에서 할당됨, AppConfig 통한 할당을 해서 DIP원칙을 지키게 (인터페이스에만 의존)
    //@Autowired private MemberRepository memberRepository; // 필드 주입 but 권장하지 않는 안티 패턴
    //@Autowired private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
