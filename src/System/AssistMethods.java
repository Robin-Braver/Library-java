package System;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

/**
 ����ϵͳʵ�ֵ�һЩ����
 */

public class AssistMethods<staric> {
    /**
     ����������������idea��ESCΪ��������
     */
    public static void clearScreen(){
        try {//��������
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
     ��ͣ�������ó�����ͣ����
     */
    public static void sleepSystem(int stop) {
        try {
            Thread.currentThread().sleep(stop);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
    �����ļ��Ķ�ȡ
    */
    public static ArrayList<String[]> readfile(String filename) {
        ArrayList<String[]> memoryArray = new ArrayList<String[]>();
        File file = new File(filename);
        BufferedReader reader = null;

        try {
            //��������ַ���
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String line = null;
            line = reader.readLine();//��ȡ��һ�б���
            while ((line = reader.readLine()) != null) {
                String [] line_split = line.split(" ");//����ȡ�������ݽ����и�
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
