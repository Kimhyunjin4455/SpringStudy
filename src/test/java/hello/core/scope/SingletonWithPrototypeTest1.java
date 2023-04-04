package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(CilentBean.class, PrototypeBean.class); // 두개 다 컴포넌트 스캔되어 자동 빈 등록됨
        CilentBean cilentBean1 = ac.getBean(CilentBean.class);
        int count1 = cilentBean1.logic();
        assertThat(count1).isEqualTo(1);

        CilentBean cilentBean2 = ac.getBean(CilentBean.class);
        int count2 = cilentBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class CilentBean{
//        private final PrototypeBean prototypeBean; // 1 생성시점에 주입

        @Autowired // 3
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count; // cmd + option + n
        }

//        @Autowired // 2
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
//
//        public int logic(){
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count; // cmd + option + n
//        }

//        @Autowired // 1
//        public CilentBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

//        public int logic(){
//            prototypeBean.addCount();
//            int count = prototypeBean.getCount();
//            return count; // cmd + option + n
//
//        }


    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy ");
        }
    }
}
