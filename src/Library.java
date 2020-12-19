import BasicElements.Borrow;
import BasicElements.Librarian;
import BasicElements.Reader;
import System.*;

import java.util.ArrayList;
import java.util.Scanner;



/**
 整个图书馆的流程框架
 */
public class Library {

    public static void LibraryFrame() {

        CenterSystem centerSystem = new CenterSystem();
        MiniSystem_Reader miniSystemreader = new MiniSystem_Reader();
        MiniSystem_Librarian miniSystem_librarian = new MiniSystem_Librarian();
        Scanner reader_in = new Scanner(System.in);
        /**
         选择登录方式
         **/
        logscreen://登录界面
        while (true) {
            AssistMethods.clearScreen();
            System.out.println("\033[1;93;45m" + "欢迎使用图书馆管理系统" + "\033[m" + "\n");
            System.out.println("1：读者登录");
            System.out.println("2：管理员登录");
            System.out.println("3：读者注册");
            System.out.println("0：退出系统");
            System.out.print("请输入选择：");
            int choice_log = reader_in.nextInt();//读取用户选择
            while (choice_log < 0 || choice_log > 3) {//纠错功能，检查用户输入是否正确
                System.out.print("\033[31m" + "输入错误，请重新输入：" + "\033[m");
                choice_log = reader_in.nextInt();//再次读取用户选择
            }

            //读者登陆--代表读者权限
            AssistMethods.clearScreen();
            if (choice_log == 1) {
                System.out.println("\033[1;93;45m" + "欢迎使用图书馆管理系统" + "\033[m");
                System.out.println("\033[1;95m" + "【读者登录】" + "\033[m" + "\n");
                System.out.print("请输入账号：");//账号
                String reader_id = reader_in.next();
                while (!miniSystemreader.isIdExist(reader_id)) {//检测id是否存在
                    System.out.print("账号输入不存在，请确认后再次输入（如果此时想返回上界面，请输入0)：");
                    reader_id = reader_in.next();
                    if (reader_id.equals("0"))
                        continue logscreen;
                }
                System.out.print("请输入密码：");//账号
                String reader_password = reader_in.next();
                while (!reader_password.equals(miniSystemreader.getPasswold(reader_id))) {//在系统中查找输入id和密码是否匹配
                    System.out.print("密码错误，请确认后再次输入密码（如果此时想返回上界面，请输入0)：");//账号
                    reader_password = reader_in.next();
                    if (reader_password.equals("0"))
                        continue logscreen;
                }
                System.out.println("\033[1;94m" + "登录成功,跳转中......" + "\033[m");
                AssistMethods.sleepSystem(3000);

                Reader reader_login = miniSystemreader.getReader(reader_id, reader_password);//获取对象初始化Library
                centerSystem.setReaderLogin(reader_login);
                AssistMethods.clearScreen();
                readerScreen://读者登陆后界面
                    while (true) {
                    AssistMethods.clearScreen();
                    System.out.println("\033[1;93;45m" + "当前模式：读者模式" + "\033[m");
                    System.out.println("\033[1;95m" + "欢迎  " + reader_login.getReader_name() + "  登录图书管理系统" + "\033[m" + "\n");
                    System.out.println("1：书籍查询");
                    System.out.println("2：借阅图书");
                    System.out.println("3：归还图书");
                    System.out.println("4：查看借阅图书");
                    System.out.println("5：账号注销");
                    System.out.println("0：退出登录");
                    System.out.print("请输入选择：");
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
                        ArrayList<Borrow> information = centerSystem.getBorrowInformation_user();//获取借阅信息
                        if (information.size()<=0)
                            System.out.println("当前账号无借阅信息");
                        else {
                            System.out.println("当前账号借阅信息如下");
                            for (Borrow borrow : information)
                                System.out.println(borrow.toString());
                        }
                    } else if (choice_operation == 5) {
                        AssistMethods.clearScreen();
                        ArrayList<Borrow>information = centerSystem.getBorrowInformation_user();//获取借阅信息
                        if (information.size()>0){//存在还有借的书没有还
                            System.out.println("当前还存在未还书籍，无法注销账号");
                            System.out.println("跳转中......");
                            AssistMethods.sleepSystem(3000);
                            continue readerScreen;//返回到读者登录后界面
                        }
                        else {
                            miniSystemreader.deleteReader(reader_login);
                            centerSystem.setReaderLogin(null);//退出记录的当前用户
                            System.out.println("账号注销成功！跳转中......");
                            AssistMethods.sleepSystem(3000);
                            continue logscreen;//返回到登录界面
                        }
                    } else if (choice_operation == 0) {
                        centerSystem.setReaderLogin(null);//退出记录的当前用户
                        System.out.println("退出登录！跳转中......");
                        AssistMethods.sleepSystem(3000);
                        continue logscreen;//回到登录界面
                    }
                    System.out.println("输入任何数字跳转：");//主要防止看不到结果就跳转了
                    reader_in.nextInt();
                    System.out.println("跳转中......");
                    AssistMethods.sleepSystem(1500);

                }
            }

            //管理登陆--代表管理员权限
            AssistMethods.clearScreen();
            if (choice_log == 2) {
                System.out.println("\033[1;93;45m" + "欢迎使用图书馆管理系统" + "\033[m");
                System.out.println("\033[1;95m" + "【管理员登录】" + "\033[m" + "\n");
                System.out.print("请输入账号：");//账号
                String librarian_id = reader_in.next();
                while (!miniSystem_librarian.isIdExist(librarian_id)) {//检测id是否存在
                    System.out.print("账号输入不存在，请确认后再次输入（如果此时想返回上界面，请输入0)：");
                    librarian_id = reader_in.next();
                    if (librarian_id.equals("0"))
                        continue logscreen;
                }
                System.out.print("请输入密码：");//账号
                String librarian_password = reader_in.next();
                while (!librarian_password.equals(miniSystem_librarian.getPasswold(librarian_id))) {//在系统中查找输入id和密码是否匹配
                    System.out.print("密码错误，请确认后再次输入密码（如果此时想返回上界面，请输入0)：");//账号
                    librarian_password = reader_in.next();
                    if (librarian_password.equals("0"))
                        continue logscreen;
                }
                System.out.println("\033[1;94m"+"登录成功，跳转中......"+"\033[m");
                AssistMethods.sleepSystem(3000);

                Librarian librarian_login = miniSystem_librarian.getLibrarian(librarian_id,librarian_password);//获取对象初始化Library
                centerSystem.setLibrarianLogin(librarian_login);
                librarianScreen://管理员登陆后界面
                    while (true){
                    AssistMethods.clearScreen();
                    System.out.println("\033[1;93;45m" + "当前模式：管理员模式" + "\033[m");
                    System.out.println("\033[1;95m" + "欢迎  "+librarian_login.getUser_name()+"  登录图书管理系统"+ "\033[m" + "\n");
                    System.out.println("1：书籍查询");
                    System.out.println("2：删除图书");
                    System.out.println("3：新增图书");
                    System.out.println("4：清空库存");
                    System.out.println("5：查看借阅信息");
                    System.out.println("0：退出登录");
                    System.out.print("请输入选择：");
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
                        System.out.println("退出登录！跳转中......");
                        AssistMethods.sleepSystem(3000);
                        continue logscreen;//回到登录界面
                    }
                    System.out.print("输入任何数字继续操作：");//主要防止看不到结果就跳转了
                    reader_in.nextInt();
                    System.out.println("跳转中......");
                    AssistMethods.sleepSystem(1500);
                }
            }

