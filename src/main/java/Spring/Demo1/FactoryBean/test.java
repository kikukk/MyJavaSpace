package Spring.Demo1.FactoryBean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author kikukk
 */

public class test {

    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringDemo.IOC/bean3.xml");
        Course course = context.getBean("myBean", Course.class);

        System.out.println(course);
    }
}
