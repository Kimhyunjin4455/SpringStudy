package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    // 객체 생성: 자기 자신을 내부에 private 하게 가지고 있는데 static 이기에 클래스 레벨에 올라가기에 딱 하나만 존재
    // 실행하면 static 영역의 SingletonService instance가 초기화 되면서 생성, 그 참조를 꺼낼 때는 아래의 코드 이용
    // 외부에서는 생성 불가

    public static SingletonService getInstance(){
        return instance;
    }// 객체 조회시에 이 코드 사용

    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }


}
