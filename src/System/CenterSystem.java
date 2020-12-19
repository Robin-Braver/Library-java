package System;

import BasicElements.Book;
import BasicElements.Borrow;
import BasicElements.Librarian;
import BasicElements.Reader;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 由借阅系统和藏书子系统组成的中心系统
 */
public class CenterSystem {
    private MiniSystem_Borrow miniSystem_borrow;
    private MiniSystem_Book miniSystem_book;

    private Reader readerLogin;//记录当前登录的用户
    private Librarian librarianLogin;//记录当前登录的管理员

    /**
     构造方法，调用四个子系统构造，完成初始化工作,reader和librarian总会有一个传入null参数
     */
    public CenterSystem(){
        miniSystem_borrow = new MiniSystem_Borrow();
        miniSystem_book = new MiniSystem_Book();
        readerLogin = null;//初始都是null后面登陆后设置
        librarianLogin = null;
    }

    public void setReaderLogin(Reader readerLogin) {
        this.readerLogin = readerLogin;
    }

    public void setLibrarianLogin(Librarian librarianLogin) {
        this.librarianLogin = librarianLogin;
    }

    /**
     读者：通过图书id 借阅图书
     */
    public void borrowBook_id() {
        System.out.println("请输入需要借阅书籍的书籍号：");
        Scanner reader_in = new Scanner(System.in);
        String book_id = reader_in.next();//读入书籍号
        for (Book book : miniSystem_book.book_set) {//遍历每一本书
            if (book.getBook_id().equals(book_id)) {//查找到书号相等的书籍
                System.out.println("查找到书籍！书籍信息如下：");
                System.out.println(book.toString());
                String book_name = book.getBook_name();//获取到当前的书名，方便插入借阅信息
                System.out.println("请输入借阅数量：");
                int num = reader_in.nextInt();//读入数量
                while(num>book.getCurrentNum()){//借阅数量大于库存
                    System.out.println("借阅数量大于当前可提供的最大量，请重新输入");
                    num = reader_in.nextInt();//再次读入数量
                }
                book.setCurrentNum(book.getCurrentNum()-num);//修改现存的数目
                Date date = new Date();//获取借阅时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date_string = sdf.format(date);//转换时间为指定的字符串格式
                Borrow borrow_new = new Borrow(readerLogin.getReader_id(),readerLogin.getReader_name(),book_id,book_name,date_string,num);
                miniSystem_borrow.borrows.add(borrow_new);//将刚刚的借阅信息加入到系统之中
                System.out.println("借阅成功！");
                return;//借阅完成退出
            }
        }
        System.out.println("未查找到书籍，请确认后再重试！");
    }

    /**
     读者：查看当前读者的借阅信息
     */
    public ArrayList<Borrow> getBorrowInformation_user(){
        ArrayList<Borrow> borrowsInformation = new ArrayList<Borrow>();//记录借阅信息的链表
        for (Borrow borrow:miniSystem_borrow.borrows) {//遍历借阅列表
            if (borrow.getReader_id().equals(readerLogin.getReader_id())){//当借阅信息号码和登录的号码一致时
                borrowsInformation.add(borrow);//插入结果链表中
            }
        }
        return borrowsInformation;//返回结果
    }

    /**
     读者：通过图书id 归还图书
     */
    public void returnBook_id() {
        ArrayList<Borrow> borrowsInformation = getBorrowInformation_user();//查看当前用户的借阅信息
        if (borrowsInformation.size()<=0){
            System.out.println("当前用户没有借阅任何书籍！");
            return;
        }
        System.out.println("当前用户借阅信息如下：");
        for(Borrow borrow:borrowsInformation){
            System.out.println(borrow.toString());
        }
        System.out.print("请输入需要归还的书籍号：");
        Scanner reader = new Scanner(System.in);
        String id_return = reader.next();//读取id
        System.out.print("请输入需要归还数目：");
        int num_return = reader.nextInt();//读取归还数目
        for(Borrow borrow_insystem:miniSystem_borrow.borrows){//再次遍历，找到书籍:条件时书号和输入书号相等以及借阅人书号是登录的账号
            if (borrow_insystem.getBook_id().equals(id_return)&&borrow_insystem.getReader_id().equals(readerLogin.getReader_id())){
                while (borrow_insystem.getNum()<num_return||num_return<=0) {//输入归还数目不合理
                    System.out.print("输入归还数目不合理，请重新输入：");
                    num_return = reader.nextInt();//再次读取归还数目
                }
                if (num_return == borrow_insystem.getNum()) {//归还的是所有借阅的书籍
                    miniSystem_borrow.borrows.remove(borrow_insystem);//删除借阅信息
                } else {//仅修改借阅上的订单信息
                    borrow_insystem.setNum(borrow_insystem.getNum() - num_return);
                }
                for (Book book:miniSystem_book.book_set){//修改馆藏信息
                    if (book.getBook_id().equals(id_return)) {
                        book.setCurrentNum(book.getCurrentNum()+num_return);
                        System.out.println("归还成功");
                        return;
                    }
                }
            }
        }
        System.out.println("归还失败");
    }


