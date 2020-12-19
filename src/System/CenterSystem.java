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
 �ɽ���ϵͳ�Ͳ�����ϵͳ��ɵ�����ϵͳ
 */
public class CenterSystem {
    private MiniSystem_Borrow miniSystem_borrow;
    private MiniSystem_Book miniSystem_book;

    private Reader readerLogin;//��¼��ǰ��¼���û�
    private Librarian librarianLogin;//��¼��ǰ��¼�Ĺ���Ա

    /**
     ���췽���������ĸ���ϵͳ���죬��ɳ�ʼ������,reader��librarian�ܻ���һ������null����
     */
    public CenterSystem(){
        miniSystem_borrow = new MiniSystem_Borrow();
        miniSystem_book = new MiniSystem_Book();
        readerLogin = null;//��ʼ����null�����½������
        librarianLogin = null;
    }

    public void setReaderLogin(Reader readerLogin) {
        this.readerLogin = readerLogin;
    }

    public void setLibrarianLogin(Librarian librarianLogin) {
        this.librarianLogin = librarianLogin;
    }

    /**
     ���ߣ�ͨ��ͼ��id ����ͼ��
     */
    public void borrowBook_id() {
        System.out.println("��������Ҫ�����鼮���鼮�ţ�");
        Scanner reader_in = new Scanner(System.in);
        String book_id = reader_in.next();//�����鼮��
        for (Book book : miniSystem_book.book_set) {//����ÿһ����
            if (book.getBook_id().equals(book_id)) {//���ҵ������ȵ��鼮
                System.out.println("���ҵ��鼮���鼮��Ϣ���£�");
                System.out.println(book.toString());
                String book_name = book.getBook_name();//��ȡ����ǰ��������������������Ϣ
                System.out.println("���������������");
                int num = reader_in.nextInt();//��������
                while(num>book.getCurrentNum()){//�����������ڿ��
                    System.out.println("�����������ڵ�ǰ���ṩ�������������������");
                    num = reader_in.nextInt();//�ٴζ�������
                }
                book.setCurrentNum(book.getCurrentNum()-num);//�޸��ִ����Ŀ
                Date date = new Date();//��ȡ����ʱ��
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date_string = sdf.format(date);//ת��ʱ��Ϊָ�����ַ�����ʽ
                Borrow borrow_new = new Borrow(readerLogin.getReader_id(),readerLogin.getReader_name(),book_id,book_name,date_string,num);
                miniSystem_borrow.borrows.add(borrow_new);//���ոյĽ�����Ϣ���뵽ϵͳ֮��
                System.out.println("���ĳɹ���");
                return;//��������˳�
            }
        }
        System.out.println("δ���ҵ��鼮����ȷ�Ϻ������ԣ�");
    }

    /**
     ���ߣ��鿴��ǰ���ߵĽ�����Ϣ
     */
    public ArrayList<Borrow> getBorrowInformation_user(){
        ArrayList<Borrow> borrowsInformation = new ArrayList<Borrow>();//��¼������Ϣ������
        for (Borrow borrow:miniSystem_borrow.borrows) {//���������б�
            if (borrow.getReader_id().equals(readerLogin.getReader_id())){//��������Ϣ����͵�¼�ĺ���һ��ʱ
                borrowsInformation.add(borrow);//������������
            }
        }
        return borrowsInformation;//���ؽ��
    }

    /**
     ���ߣ�ͨ��ͼ��id �黹ͼ��
     */
    public void returnBook_id() {
        ArrayList<Borrow> borrowsInformation = getBorrowInformation_user();//�鿴��ǰ�û��Ľ�����Ϣ
        if (borrowsInformation.size()<=0){
            System.out.println("��ǰ�û�û�н����κ��鼮��");
            return;
        }
        System.out.println("��ǰ�û�������Ϣ���£�");
        for(Borrow borrow:borrowsInformation){
            System.out.println(borrow.toString());
        }
        System.out.print("��������Ҫ�黹���鼮�ţ�");
        Scanner reader = new Scanner(System.in);
        String id_return = reader.next();//��ȡid
        System.out.print("��������Ҫ�黹��Ŀ��");
        int num_return = reader.nextInt();//��ȡ�黹��Ŀ
        for(Borrow borrow_insystem:miniSystem_borrow.borrows){//�ٴα������ҵ��鼮:����ʱ��ź������������Լ�����������ǵ�¼���˺�
            if (borrow_insystem.getBook_id().equals(id_return)&&borrow_insystem.getReader_id().equals(readerLogin.getReader_id())){
                while (borrow_insystem.getNum()<num_return||num_return<=0) {//����黹��Ŀ������
                    System.out.print("����黹��Ŀ���������������룺");
                    num_return = reader.nextInt();//�ٴζ�ȡ�黹��Ŀ
                }
                if (num_return == borrow_insystem.getNum()) {//�黹�������н��ĵ��鼮
                    miniSystem_borrow.borrows.remove(borrow_insystem);//ɾ��������Ϣ
                } else {//���޸Ľ����ϵĶ�����Ϣ
                    borrow_insystem.setNum(borrow_insystem.getNum() - num_return);
                }
                for (Book book:miniSystem_book.book_set){//�޸Ĺݲ���Ϣ
                    if (book.getBook_id().equals(id_return)) {
                        book.setCurrentNum(book.getCurrentNum()+num_return);
                        System.out.println("�黹�ɹ�");
                        return;
                    }
                }
            }
        }
        System.out.println("�黹ʧ��");
    }