            //读者注册
            AssistMethods.clearScreen();
            if (choice_log == 3) {

                System.out.println("\033[1;93;45m" + "欢迎使用图书馆管理系统" + "\033[m");
                System.out.println("\033[1;95m" + "【读者注册】" + "\033[m" + "\n");
                Reader reader_login = miniSystemreader.addReader();
                System.out.println("\033[30m" + "正在登陆，跳转中......" + "\033[m");
                AssistMethods.sleepSystem(3000);

                centerSystem.setReaderLogin(reader_login);
                readerScreen://读者登陆后界面
                    while (true) {
                    AssistMethods.clearScreen();
                    System.out.println("\033[1;93;45m" + "当前模式：读者模式" + "\033[m");
                    System.out.println("\033[1;95m" + "欢迎  " + reader_login.getReader_name() + "  登录图书管理系统" + "\033[m" + "\n");
                    System.out.println("1：书籍查询");
                    System.out.println("2：借阅图书");
                    System.out.println("3：归还图书");
                    System.out.println("4：查看借阅图书");
                    System.out.println("5：账号注销");
                    System.out.println("0：退出登录");
                    System.out.print("请输入选择：");
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
                        ArrayList<Borrow> information = centerSystem.getBorrowInformation_user();//获取借阅信息
                        if (information.size()<=0)
                            System.out.println("当前账号无借阅信息");
                        else {
                            System.out.println("当前账号借阅信息如下");
                            for (Borrow borrow : information)
                                System.out.println(borrow.toString());
                        }
                    } else if (choice_operation == 5) {
                        AssistMethods.clearScreen();
                        ArrayList<Borrow>information = centerSystem.getBorrowInformation_user();//获取借阅信息
                        if (information.size()>0){//存在还有借的书没有还
                            System.out.println("当前还存在未还书籍，无法注销账号");
                            System.out.println("跳转中......");
                            AssistMethods.sleepSystem(3000);
                            continue readerScreen;//返回到读者登录后界面
                        }
                        else {
                            miniSystemreader.deleteReader(reader_login);
                            centerSystem.setReaderLogin(null);//退出记录的当前用户
                            System.out.println("账号注销成功！跳转中......");
                            AssistMethods.sleepSystem(3000);
                            continue logscreen;//返回到登录界面
                        }
                    } else if (choice_operation == 0) {
                        centerSystem.setReaderLogin(null);//退出记录的当前用户
                        System.out.println("退出登录！跳转中......");
                        AssistMethods.sleepSystem(3000);
                        continue logscreen;//回到登录界面
                    }
                    System.out.println("输入任何数字跳转：");//主要防止看不到结果就跳转了
                    reader_in.nextInt();
                    System.out.println("跳转中......");
                    AssistMethods.sleepSystem(1500);

                }
            }

            //退出
            AssistMethods.clearScreen();
            if (choice_log == 0)
                return;
        }

    }
    
}
