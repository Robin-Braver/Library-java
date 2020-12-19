package System;

import BasicElements.Book;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
���ļ���ȡͼ����鼮��Ϣ�������鼮����Ӧ����
 */
public  class MiniSystem_Book {
    HashSet<Book> book_set;//ʹ��hashset����鼮
    private String fileName_book = "C:\\Users\\23862\\Desktop\\ѧϰ\\��ѧ\\Java\\�γ����\\Book.txt";//��ȡ�ļ�λ��

    /**
     ���췽�������ļ��鼮��ȡ���ڴ���
     */
    public MiniSystem_Book(){
        book_set = new HashSet<Book>();//��ʼ���洢��hashset
        ArrayList<String[]> bookArray = AssistMethods.readfile(fileName_book);
        for (int i=0;i<bookArray.size();++i)
        {
            //������ȡ��ӦԪ��
            String book_id = bookArray.get(i)[0];
            String book_name = bookArray.get(i)[1];
            String author= bookArray.get(i)[2];
            int currentNum= Integer.valueOf(bookArray.get(i)[3]);
            int totalNum= Integer.valueOf(bookArray.get(i)[4]);
            String publisher= bookArray.get(i)[5];
            String publish= bookArray.get(i)[6];
            Book book_insert = new Book(book_id,book_name,author,currentNum,totalNum,publisher,publish);//��ʼ���鼮��
            book_set.add(book_insert);//����ʼ���õ��鼮���뵽hashset��
        }
    }

    /**
     ���һ����
     */
    public void addBook(){
        System.out.print("������Ҫ�����鼮����ţ�");
        Scanner reader = new Scanner(System.in);
        String id = reader.next();//��ȡid
        for (Book book_inset:book_set) //����ÿ���飬�������ţ��Ƿ�������
        {
            if (id.equals(book_inset.getBook_id())) {//�����ȣ��Ѿ�����
                System.out.print("���鼮�Ѿ�����ϵͳ�У���ֱ��������Ҫ�ɱ�����������");
                int numInput = reader.nextInt();
                book_inset.setCurrentNum(book_inset.getCurrentNum()+numInput);//���µ�ǰ����
                book_inset.setTotalNum(book_inset.getTotalNum()+numInput);//���²��������
                return;//��ǰ����ѭ��
            }
        }
        System.out.print("������Ҫ�����鼮��������");
        String name = reader.next();
        System.out.print("������Ҫ�����鼮�����ߣ�");
        String author = reader.next();
        System.out.print("��������Ҫ�ɱ�����������");
        int numInput = reader.nextInt();
        System.out.print("������Ҫ�����鼮�ĳ����磺");
        String publish = reader.next();
        System.out.print("������Ҫ�����鼮�ĳ������ڣ�");
        String publisher = reader.next();
        Book book_insert = new Book(id,name,author,numInput,numInput,publish,publisher);
        boolean isDone = book_set.add(book_insert);
        if (isDone)
            System.out.println("�鼮����ɹ���");
        else
            System.out.println("�鼮����ʧ�ܣ������³��ԣ�");
    }

    /**
     ɾ��һ����
     */
    public void deleteBook(String id){
        for (Book book_inset:book_set) //����ÿ���飬�������ţ��Ƿ�������
        {
            if (id.equals(book_inset.getBook_id())) {//�����ȣ��Ѿ�����
               book_set.remove(book_inset);//ɾ��Ŀ���鼮
                return;//��ǰ����ѭ��
            }
        }
        System.out.println("��ɾ���鼮�����ڣ���ȷ�Ϻ����ԣ�");
    }

    /**
     ɾ��������
     */
    public void clearBook(){
        book_set.clear();
    }

    /**
     ��ѯ�����鼮
     */
    public void searchBook(){
        System.out.println("ϵͳ�й���"+book_set.size()+"���飬������Ϣ���£�");
        for (Book book:book_set)
            System.out.println(book.toString());
    }

    /**
     ͨ�����ֲ�ѯ�鼮��Ҳ����ģ����ѯ
     */
    public void searchBook_name(){
        System.out.print("������Ҫ��ѯ�鼮������:");
        Scanner reader = new Scanner(System.in);
        String name = reader.next();//��ȡ����
        String pattern = ".*"+name+".*";//������ʽ���
        ArrayList<Book> find_result = new ArrayList<Book>();//��¼��ѯ���
        for (Book book_inset:book_set) //����ÿ���飬������������Ƿ�������
        {
            if (Pattern.matches(pattern, book_inset.getBook_name())) {//��������Ƿ�����ؼ���
                find_result.add(book_inset);
            }
        }
        if (find_result.size()<=0)
            System.out.println("ϵͳ�в������������鼮��");
        else{
            System.out.println("������"+find_result.size()+"���飬������Ϣ���£�");
            for (Book book_find:find_result) {
                System.out.println(book_find.toString());
            }
        }
    }

    /**
     ͨ����Ų�ѯ�鼮
     */
    public void searchBook_id() {
        System.out.print("������Ҫ��ѯ�鼮�����:");
        Scanner reader = new Scanner(System.in);
        String id = reader.next();//��ȡ���
        for (Book book_inset : book_set) //����ÿ���飬������������Ƿ�������
        {
            if (id.equals(book_inset.getBook_id())) {//��������Ƿ�����ؼ���
                System.out.println("�����ɹ����鼮��Ϣ���£�");
                System.out.println(book_inset.toString());
                return;
            }
        }
        System.out.println("ϵͳ�в������������鼮��");
    }

    /**
     ͨ�����߲�ѯ�鼮��Ҳ����ģ����ѯ
     */
    public void searchBook_author(){
        System.out.print("������Ҫ��ѯ�鼮��������:");
        Scanner reader = new Scanner(System.in);
        String author = reader.next();//��ȡ����
        String pattern = ".*"+author+".*";//������ʽ���
        ArrayList<Book> find_result = new ArrayList<Book>();//��¼��ѯ���
        for (Book book_inset:book_set) //����ÿ���飬������ߣ��Ƿ�������
        {
            if (Pattern.matches(pattern, book_inset.getAuthor())) {//����������Ƿ�����ؼ���
                find_result.add(book_inset);
            }
        }
        if (find_result.size()<=0)
            System.out.println("ϵͳ�в������������鼮��");
        else{
            System.out.println("������"+find_result.size()+"���飬������Ϣ���£�");
            for (Book book_find:find_result) {
                System.out.println(book_find.toString());
            }
        }
    }

    /**
     ͨ���������ѯ�鼮��Ҳ����ģ����ѯ
     */
    public void searchBook_publisher(){
        System.out.print("������Ҫ��ѯ�鼮�ĳ�������:");
        Scanner reader = new Scanner(System.in);
        String publisher = reader.next();//��ȡ������
        String pattern = ".*"+publisher+".*";//������ʽ���
        ArrayList<Book> find_result = new ArrayList<Book>();//��¼��ѯ���
        for (Book book_inset:book_set) //����ÿ���飬�������磬�Ƿ�������
        {
            if (Pattern.matches(pattern, book_inset.getPublisher())) {//���������Ƿ�����ؼ���
                find_result.add(book_inset);
            }
        }
        if (find_result.size()<=0)
            System.out.println("ϵͳ�в������������鼮��");
        else{
            System.out.println("������"+find_result.size()+"���飬������Ϣ���£�");
            for (Book book_find:find_result) {
                System.out.println(book_find.toString());
            }
        }
    }

}
