import BasicElements.Borrow;
import BasicElements.Librarian;
import BasicElements.Reader;
import System.*;

import java.util.ArrayList;
import java.util.Scanner;



/**
 ����ͼ��ݵ����̿��
 */
public class Library {

    public static void LibraryFrame() {

        CenterSystem centerSystem = new CenterSystem();
        MiniSystem_Reader miniSystemreader = new MiniSystem_Reader();
        MiniSystem_Librarian miniSystem_librarian = new MiniSystem_Librarian();
        Scanner reader_in = new Scanner(System.in);
        /**
         ѡ���¼��ʽ
         **/
        logscreen://��¼����
        while (true) {
            AssistMethods.clearScreen();
            System.out.println("\033[1;93;45m" + "��ӭʹ��ͼ��ݹ���ϵͳ" + "\033[m" + "\n");
            System.out.println("1�����ߵ�¼");
            System.out.println("2������Ա��¼");
            System.out.println("3������ע��");
            System.out.println("0���˳�ϵͳ");
            System.out.print("������ѡ��");
            int choice_log = reader_in.nextInt();//��ȡ�û�ѡ��
            while (choice_log < 0 || choice_log > 3) {//�����ܣ�����û������Ƿ���ȷ
                System.out.print("\033[31m" + "����������������룺" + "\033[m");
                choice_log = reader_in.nextInt();//�ٴζ�ȡ�û�ѡ��
            }

            //���ߵ�½--�������Ȩ��
            AssistMethods.clearScreen();
            if (choice_log == 1) {
                System.out.println("\033[1;93;45m" + "��ӭʹ��ͼ��ݹ���ϵͳ" + "\033[m");
                System.out.println("\033[1;95m" + "�����ߵ�¼��" + "\033[m" + "\n");
                System.out.print("�������˺ţ�");//�˺�
                String reader_id = reader_in.next();
                while (!miniSystemreader.isIdExist(reader_id)) {//���id�Ƿ����
                    System.out.print("�˺����벻���ڣ���ȷ�Ϻ��ٴ����루�����ʱ�뷵���Ͻ��棬������0)��");
                    reader_id = reader_in.next();
                    if (reader_id.equals("0"))
                        continue logscreen;
                }
                System.out.print("���������룺");//�˺�
                String reader_password = reader_in.next();
                while (!reader_password.equals(miniSystemreader.getPasswold(reader_id))) {//��ϵͳ�в�������id�������Ƿ�ƥ��
                    System.out.print("���������ȷ�Ϻ��ٴ��������루�����ʱ�뷵���Ͻ��棬������0)��");//�˺�
                    reader_password = reader_in.next();
                    if (reader_password.equals("0"))
                        continue logscreen;
                }
                System.out.println("\033[1;94m" + "��¼�ɹ�,��ת��......" + "\033[m");
                AssistMethods.sleepSystem(3000);

                Reader reader_login = miniSystemreader.getReader(reader_id, reader_password);//��ȡ�����ʼ��Library
                centerSystem.setReaderLogin(reader_login);
                AssistMethods.clearScreen();
                readerScreen://���ߵ�½�����
                    while (true) {
                    AssistMethods.clearScreen();
                    System.out.println("\033[1;93;45m" + "��ǰģʽ������ģʽ" + "\033[m");
                    System.out.println("\033[1;95m" + "��ӭ  " + reader_login.getReader_name() + "  ��¼ͼ�����ϵͳ" + "\033[m" + "\n");
                    System.out.println("1���鼮��ѯ");
                    System.out.println("2������ͼ��");
                    System.out.println("3���黹ͼ��");
                    System.out.println("4���鿴����ͼ��");
                    System.out.println("5���˺�ע��");
                    System.out.println("0���˳���¼");
                    System.out.print("������ѡ��");
                    int choice_operation = reader_in.nextInt();
                    if (choice_operation == 1) {
                        AssistMethods.clearScreen();
                        centerSystem.findBooks();
                    } else if (choice_operation == 2) {
                        AssistMethods.clearScreen();
                        centerSystem.borrowBook_id();
                    } else if (choice_operation == 3) {
                        AssistMethods.clearScreen();
                        centerSystem.returnBook_id();
                    } else if (choice_operation == 4) {
                        AssistMethods.clearScreen();
                        ArrayList<Borrow> information = centerSystem.getBorrowInformation_user();//��ȡ������Ϣ
                        if (information.size()<=0)
                            System.out.println("��ǰ�˺��޽�����Ϣ");
                        else {
                            System.out.println("��ǰ�˺Ž�����Ϣ����");
                            for (Borrow borrow : information)
                                System.out.println(borrow.toString());
                        }
                    } else if (choice_operation == 5) {
                        AssistMethods.clearScreen();
                        ArrayList<Borrow>information = centerSystem.getBorrowInformation_user();//��ȡ������Ϣ
                        if (information.size()>0){//���ڻ��н����û�л�
                            System.out.println("��ǰ������δ���鼮���޷�ע���˺�");
                            System.out.println("��ת��......");
                            AssistMethods.sleepSystem(3000);
                            continue readerScreen;//���ص����ߵ�¼�����
                        }
                        else {
                            miniSystemreader.deleteReader(reader_login);
                            centerSystem.setReaderLogin(null);//�˳���¼�ĵ�ǰ�û�
                            System.out.println("�˺�ע���ɹ�����ת��......");
                            AssistMethods.sleepSystem(3000);
                            continue logscreen;//���ص���¼����
                        }
                    } else if (choice_operation == 0) {
                        centerSystem.setReaderLogin(null);//�˳���¼�ĵ�ǰ�û�
                        System.out.println("�˳���¼����ת��......");
                        AssistMethods.sleepSystem(3000);
                        continue logscreen;//�ص���¼����
                    }
                    System.out.println("�����κ�������ת��");//��Ҫ��ֹ�������������ת��
                    reader_in.nextInt();
                    System.out.println("��ת��......");
                    AssistMethods.sleepSystem(1500);

                }
            }

            //�����½--�������ԱȨ��
            AssistMethods.clearScreen();
            if (choice_log == 2) {
                System.out.println("\033[1;93;45m" + "��ӭʹ��ͼ��ݹ���ϵͳ" + "\033[m");
                System.out.println("\033[1;95m" + "������Ա��¼��" + "\033[m" + "\n");
                System.out.print("�������˺ţ�");//�˺�
                String librarian_id = reader_in.next();
                while (!miniSystem_librarian.isIdExist(librarian_id)) {//���id�Ƿ����
                    System.out.print("�˺����벻���ڣ���ȷ�Ϻ��ٴ����루�����ʱ�뷵���Ͻ��棬������0)��");
                    librarian_id = reader_in.next();
                    if (librarian_id.equals("0"))
                        continue logscreen;
                }
                System.out.print("���������룺");//�˺�
                String librarian_password = reader_in.next();
                while (!librarian_password.equals(miniSystem_librarian.getPasswold(librarian_id))) {//��ϵͳ�в�������id�������Ƿ�ƥ��
                    System.out.print("���������ȷ�Ϻ��ٴ��������루�����ʱ�뷵���Ͻ��棬������0)��");//�˺�
                    librarian_password = reader_in.next();
                    if (librarian_password.equals("0"))
                        continue logscreen;
                }
                System.out.println("\033[1;94m"+"��¼�ɹ�����ת��......"+"\033[m");
                AssistMethods.sleepSystem(3000);

                Librarian librarian_login = miniSystem_librarian.getLibrarian(librarian_id,librarian_password);//��ȡ�����ʼ��Library
                centerSystem.setLibrarianLogin(librarian_login);
                librarianScreen://����Ա��½�����
                    while (true){
                    AssistMethods.clearScreen();
                    System.out.println("\033[1;93;45m" + "��ǰģʽ������Աģʽ" + "\033[m");
                    System.out.println("\033[1;95m" + "��ӭ  "+librarian_login.getUser_name()+"  ��¼ͼ�����ϵͳ"+ "\033[m" + "\n");
                    System.out.println("1���鼮��ѯ");
                    System.out.println("2��ɾ��ͼ��");
                    System.out.println("3������ͼ��");
                    System.out.println("4����տ��");
                    System.out.println("5���鿴������Ϣ");
                    System.out.println("0���˳���¼");
                    System.out.print("������ѡ��");
                    int choice_operation = reader_in.nextInt();
                    if (choice_operation==1){
                        AssistMethods.clearScreen();
                        centerSystem.findBooks();
                    }
                    else if (choice_operation == 2) {
                        AssistMethods.clearScreen();
                        centerSystem.deleteBooks();
                    }
                    else if (choice_operation == 3){
                        AssistMethods.clearScreen();
                        centerSystem.addBooks();
                    }
                    else if (choice_operation == 4){
                        AssistMethods.clearScreen();
                        centerSystem.clearBooks();
                    }
                    else if (choice_operation == 5){
                        AssistMethods.clearScreen();
                        centerSystem.getBorrowInformation_all();
                    }
                    else if (choice_operation == 0){
                        centerSystem.setLibrarianLogin(null);
                        System.out.println("�˳���¼����ת��......");
                        AssistMethods.sleepSystem(3000);
                        continue logscreen;//�ص���¼����
                    }
                    System.out.print("�����κ����ּ���������");//��Ҫ��ֹ�������������ת��
                    reader_in.nextInt();
                    System.out.println("��ת��......");
                    AssistMethods.sleepSystem(1500);
                }
            }

            //����ע��
            AssistMethods.clearScreen();
            if (choice_log == 3) {

                System.out.println("\033[1;93;45m" + "��ӭʹ��ͼ��ݹ���ϵͳ" + "\033[m");
                System.out.println("\033[1;95m" + "������ע�᡿" + "\033[m" + "\n");
                Reader reader_login = miniSystemreader.addReader();
                System.out.println("\033[30m" + "���ڵ�½����ת��......" + "\033[m");
                AssistMethods.sleepSystem(3000);

                centerSystem.setReaderLogin(reader_login);
                readerScreen://���ߵ�½�����
                    while (true) {
                    AssistMethods.clearScreen();
                    System.out.println("\033[1;93;45m" + "��ǰģʽ������ģʽ" + "\033[m");
                    System.out.println("\033[1;95m" + "��ӭ  " + reader_login.getReader_name() + "  ��¼ͼ�����ϵͳ" + "\033[m" + "\n");
                    System.out.println("1���鼮��ѯ");
                    System.out.println("2������ͼ��");
                    System.out.println("3���黹ͼ��");
                    System.out.println("4���鿴����ͼ��");
                    System.out.println("5���˺�ע��");
                    System.out.println("0���˳���¼");
                    System.out.print("������ѡ��");
                    int choice_operation = reader_in.nextInt();
                    if (choice_operation == 1) {
                        AssistMethods.clearScreen();
                        centerSystem.findBooks();
                    } else if (choice_operation == 2) {
                        AssistMethods.clearScreen();
                        centerSystem.borrowBook_id();
                    } else if (choice_operation == 3) {
                        AssistMethods.clearScreen();
                        centerSystem.returnBook_id();
                    } else if (choice_operation == 4) {
                        AssistMethods.clearScreen();
                        ArrayList<Borrow> information = centerSystem.getBorrowInformation_user();//��ȡ������Ϣ
                        if (information.size()<=0)
                            System.out.println("��ǰ�˺��޽�����Ϣ");
                        else {
                            System.out.println("��ǰ�˺Ž�����Ϣ����");
                            for (Borrow borrow : information)
                                System.out.println(borrow.toString());
                        }
                    } else if (choice_operation == 5) {
                        AssistMethods.clearScreen();
                        ArrayList<Borrow>information = centerSystem.getBorrowInformation_user();//��ȡ������Ϣ
                        if (information.size()>0){//���ڻ��н����û�л�
                            System.out.println("��ǰ������δ���鼮���޷�ע���˺�");
                            System.out.println("��ת��......");
                            AssistMethods.sleepSystem(3000);
                            continue readerScreen;//���ص����ߵ�¼�����
                        }
                        else {
                            miniSystemreader.deleteReader(reader_login);
                            centerSystem.setReaderLogin(null);//�˳���¼�ĵ�ǰ�û�
                            System.out.println("�˺�ע���ɹ�����ת��......");
                            AssistMethods.sleepSystem(3000);
                            continue logscreen;//���ص���¼����
                        }
                    } else if (choice_operation == 0) {
                        centerSystem.setReaderLogin(null);//�˳���¼�ĵ�ǰ�û�
                        System.out.println("�˳���¼����ת��......");
                        AssistMethods.sleepSystem(3000);
                        continue logscreen;//�ص���¼����
                    }
                    System.out.println("�����κ�������ת��");//��Ҫ��ֹ�������������ת��
                    reader_in.nextInt();
                    System.out.println("��ת��......");
                    AssistMethods.sleepSystem(1500);

                }
            }

            //�˳�
            AssistMethods.clearScreen();
            if (choice_log == 0)
                return;
        }

    }
    
}
