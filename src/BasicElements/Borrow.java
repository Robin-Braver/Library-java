package BasicElements;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *��¼ͼ��ݵĽ�����Ϣ
 */
public class Borrow {
    static int maxLastDay = 60;//������ʱ��Ϊ60��

    private String reader_id;//���Ķ��ߺ���
    private String reader_name;//���Ķ�������
    private String book_id;//�����鼮��
    private String book_name;//�����鼮����
    private String date_start;//���Ŀ�ʼʱ��
    private int num;//������Ŀ


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
                    "���Ķ��ߺ�:'" + reader_id + '\'' +
                    ",��������'" + reader_name + '\'' +
                    ",�����鼮�ţ�'" + book_id + '\'' +
                    ",������'" + book_name + '\'' +
                    ", ����ʱ��:'" + date_start + '\'' +
                    ",����������" + num  +
                    ",�����ʱ�䣨�죩��" +maxLastDay+
                    ",�Ƿ����ڣ�" +isOverdue()+
                    '}';
    }

    /**
     �жϸý���ͼ���Ƿ�����
     */
    public boolean isOverdue() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;//��ʼʱ��,Ϊ����ʱ��
        try {
            startDate = sdf.parse(date_start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = new Date(); //����ʱ��Ϊ��ǰʱ��
        long betweenDate = (endDate.getTime() - startDate.getTime()) / (60 * 60 * 24 * 1000); //�õ��������� betweenDate
        if (betweenDate <= maxLastDay)//С�����������ڣ���û�е�ʱ��
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
