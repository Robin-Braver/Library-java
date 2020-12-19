package System;

import BasicElements.Reader;

import java.util.ArrayList;
import java.util.Scanner;


/**
 ���������Ϣ���ڴ�
 */
public class MiniSystem_Reader {
    private ArrayList<Reader> readers;//ʹ��hashset��Ŷ���
    private String fileName_reader = "C:\\Users\\23862\\Desktop\\ѧϰ\\��ѧ\\Java\\�γ����\\Reader.txt";

    /**
     ���췽������������Ϣ��ȡ���ڴ���
     */
    public MiniSystem_Reader(){
        readers = new ArrayList<Reader>();//��ʼ���洢��readers
        ArrayList<String[]> librarianArray = AssistMethods.readfile(fileName_reader);
        for (int i=0;i<librarianArray.size();++i)
        {
            //������ȡ��ӦԪ��
            String reader_id = librarianArray.get(i)[0];
            String reader_name = librarianArray.get(i)[1];
            String kind = librarianArray.get(i)[2];
            String sex = librarianArray.get(i)[3];
            String password = librarianArray.get(i)[4];
            Reader reader_insert = new Reader(reader_id,reader_name,kind,sex,password);//��ʼ����ȡ����Ԫ��
            readers.add(reader_insert);//����Ԫ��
        }
    }

    /**
     ��ѯ�˺��Ƿ���ϵͳ��
     */
    public boolean isIdExist(String _id) {
        for (Reader reader : readers) {//��������id
            if (_id.equals(reader.getReader_id())) {//idһ��
                return true;//�˺Ŵ���
            }
        }
        return false;//�˺Ų�����
    }

    /**
     ��ȡ���룬�����û���¼ʱ����֤�������롣
     */
    public String getPasswold(String id) {
        for (Reader reader : readers) {//��������id
            if (id.equals(reader.getReader_id())) {//idһ��
                return reader.getPassword();//��������
            }
        }
        return null;//���Ҳ��������ؿգ�һ���ò�����һ������Ϊ����ǰ�ж��˺��Ƿ����
    }

    /**
     * ͨ���˺ź������ȡ���reader����,���ڵ�½
     */
    public Reader getReader(String id,String pass){
        for (Reader reader:readers) {
            if (reader.getReader_id().equals(id) && reader.getPassword().equals(pass))
                return reader;
        }
        return null;
    }


    /**
     �½�һ���˺�
     */
    public Reader addReader(){
        System.out.println("�������Զ����˺ţ�");
        Scanner reader = new Scanner(System.in);
        String id = reader.next();
        boolean isExist = isIdExist(id);//����˺��Ƿ��Ѿ���ע��
        while (isExist) {
            System.out.println("���˺��ѱ�ע�ᣬ�����������˺�");
            id = reader.next();//�ٴ�����
            isExist = isIdExist(id);//�ٴμ���˺��Ƿ��Ѿ���ע��
        }
        System.out.println("��������ʵ������");
        String name = reader.next();
        System.out.println("��������ݣ�");
        String kind = reader.next();
        System.out.println("�������Ա�");
        String sex = reader.next();
        System.out.println("���������룺");
        String password1 = reader.next();
        System.out.println("��ȷ�ϸո���������룺");
        String password2 = reader.next();
        boolean equality = password2.equals(password1);//������������Ƿ����
        if (!equality){//�������벻���
            System.out.println("���������֮ǰ���벻��ȣ����ٴ��������룺");
            password2 = reader.next();
            equality = password2.equals(password1);//�ٴμ�����������Ƿ����
        }
        Reader reader_new = new Reader(id,name,kind,sex,password1);//��ʼ���õ�����Ϣ
        readers.add(reader_new);//������ע����û�
        System.out.println("ע��ɹ���");
        return reader_new;
    }


    /**
     ͨ��reader����ע��һ���˺�
     */
    public boolean deleteReader(Reader reader){
        return readers.remove(reader);//ɾ���˺�
    }
}
