package System;

import BasicElements.Borrow;


import java.util.ArrayList;

/**
 ���������Ϣ���ڴ�
 */
public class MiniSystem_Borrow {
    ArrayList<Borrow> borrows;//ʹ��ArrayList��Ž�����Ϣ
    private String fileName_borrow = "C:\\Users\\23862\\Desktop\\ѧϰ\\��ѧ\\Java\\�γ����\\Borrow.txt";

    /**
     ���췽������������Ϣ��ȡ���ڴ���
     */
    MiniSystem_Borrow(){
        borrows = new ArrayList<Borrow>();//��ʼ���洢��borrows
        ArrayList<String[]> borrowArray = AssistMethods.readfile(fileName_borrow);
        for (int i=0;i<borrowArray.size();++i)
        {
            //������ȡ��ӦԪ��
            String reader_id = borrowArray.get(i)[0];
            String reader_name = borrowArray.get(i)[1];
            String book_id = borrowArray.get(i)[2];
            String book_name = borrowArray.get(i)[3];
            String time = borrowArray.get(i)[4];
            int num = Integer.valueOf(borrowArray.get(i)[5]);
            Borrow borrow_insert = new Borrow(reader_id,reader_name,book_id,book_name,time,num);//���ʻ���������
            borrows.add(borrow_insert);//����Ԫ��
        }
    }

    /**
     ��ӡ���н�����Ϣ
     */
    public void Search(){
        for (Borrow borrow:borrows) {//����Ԫ��
           if (borrow.isOverdue()){//�Ѿ�������
                System.out.println("\033[31m"+borrow.toString());
            }
           else{
               System.out.println("\033[30m"+borrow.toString());
           }
        }
    }


}




