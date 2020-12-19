package BasicElements;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *记录图书馆的借阅信息
 */
public class Borrow {
    static int maxLastDay = 60;//最大借阅时间为60天

    private String reader_id;//借阅读者号码
    private String reader_name;//借阅读者姓名
    private String book_id;//借阅书籍号
    private String book_name;//借阅书籍姓名
    private String date_start;//借阅开始时间
    private int num;//借阅数目


    public Borrow(String rid,String rname,String bid,String bname,String date,int _num){
        reader_id = rid;
        reader_name = rname;
        book_id = bid;
        book_name = bname;
        date_start = date;
        num = _num;
    }


    public String getReader_id() { return reader_id; }
    public String getBook_id() { return book_id; }
    public String getDate_start() { return date_start; }
    public String getBook_name() { return book_name; }
    public String getReader_name() { return reader_name; }
    public int getNum() { return num; }


    public void setReader_name(String reader_name) { this.reader_name = reader_name; }
    public void setBook_name(String book_name) { this.book_name = book_name; }
    public void setNum(int num) { this.num = num; }
    public void setReader_id(String reader_id) { this.reader_id = reader_id; }
    public void setBook_id(String book_id) { this.book_id = book_id; }
    public void setDate_start(String date_start) { this.date_start = date_start; }


    @Override
    public String toString() {
            return "Borrow{" +
                    "借阅读者号:'" + reader_id + '\'' +
                    ",读者名：'" + reader_name + '\'' +
                    ",借阅书籍号：'" + book_id + '\'' +
                    ",书名：'" + book_name + '\'' +
                    ", 借阅时间:'" + date_start + '\'' +
                    ",借阅数量：" + num  +
                    ",借阅最长时间（天）：" +maxLastDay+
                    ",是否逾期：" +isOverdue()+
                    '}';
    }

    /**
     判断该借阅图书是否逾期
     */
    public boolean isOverdue() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;//开始时间,为借阅时间
        try {
            startDate = sdf.parse(date_start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = new Date(); //结束时间为当前时间
        long betweenDate = (endDate.getTime() - startDate.getTime()) / (60 * 60 * 24 * 1000); //得到相差的天数 betweenDate
        if (betweenDate <= maxLastDay)//小于最大借阅日期，即没有到时间
            return false;
        else
            return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrow borrow = (Borrow) o;
        return num == borrow.num &&
                Objects.equals(reader_id, borrow.reader_id) &&
                Objects.equals(reader_name, borrow.reader_name) &&
                Objects.equals(book_id, borrow.book_id) &&
                Objects.equals(book_name, borrow.book_name) &&
                Objects.equals(date_start, borrow.date_start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reader_id, reader_name, book_id, book_name, date_start, num);
    }
}
