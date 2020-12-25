package Spring.JdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @author kikukk
 */

@Repository
public class BookDaoImp {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //添加的方法
    void addBook(Book book) {
        String sql = "insert into t_book values(?,?,?)";
        Object[] args = {book.getBookId(),book.getBookname(),book.getBstatus()};
        int update = jdbcTemplate.update(sql,args);
        System.out.println(update);
    }

    void updateBook(Book book){
        String sql = "update t_book set name=?,status=? where id=?";
        Object[] args = {book.getBookname(),book.getBstatus(),book.getBookId()};
        int update = jdbcTemplate.update(sql,args);
        System.out.println(update);
    }

    void deleteBook(String id){
        String sql = "delete from t_book where id=?";
        int update = jdbcTemplate.update(sql,id);
        System.out.println(update);
    }

    int selectCount() {
        String sql = "select count(*) from t_book";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    String findBookName(String id){
        String sql = "select name from t_book where id=?";
        return jdbcTemplate.queryForObject(sql,String.class,id);
    }

    Book findBookInfo(String id) {
        String sql = "select * from t_book where id=?";
        Book book =  jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Book.class),id);
        System.out.println(book.getBookId());
        return book;
    }

    List<Book> findAllBook() {
        String sql = "select * from t_book";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Book.class));
    }

    void batchAddBook(List<Object[]> batchArgs){
        String sql = "insert into t_book values(?,?,?)";
        int[] ints = jdbcTemplate.batchUpdate(sql,batchArgs);
        System.out.println(Arrays.toString(ints));


    }

    public void batchUpdateBook(List<Object[]> batchArgs) {
        String sql = "update t_book set name=?,status=? where id=?";
        int[] ints = jdbcTemplate.batchUpdate(sql,batchArgs);
        System.out.println(Arrays.toString(ints));
    }
}
