package Spring.JdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kikukk
 */
@Service
public class BookService {
    @Autowired
    private BookDaoImp bookDao;

    //添加的方法
    void addBook(Book book){
        bookDao.addBook(book);
    }

    void updateBook(Book book){
        bookDao.updateBook(book);
    }

    void deleteBook(String id){
        bookDao.deleteBook(id);
    }

    int findCount(){
        return bookDao.selectCount();
    }

    String findBookName(String id){return bookDao.findBookName(id);}

    Book findBookInfo(String id){
        return bookDao.findBookInfo(id);
    }

    List<Book> findAllBook(){
        return bookDao.findAllBook();
    }

    void batchAddBook(List<Object[]> batchArgs){
        bookDao.batchAddBook(batchArgs);
    }

    void batchUpdateBook(List<Object[]> batchArgs) {
        bookDao.batchUpdateBook(batchArgs);
    }
}
