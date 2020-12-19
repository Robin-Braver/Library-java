package System;

import BasicElements.Librarian;

import java.util.ArrayList;

/**
 �������Ա��Ϣ���ڴ�
 */
public class MiniSystem_Librarian {
    private ArrayList<Librarian>librarians_array;//ʹ��ArrayList��Ź���Ա��Ϣ
    private String filename_librarian ="C:\\Users\\23862\\Desktop\\ѧϰ\\��ѧ\\Java\\�γ����\\Librarian.txt";//��Ź���Ա��Ϣ��txt�ı�

    /**
     ���췽����������Ա��Ϣ��ȡ���ڴ���
     */
    public MiniSystem_Librarian(){
        librarians_array = new ArrayList<Librarian>();//��ʼ���洢��librarians_array
        ArrayList<String[]> librarianArray = AssistMethods.readfile(filename_librarian);
        for (int i=0;i<librarianArray.size();++i)
        {
            //������ȡ��ӦԪ��
            String librarian_id = librarianArray.get(i)[0];
            String librarian_name = librarianArray.get(i)[1];
            String sex = librarianArray.get(i)[2];
            String password = librarianArray.get(i)[3];
            Librarian librarian_insert = new Librarian(librarian_id,librarian_name,sex,password);//��ʼ����ȡ������Ϣ
            librarians_array.add(librarian_insert);
        }
    }

    /**
     ��ѯ�˺��Ƿ���ϵͳ��
     */
    public boolean isIdExist(String _id) {
        for (Librarian librarian : librarians_array) {//��������id
            if (_id.equals(librarian.getUser_id())) {//idһ��
                return true;//�˺Ŵ���
            }
        }
        return false;//�˺Ų�����
    }

    /**
     ��ȡ���룬�����û���¼ʱ����֤�������롣
     */
    public String getPasswold(String id) {
        for (Librarian librarian : librarians_array) {//��������id
            if (id.equals(librarian.getUser_id())) {//idһ��
                return librarian.getPassword();//��������
            }
        }
        return null;//���Ҳ��������ؿգ�һ���ò�����һ������Ϊ����ǰ�ж��˺��Ƿ����
    }

    /**
     * ͨ���˺ź������ȡ���librarian����,���ڵ�½
     */
    public Librarian getLibrarian(String id, String pass){
        for (Librarian librarian:librarians_array) {
            if (librarian.getUser_id().equals(id) && librarian.getPassword().equals(pass))
                return librarian;
        }
        return null;
    }



}
