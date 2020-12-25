package Spring.JdbcTemplate;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kikukk
 */
public class test {

    @Test
    public void testJdbcTemplateAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringDemo/JdbcTemplate/bean1.xml");
        BookService bookService = context.getBean("bookService",BookService.class);
        Book book = new Book();
        book.setBookId("1");
        book.setBookname("java");
        book.setBstatus("a");
        bookService.addBook(book);
    }

    @Test
    public void testJdbcTemplateUpdate(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringDemo/JdbcTemplate/bean1.xml");
        BookService bookService = context.getBean("bookService",BookService.class);
        Book book = new Book();
        book.setBookId("1");
        book.setBookname("java2");
        book.setBstatus("b");
        bookService.updateBook(book);
    }

    @Test
    public void testJdbcTemplateDelete(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringDemo/JdbcTemplate/bean1.xml");
        BookService bookService = context.getBean("bookService",BookService.class);
        bookService.deleteBook("1");
    }

    @Test
    public void testJdbcTemplateCount(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringDemo/JdbcTemplate/bean1.xml");
        BookService bookService = context.getBean("bookService",BookService.class);
        System.out.println(bookService.findCount());
    }

    @Test
    public void testJdbcTemplateFindName(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringDemo/JdbcTemplate/bean1.xml");
        BookService bookService = context.getBean("bookService",BookService.class);
        System.out.println(bookService.findBookName("1"));
    }

    @Test
    public void testJdbcTemplateFindOne(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringDemo/JdbcTemplate/bean1.xml");
        BookService bookService = context.getBean("bookService",BookService.class);
        System.out.println(bookService.findBookInfo("1"));
    }

    @Test
    public void testJdbcTemplateFindAll(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringDemo/JdbcTemplate/bean1.xml");
        BookService bookService = context.getBean("bookService",BookService.class);
        System.out.println(bookService.findAllBook());
    }

    @Test
    public void testJdbcTemplateBatchAddBook(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringDemo/JdbcTemplate/bean1.xml");
        BookService bookService = context.getBean("bookService",BookService.class);
        List<Object[]> batchArgs = new ArrayList<>();
        Object[] o1 = {"3","C#","c"};
        Object[] o2 = {"4","MySql","d"};
        Object[] o3 = {"5","Kotlin","e"};
        batchArgs.add(o1);
        batchArgs.add(o2);
        batchArgs.add(o3);
        bookService.batchAddBook(batchArgs);
    }

    @Test
    public void testJdbcTemplateBatchUpdateBook(){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringDemo/JdbcTemplate/bean1.xml");
        BookService bookService = context.getBean("bookService",BookService.class);
        List<Object[]> batchArgs = new ArrayList<>();
        Object[] o1 = {"C#","c","4"};
        Object[] o2 = {"MySql","d","5"};
        Object[] o3 = {"Kotlin","e","3"};
        batchArgs.add(o1);
        batchArgs.add(o2);
        batchArgs.add(o3);
        bookService.batchUpdateBook(batchArgs);
    }

}