    /**
     both����ѯͼ�飬֧�ֶ��ַ�ʽ�Ĳ�ѯ
     */
    public void findBooks() {
        System.out.println("\033[1;93;45m" + "��ǰģʽ������ģʽ" + "\033[m");
        System.out.println("��ѡ���Լ��Ĳ�ѯ��ʽ");
        System.out.println("1����ѯ�����鼮");
        System.out.println("2��ͨ��������ѯ��֧��ģ����ѯ��");
        System.out.println("3��ͨ����Ų�ѯ");
        System.out.println("4��ͨ�����߲�ѯ��֧��ģ����ѯ��");
        System.out.println("5��ͨ���������ѯ��֧��ģ����ѯ��");
        System.out.println("0���˳���ѯ");
        System.out.print("������Ҫ��ѯ�ķ�ʽ��");
        Scanner reader = new Scanner(System.in);
        int choice = reader.nextInt();//��ȡ�û�ѡ��
        while(0>choice&&choice>5){//��������Ƿ����
            System.out.println("������������������ٴ����룡");
            System.out.print("��ѡ���Լ��Ĳ�ѯ��ʽ:");
            reader = new Scanner(System.in);
        }
        while(choice!=0){//��ѡ���˳���һֱ���Բ�ѯ
          AssistMethods.clearScreen();
            if (choice==1){
                AssistMethods.clearScreen();
                System.out.println("\033[31m"+"��ǰ��ѯ��ʽ����ѯ�����鼮��");
                System.out.println("\033[30m");
                miniSystem_book.searchBook();
            }
            else if (choice==2){
                AssistMethods.clearScreen();
                System.out.println("\033[31m"+"��ǰ��ѯ��ʽ��ͨ��������ѯ��");
                System.out.println("\033[30m");
                miniSystem_book.searchBook_name();
            }
            else if (choice==3){
                AssistMethods.clearScreen();
                System.out.println("\033[31m"+"��ǰ��ѯ��ʽ��ͨ����Ų�ѯ��");
                System.out.println("\033[30m");
                miniSystem_book.searchBook_id();
            }
            else if (choice==4){
                AssistMethods.clearScreen();
                System.out.println("\033[31m"+"��ǰ��ѯ��ʽ��ͨ�����߲�ѯ��");
                System.out.println("\033[30m");
                miniSystem_book.searchBook_author();
            }
            else if (choice==5){
                AssistMethods.clearScreen();
                System.out.println("\033[31m"+"��ǰ��ѯ��ʽ��ͨ���������ѯ��");
                System.out.println("\033[30m");
                miniSystem_book.searchBook_publisher();
            }
            System.out.println();
            System.out.print("��ѡ�������Ҫ���еĲ�����");
            System.out.println("[1����ѯ�����鼮,2��ͨ��������ѯ,3:ͨ����Ų�ѯ,4��ͨ�����߲�ѯ,5��ͨ���������ѯ,0���˳���ѯ]");
            System.out.print("��ѡ���Լ��Ĳ�ѯ��ʽ��");
            choice = reader.nextInt();//��ȡ�û�ѡ��
        }
    }

    /**
     ����Ա������ɾ��һ����
     */
    public void deleteBooks(){
        System.out.println("������Ҫɾ���鼮����ţ�");
        Scanner reader = new Scanner(System.in);
        String id = reader.next();//��ȡid
        miniSystem_book.deleteBook(id);//������ϵͳɾ��
        int index = 0;
        while (index<miniSystem_borrow.borrows.size()){//ɾ�����ڴ�������н�����Ϣ
            Borrow borrow = miniSystem_borrow.borrows.get(index);
            if (borrow.getBook_id().equals(id)) {
                miniSystem_borrow.borrows.remove(borrow);//ɾ����ؽ�����Ϣ
                continue;
            }++index;//ɾ��������++����Ϊ����Ԫ�ظ����ڱ�
        }
        System.out.println("�鼮ɾ���ɹ���");

    }

    /**
     ����Ա������ ����һ����
     */
    public void addBooks(){
        miniSystem_book.addBook();//������ϵͳ�Ĳ���һ����
    }

    /**
     ����Ա������ɾ��������
     */
    public void clearBooks(){
        miniSystem_book.clearBook();//������ϵͳ�ķ���
        miniSystem_borrow.borrows.clear();//ɾ�����н�����Ϣ
        System.out.println("��ճɹ���");
    }

    /**
     ����Ա�������鿴������Ϣ
     */
    public void getBorrowInformation_all(){
        miniSystem_borrow.Search();//������ϵͳ���鿴������Ϣ
    }



}
