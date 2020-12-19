package System;

import BasicElements.Librarian;

import java.util.ArrayList;

/**
 导入管理员信息入内存
 */
public class MiniSystem_Librarian {
    private ArrayList<Librarian>librarians_array;//使用ArrayList存放管理员信息
    private String filename_librarian ="C:\\Users\\23862\\Desktop\\学习\\已学\\Java\\课程设计\\Librarian.txt";//存放管理员信息的txt文本

    /**
     构造方法，将管理员信息读取到内存中
     */
    public MiniSystem_Librarian(){
        librarians_array = new ArrayList<Librarian>();//初始化存储的librarians_array
        ArrayList<String[]> librarianArray = AssistMethods.readfile(filename_librarian);
        for (int i=0;i<librarianArray.size();++i)
        {
            //依次提取相应元素
            String librarian_id = librarianArray.get(i)[0];
            String librarian_name = librarianArray.get(i)[1];
            String sex = librarianArray.get(i)[2];
            String password = librarianArray.get(i)[3];
            Librarian librarian_insert = new Librarian(librarian_id,librarian_name,sex,password);//初始化提取到的信息
            librarians_array.add(librarian_insert);
        }
    }

    /**
     查询账号是否在系统中
     */
    public boolean isIdExist(String _id) {
        for (Librarian librarian : librarians_array) {//遍历检索id
            if (_id.equals(librarian.getUser_id())) {//id一致
                return true;//账号存在
            }
        }
        return false;//账号不存在
    }

    /**
     获取密码，用于用户登录时，验证输入密码。
     */
    public String getPasswold(String id) {
        for (Librarian librarian : librarians_array) {//遍历检索id
            if (id.equals(librarian.getUser_id())) {//id一致
                return librarian.getPassword();//返回密码
            }
        }
        return null;//查找不到即返回空，一般用不到这一步，因为会提前判断账号是否存在
    }

    /**
     * 通过账号和密码获取这个librarian对象,用于登陆
     */
    public Librarian getLibrarian(String id, String pass){
        for (Librarian librarian:librarians_array) {
            if (librarian.getUser_id().equals(id) && librarian.getPassword().equals(pass))
                return librarian;
        }
        return null;
    }



}
