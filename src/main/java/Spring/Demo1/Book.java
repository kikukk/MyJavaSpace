package Spring.Demo1;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName Book
 * @Description TODO
 * @date 2020/9/7 10:05
 */

@Component (value="book")
class Book {
    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Book{" +
                "list=" + list +
                '}';
    }

    public void test(){
        System.out.println(list);
    }
}
