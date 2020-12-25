package Spring.JdbcTemplate;

/**
 * @author kikukk
 */
public class Book {
    private String bookId;
    private String bookname;
    private String bstatus;

    public String getBookId() {
        return bookId;
    }

    public String getBookname() {
        return bookname;
    }

    public String getBstatus() {
        return bstatus;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setBstatus(String bstatus) {
        this.bstatus = bstatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookname='" + bookname + '\'' +
                ", bstatus='" + bstatus + '\'' +
                '}';
    }
}
