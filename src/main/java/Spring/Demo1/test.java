package Spring.Demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class test {
    public static void main(String[] args) {}
    @Test
    public void testAdd(){
        //加载配置文件
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean2.xml");
        //获取配置创建的对象
        Book book = context.getBean("book",Book.class);
        System.out.println(book);
        book.test();
    }

    @Test
    public void testCollection2(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean2.xml");
        Book book1 = context.getBean("book",Book.class);
        Book book2 = context.getBean("book",Book.class);
    }



    @Test
    public void testBean3(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
        Orders orders = context.getBean("orders",Orders.class);
        System.out.println("第四步 获取创建bean实例对象");
        System.out.println(orders);
        //手动让bean实例销毁
        context.close();
    }

}
