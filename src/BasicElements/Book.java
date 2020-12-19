package BasicElements;

import java.util.Date;
import java.util.Objects;

/**
记录图书馆存放书籍信息
*/

public class Book {
    private String book_id;//书号
    private String book_name;//书名
    private String author;//作者！
    private int currentNum;//现存
    private int totalNum;//库存
    private String publisher;//出版社！
    private String publish;//出版日期

    public Book(String _book_id,String _book_name,String _author,int _currentNum,int _totalNum,String _publisher,String _publish){//构造方法
    book_id = _book_id;
    book_name = _book_name;
    author = _author;
    currentNum = _currentNum;
    totalNum = _totalNum;
    publisher = _publisher;
    publish = _publish;
}


    public String getBook_id(){return book_id; }
    public String getBook_name(){return book_name;}
    public String getAuthor() { return author; }
    public int getCurrentNum() { return currentNum; }
    public int getTotalNum() { return totalNum; }
    public String getPublisher() { return publisher; }
    public String getPublish() { return publish; }

    public void setBook_id(String book_id) { this.book_id = book_id; }
    public void setBook_name(String book_name) {this.book_name = book_name; }
    public void setCurrentNum(int currentNum) { this.currentNum = currentNum; }
    public void setTotalNum(int totalNum) { this.totalNum = totalNum; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public void setPublish(String publish) { this.publish = publish; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return currentNum == book.currentNum &&
                totalNum == book.totalNum &&
                Objects.equals(book_id, book.book_id) &&
                Objects.equals(book_name, book.book_name) &&
                Objects.equals(author, book.author) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(publish, book.publish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id);
        //return Objects.hash(book_id, book_name, author, currentNum, totalNum, publisher, publish);
    }

    @Override
    public String toString() {
        return "Book{" +
                "书号：'" + book_id + '\'' +
                ",书名：'" + book_name + '\'' +
                ",作者：'" + author + '\'' +
                ",现存：" + currentNum +
                ",库存总量：" + totalNum +
                ",出版社：'" + publisher + '\'' +
                ",出版日期：" + publish +
                '}';
    }
}
