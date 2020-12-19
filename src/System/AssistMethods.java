package System;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

/**
 辅助系统实现的一些方法
 */

public class AssistMethods<staric> {
    /**
     清屏操作，设置在idea中ESC为清屏操作
     */
    public static void clearScreen(){
        try {//清屏操作
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_ESCAPE);
            r.keyRelease(KeyEvent.VK_ESCAPE);
            r.delay(100);
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     暂停操作，让程序暂停几秒
     */
    public static void sleepSystem(int stop) {
        try {
            Thread.currentThread().sleep(stop);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
    负责文件的读取
    */
    public static ArrayList<String[]> readfile(String filename) {
        ArrayList<String[]> memoryArray = new ArrayList<String[]>();
        File file = new File(filename);
        BufferedReader reader = null;

        try {
            //加入编码字符集
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String line = null;
            line = reader.readLine();//读取第一行标题
            while ((line = reader.readLine()) != null) {
                String [] line_split = line.split(" ");//将读取到的内容进行切割
                memoryArray.add(line_split);
                //for (int i=0;i<result.length;++i) {System.out.println(line_split[i]); }
                //System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return memoryArray;
    }
}
