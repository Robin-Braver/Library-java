package BasicElements;

import java.util.Objects;

/**
��Ŷ�����Ϣ,��Ҫ���ڼ�¼�Ǽ���Ϣ
 */

public class Reader {
    private String reader_id;//���ߺţ����ڵ�½ϵͳ���涨Ϊ11λ
    private String reader_name;//��������
    private String kind;//��ʦ����ѧ��
    private String sex;//�Ա�
    private String password;//����

    //���췽��
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
