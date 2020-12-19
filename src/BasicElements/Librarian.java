package BasicElements;

import java.util.Objects;

/**
 记录图书馆管理员信息
 */

public class Librarian {
    private String user_id;//账号
    private String user_name;//姓名
    private String sex;//性别
    private String password;//密码

    public Librarian(String id,String name,String sx,String passw){//构造方法
        user_name = name;
        user_id = id;
        sex = sx;
        password = passw;
    }

    public String getUser_name() { return user_name; }
    public String getPassword() { return password; }
    public String getSex() { return sex; }
    public String getUser_id() { return user_id; }

    public void setUser_name(String user_name) { this.user_name = user_name; }
    public void setPassword(String password) { this.password = password; }

    public void setSex(String sex) { this.sex = sex; }

    public void setUser_id(String user_id) { this.user_id = user_id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Librarian librarian = (Librarian) o;
        return Objects.equals(user_id, librarian.user_id) &&
                Objects.equals(user_name, librarian.user_name) &&
                Objects.equals(sex, librarian.sex) &&
                Objects.equals(password, librarian.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, user_name, sex, password);
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
