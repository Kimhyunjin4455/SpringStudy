package hello.core.web;


import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    // MyLogger 클래스의 scope에 프록시 모드가 지정되어 선언한 MyLogger은 프록시 클래스, 이 클래스의 기능을 호출하는 시점에 프록시 클래스가 진짜를 찾아 동작
//    private final ObjectProvider<MyLogger> myLoggerProvide; // DL발생, 주입 시점에 받음

    @RequestMapping("log-demo") // log-demo 요청이 오면
    @ResponseBody // 문자를 그대로 응답으로 보냄
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        // 자바에서 제공하는 표준 서블릿 규약이 있음, 그것의 의한 HttpRequest 정보를 받을 수 있다.
        String requestURL = request.getRequestURL().toString();


//        MyLogger myLogger = myLoggerProvide.getObject();
        // 컨트롤러에 고객 요청이 옴 > http 가 살아있음(on) > scope(request) 가능 > 그 상태에서 myLogger 호출
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }


}
