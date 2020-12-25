package Spring.AOP1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author kikukk
 */
public class test {
    @Test
    public void testAop(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringDemo/AOP/AopBean.xml");
        User user1 = context.getBean("user",User.class);
        user1.add();
    }

    @Test
    public void testAop2(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigAop.class);
        User user1 = context.getBean("user",User.class);
        user1.add();
    }

}
