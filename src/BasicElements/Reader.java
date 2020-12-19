package BasicElements;

import java.util.Objects;

/**
存放读者信息,主要用于记录登记信息
 */

public class Reader {
    private String reader_id;//读者号，用于登陆系统，规定为11位
    private String reader_name;//读者姓名
    private String kind;//老师还是学生
    private String sex;//性别
    private String password;//密码

    //构造方法
    public Reader(String id, String name, String kd, String sx, String passw){
        reader_id = id;
        reader_name = name;
        kind = kd;
        sex = sx;
        password = passw;
    }


    public String getReader_id() { return reader_id; }
    public String getReader_name() { return reader_name; }
    public String getKind() { return kind; }
    public String getSex() { return sex; }
    public String getPassword() { return password; }

    public void setReader_id(String reader_id) { this.reader_id = reader_id; }
    public void setReader_name(String reader_name) { this.reader_name = reader_name; }
    public void setKind(String kind) { this.kind = kind; }
    public void setSex(String sex) { this.sex = sex; }
    public void setPassword(String passworld) { this.password = passworld; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equals(reader_id, reader.reader_id) &&
                Objects.equals(reader_name, reader.reader_name) &&
                Objects.equals(kind, reader.kind) &&
                Objects.equals(sex, reader.sex) &&
                Objects.equals(password, reader.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reader_id, reader_name, kind, sex, password);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "reader_id='" + reader_id + '\'' +
                ", reader_name='" + reader_name + '\'' +
                ", kind='" + kind + '\'' +
                ", sex='" + sex + '\'' +
                ", passworld='" + password + '\'' +
                '}';
    }
}
