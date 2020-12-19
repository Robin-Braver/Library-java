package System;

import BasicElements.Borrow;


import java.util.ArrayList;

/**
 导入借阅信息入内存
 */
public class MiniSystem_Borrow {
    ArrayList<Borrow> borrows;//使用ArrayList存放借阅信息
    private String fileName_borrow = "C:\\Users\\23862\\Desktop\\学习\\已学\\Java\\课程设计\\Borrow.txt";

    /**
     构造方法，将借阅信息读取到内存中
     */
    MiniSystem_Borrow(){
        borrows = new ArrayList<Borrow>();//初始化存储的borrows
        ArrayList<String[]> borrowArray = AssistMethods.readfile(fileName_borrow);
        for (int i=0;i<borrowArray.size();++i)
        {
            //依次提取相应元素
            String reader_id = borrowArray.get(i)[0];
            String reader_name = borrowArray.get(i)[1];
            String book_id = borrowArray.get(i)[2];
            String book_name = borrowArray.get(i)[3];
            String time = borrowArray.get(i)[4];
            int num = Integer.valueOf(borrowArray.get(i)[5]);
            Borrow borrow_insert = new Borrow(reader_id,reader_name,book_id,book_name,time,num);//舒适化插入数据
            borrows.add(borrow_insert);//插入元素
        }
    }

    /**
     打印所有借阅信息
     */
    public void Search(){
        for (Borrow borrow:borrows) {//遍历元素
           if (borrow.isOverdue()){//已经逾期了
                System.out.println("\033[31m"+borrow.toString());
            }
           else{
               System.out.println("\033[30m"+borrow.toString());
           }
        }
    }


}




