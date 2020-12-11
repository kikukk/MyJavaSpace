package Spring.Demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author kikukk
 */
public class test {

    @Test
    public void testDemo2_1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Demo2_1.xml");
        UserService userService = context.getBean("userService",UserService.class);
        userService.add();
    }


    @Test
    public void testDemo2_2(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService",UserService.class);
        userService.add();
    }
}
