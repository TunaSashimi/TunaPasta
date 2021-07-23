package history;

public class ForeachTest {
    public static void main(String[] args) {
        Book book = new Book("武林外传", new String[]{"吕轻侯", "郭芙蓉", "白展堂"});
        //直接可以迭代~
        for (String s : book.authors)
            System.out.println(s);
    }
}

class Book {
    String name;
    String[] authors;

    public Book(String name, String[] authors) {
        this.name = name;
        this.authors = authors;
    }
}