package System;

import BasicElements.Book;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
从文件读取图书馆书籍信息，并对书籍做相应操作
 */
public  class MiniSystem_Book {
    HashSet<Book> book_set;//使用hashset存放书籍
    private String fileName_book = "C:\\Users\\23862\\Desktop\\学习\\已学\\Java\\课程设计\\Book.txt";//读取文件位置

    /**
     构造方法，将文件书籍读取到内存中
     */
    public MiniSystem_Book(){
        book_set = new HashSet<Book>();//初始化存储的hashset
        ArrayList<String[]> bookArray = AssistMethods.readfile(fileName_book);
        for (int i=0;i<bookArray.size();++i)
        {
            //依次提取相应元素
            String book_id = bookArray.get(i)[0];
            String book_name = bookArray.get(i)[1];
            String author= bookArray.get(i)[2];
            int currentNum= Integer.valueOf(bookArray.get(i)[3]);
            int totalNum= Integer.valueOf(bookArray.get(i)[4]);
            String publisher= bookArray.get(i)[5];
            String publish= bookArray.get(i)[6];
            Book book_insert = new Book(book_id,book_name,author,currentNum,totalNum,publisher,publish);//初始化书籍类
            book_set.add(book_insert);//将初始化好的书籍插入到hashset中
        }
    }

    /**
     添加一本书
     */
    public void addBook(){
        System.out.print("请输入要插入书籍的书号：");
        Scanner reader = new Scanner(System.in);
        String id = reader.next();//读取id
        for (Book book_inset:book_set) //遍历每本书，检查其书号，是否和书相等
        {
            if (id.equals(book_inset.getBook_id())) {//书号相等，已经存在
                System.out.print("此书籍已经存在系统中，请直接输入需要采编入库的数量：");
                int numInput = reader.nextInt();
                book_inset.setCurrentNum(book_inset.getCurrentNum()+numInput);//更新当前数量
                book_inset.setTotalNum(book_inset.getTotalNum()+numInput);//更新插入后数量
                return;//提前结束循环
            }
        }
        System.out.print("请输入要插入书籍的书名：");
        String name = reader.next();
        System.out.print("请输入要插入书籍的作者：");
        String author = reader.next();
        System.out.print("请输入需要采编入库的数量：");
        int numInput = reader.nextInt();
        System.out.print("请输入要插入书籍的出版社：");
        String publish = reader.next();
        System.out.print("请输入要插入书籍的出版日期：");
        String publisher = reader.next();
        Book book_insert = new Book(id,name,author,numInput,numInput,publish,publisher);
        boolean isDone = book_set.add(book_insert);
        if (isDone)
            System.out.println("书籍插入成功！");
        else
            System.out.println("书籍插入失败，请重新尝试！");
    }

    /**
     删除一本书
     */
    public void deleteBook(String id){
        for (Book book_inset:book_set) //遍历每本书，检查其书号，是否和书相等
        {
            if (id.equals(book_inset.getBook_id())) {//书号相等，已经存在
               book_set.remove(book_inset);//删除目标书籍
                return;//提前结束循环
            }
        }
        System.out.println("所删除书籍不存在，请确认后再试！");
    }

    /**
     删除所有书
     */
    public void clearBook(){
        book_set.clear();
    }

    /**
     查询所有书籍
     */
    public void searchBook(){
        System.out.println("系统中共有"+book_set.size()+"本书，具体信息如下：");
        for (Book book:book_set)
            System.out.println(book.toString());
    }

    /**
     通过名字查询书籍，也可以模糊查询
     */
    public void searchBook_name(){
        System.out.print("请输入要查询书籍的书名:");
        Scanner reader = new Scanner(System.in);
        String name = reader.next();//读取姓名
        String pattern = ".*"+name+".*";//正则表达式结果
        ArrayList<Book> find_result = new ArrayList<Book>();//记录查询结果
        for (Book book_inset:book_set) //遍历每本书，检查其书名，是否和书相等
        {
            if (Pattern.matches(pattern, book_inset.getBook_name())) {//检查书名是否包含关键字
                find_result.add(book_inset);
            }
        }
        if (find_result.size()<=0)
            System.out.println("系统中不存在这样的书籍！");
        else{
            System.out.println("检索到"+find_result.size()+"本书，具体信息如下：");
            for (Book book_find:find_result) {
                System.out.println(book_find.toString());
            }
        }
    }

    /**
     通过书号查询书籍
     */
    public void searchBook_id() {
        System.out.print("请输入要查询书籍的书号:");
        Scanner reader = new Scanner(System.in);
        String id = reader.next();//读取书号
        for (Book book_inset : book_set) //遍历每本书，检查其书名，是否和书相等
        {
            if (id.equals(book_inset.getBook_id())) {//检查书名是否包含关键字
                System.out.println("检索成功！书籍信息如下：");
                System.out.println(book_inset.toString());
                return;
            }
        }
        System.out.println("系统中不存在这样的书籍！");
    }

    /**
     通过作者查询书籍，也可以模糊查询
     */
    public void searchBook_author(){
        System.out.print("请输入要查询书籍的作者名:");
        Scanner reader = new Scanner(System.in);
        String author = reader.next();//读取作者
        String pattern = ".*"+author+".*";//正则表达式结果
        ArrayList<Book> find_result = new ArrayList<Book>();//记录查询结果
        for (Book book_inset:book_set) //遍历每本书，检查作者，是否和书相等
        {
            if (Pattern.matches(pattern, book_inset.getAuthor())) {//检查作者名是否包含关键字
                find_result.add(book_inset);
            }
        }
        if (find_result.size()<=0)
            System.out.println("系统中不存在这样的书籍！");
        else{
            System.out.println("检索到"+find_result.size()+"本书，具体信息如下：");
            for (Book book_find:find_result) {
                System.out.println(book_find.toString());
            }
        }
    }

    /**
     通过出版社查询书籍，也可以模糊查询
     */
    public void searchBook_publisher(){
        System.out.print("请输入要查询书籍的出版社名:");
        Scanner reader = new Scanner(System.in);
        String publisher = reader.next();//读取出版社
        String pattern = ".*"+publisher+".*";//正则表达式结果
        ArrayList<Book> find_result = new ArrayList<Book>();//记录查询结果
        for (Book book_inset:book_set) //遍历每本书，检查出版社，是否和书相等
        {
            if (Pattern.matches(pattern, book_inset.getPublisher())) {//检查出版社是否包含关键字
                find_result.add(book_inset);
            }
        }
        if (find_result.size()<=0)
            System.out.println("系统中不存在这样的书籍！");
        else{
            System.out.println("检索到"+find_result.size()+"本书，具体信息如下：");
            for (Book book_find:find_result) {
                System.out.println(book_find.toString());
            }
        }
    }

}
