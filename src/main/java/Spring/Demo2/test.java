package Spring.Demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author kikukk
 */
public class test {

    @Test
    public void testDemo2_2(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService",UserService.class);
        userService.add();
    }
}
