package Spring.Demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {


    @Test
    public void testAdd(){
        //加载配置文件
        ApplicationContext context =
                new ClassPathXmlApplicationContext("SpringDemo.IOC/bean2.xml");
        //获取配置创建的对象
        Book book = context.getBean("book",Book.class);
        System.out.println(book);
        book.test();
    }


    @Test
    public void testBean3(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringDemo.IOC/bean4.xml");
        Orders orders = context.getBean("orders",Orders.class);
        System.out.println("第四步 获取创建bean实例对象");
        System.out.println(orders);
        //手动让bean实例销毁
        context.close();
    }

}