    /**
     both：查询图书，支持多种方式的查询
     */
    public void findBooks() {
        System.out.println("\033[1;93;45m" + "当前模式：读者模式" + "\033[m");
        System.out.println("请选择自己的查询方式");
        System.out.println("1：查询所有书籍");
        System.out.println("2：通过书名查询（支持模糊查询）");
        System.out.println("3：通过书号查询");
        System.out.println("4：通过作者查询（支持模糊查询）");
        System.out.println("5：通过出版社查询（支持模糊查询）");
        System.out.println("0：退出查询");
        System.out.print("请输入要查询的方式：");
        Scanner reader = new Scanner(System.in);
        int choice = reader.nextInt();//获取用户选择
        while(0>choice&&choice>5){//检查输入是否合理
            System.out.println("数字输入有误，请检查后再次输入！");
            System.out.print("请选择自己的查询方式:");
            reader = new Scanner(System.in);
        }
        while(choice!=0){//不选择退出就一直可以查询
          AssistMethods.clearScreen();
            if (choice==1){
                AssistMethods.clearScreen();
                System.out.println("\033[31m"+"当前查询方式【查询所有书籍】");
                System.out.println("\033[30m");
                miniSystem_book.searchBook();
            }
            else if (choice==2){
                AssistMethods.clearScreen();
                System.out.println("\033[31m"+"当前查询方式【通过书名查询】");
                System.out.println("\033[30m");
                miniSystem_book.searchBook_name();
            }
            else if (choice==3){
                AssistMethods.clearScreen();
                System.out.println("\033[31m"+"当前查询方式【通过书号查询】");
                System.out.println("\033[30m");
                miniSystem_book.searchBook_id();
            }
            else if (choice==4){
                AssistMethods.clearScreen();
                System.out.println("\033[31m"+"当前查询方式【通过作者查询】");
                System.out.println("\033[30m");
                miniSystem_book.searchBook_author();
            }
            else if (choice==5){
                AssistMethods.clearScreen();
                System.out.println("\033[31m"+"当前查询方式【通过出版社查询】");
                System.out.println("\033[30m");
                miniSystem_book.searchBook_publisher();
            }
            System.out.println();
            System.out.print("请选择接下来要进行的操作：");
            System.out.println("[1：查询所有书籍,2：通过书名查询,3:通过书号查询,4：通过作者查询,5：通过出版社查询,0：退出查询]");
            System.out.print("请选择自己的查询方式：");
            choice = reader.nextInt();//获取用户选择
        }
    }

    /**
     管理员操作：删除一本书
     */
    public void deleteBooks(){
        System.out.println("请输入要删除书籍的书号：");
        Scanner reader = new Scanner(System.in);
        String id = reader.next();//读取id
        miniSystem_book.deleteBook(id);//调用子系统删除
        int index = 0;
        while (index<miniSystem_borrow.borrows.size()){//删除关于此书的所有借阅信息
            Borrow borrow = miniSystem_borrow.borrows.get(index);
            if (borrow.getBook_id().equals(id)) {
                miniSystem_borrow.borrows.remove(borrow);//删除相关借阅信息
                continue;
            }++index;//删除操作不++，因为数组元素个数在变
        }
        System.out.println("书籍删除成功！");

    }

    /**
     管理员操作： 新增一本书
     */
    public void addBooks(){
        miniSystem_book.addBook();//调用子系统的插入一本书
    }

    /**
     管理员操作：删除所有书
     */
    public void clearBooks(){
        miniSystem_book.clearBook();//调用子系统的方法
        miniSystem_borrow.borrows.clear();//删除所有借阅信息
        System.out.println("清空成功！");
    }

    /**
     管理员操作：查看借阅信息
     */
    public void getBorrowInformation_all(){
        miniSystem_borrow.Search();//调用子系统，查看借阅信息
    }



}
