package System;

import BasicElements.Reader;

import java.util.ArrayList;
import java.util.Scanner;


/**
 导入读者信息入内存
 */
public class MiniSystem_Reader {
    private ArrayList<Reader> readers;//使用hashset存放读者
    private String fileName_reader = "C:\\Users\\23862\\Desktop\\学习\\已学\\Java\\课程设计\\Reader.txt";

    /**
     构造方法，将读者信息读取到内存中
     */
    public MiniSystem_Reader(){
        readers = new ArrayList<Reader>();//初始化存储的readers
        ArrayList<String[]> librarianArray = AssistMethods.readfile(fileName_reader);
        for (int i=0;i<librarianArray.size();++i)
        {
            //依次提取相应元素
            String reader_id = librarianArray.get(i)[0];
            String reader_name = librarianArray.get(i)[1];
            String kind = librarianArray.get(i)[2];
            String sex = librarianArray.get(i)[3];
            String password = librarianArray.get(i)[4];
            Reader reader_insert = new Reader(reader_id,reader_name,kind,sex,password);//初始化提取到的元素
            readers.add(reader_insert);//插入元素
        }
    }

    /**
     查询账号是否在系统中
     */
    public boolean isIdExist(String _id) {
        for (Reader reader : readers) {//遍历检索id
            if (_id.equals(reader.getReader_id())) {//id一致
                return true;//账号存在
            }
        }
        return false;//账号不存在
    }

    /**
     获取密码，用于用户登录时，验证输入密码。
     */
    public String getPasswold(String id) {
        for (Reader reader : readers) {//遍历检索id
            if (id.equals(reader.getReader_id())) {//id一致
                return reader.getPassword();//返回密码
            }
        }
        return null;//查找不到即返回空，一般用不到这一步，因为会提前判断账号是否存在
    }

    /**
     * 通过账号和密码获取这个reader对象,用于登陆
     */
    public Reader getReader(String id,String pass){
        for (Reader reader:readers) {
            if (reader.getReader_id().equals(id) && reader.getPassword().equals(pass))
                return reader;
        }
        return null;
    }


    /**
     新建一个账号
     */
    public Reader addReader(){
        System.out.println("请输入自定义账号：");
        Scanner reader = new Scanner(System.in);
        String id = reader.next();
        boolean isExist = isIdExist(id);//检查账号是否已经被注册
        while (isExist) {
            System.out.println("此账号已被注册，请重新输入账号");
            id = reader.next();//再次输入
            isExist = isIdExist(id);//再次检查账号是否已经被注册
        }
        System.out.println("请输入真实姓名：");
        String name = reader.next();
        System.out.println("请输入身份：");
        String kind = reader.next();
        System.out.println("请输入性别：");
        String sex = reader.next();
        System.out.println("请输入密码：");
        String password1 = reader.next();
        System.out.println("请确认刚刚输入的密码：");
        String password2 = reader.next();
        boolean equality = password2.equals(password1);//检测两次输入是否相等
        if (!equality){//两次输入不相等
            System.out.println("输入密码和之前输入不相等，请再次输入密码：");
            password2 = reader.next();
            equality = password2.equals(password1);//再次检测两次输入是否相等
        }
        Reader reader_new = new Reader(id,name,kind,sex,password1);//初始化得到的信息
        readers.add(reader_new);//插入新注册的用户
        System.out.println("注册成功！");
        return reader_new;
    }


    /**
     通过reader对象，注销一个账号
     */
    public boolean deleteReader(Reader reader){
        return readers.remove(reader);//删除账号
    }
}
