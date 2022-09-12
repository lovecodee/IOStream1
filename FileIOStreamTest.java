package ioexercise1;

import org.testng.annotations.Test;

import java.io.*;

/**
 * @author Jin
 * @Date 2022年09月2022/9/8日9:27
 *
 * 概述:  FileInputStream和FileInputStream的使用练习
 *
 */
public class FileIOStreamTest {
    /**
     * FileInputStream读取文本文件
     * 步骤如下：
     *       （1）创建File类的对象，指明读取文件位置
     *       （2）创建输入流
     *       （3）创建字节数组来进行读取数据
     *       （4）关闭输入和输出流
     * 说明：
     *    如果是使用字节流（I/OStream）来进行处理文本文件，会出现乱码问题
     *    字节流和字符流的具体场景如下：
     *       （1）对于文本文件<.txt .java .c .cpp等>，使用字符流处理
     *       （2）对于非文本文件<.jpg .mp3 .mp4 .avi .doc .ppt等>，使用字节流处理
     * */
    @Test
    public void testInputStream(){
        FileInputStream in= null;
        try {
            File file=new File("hello1.txt");
            in = new FileInputStream(file);
            byte[]bu=new byte[5];
            int len;
            while ((len=in.read(bu))!=-1) {
                String tr=new String(bu);
                System.out.print(tr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //输出结果：hello world tu?腑鍥?
    }
    /**
     * 图片的读出和写入：
     * 步骤如下：
     *       （1）创建两个File类来进行指明输入和输出的文件对象
     *       （2）创建输入流和输出流（字节流）对象
     *       （3）创建一个字节数组来存储每次读取的数据
     *       （4）数据的读出和写入
     *       （5）关闭输入流和输出流
     * */
    @Test
    public void copyFileIaO()  {
        FileInputStream input= null;
        FileOutputStream out = null;
        try {
            //创建File类对象
            File file1=new File("海豚.png");
            File file2=new File("海豚1.png");
            //创建输入流和输出流
            input = new FileInputStream(file1);
            out = new FileOutputStream(file2);
            //数据的读入和读出
            byte []buf=new byte[5];
            int len;
            while((len=input.read(buf))!=-1){
                out.write(buf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                //输入输出流的关闭
                if(input!=null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(out!=null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
    }
}
public void copyFile(String initPath,String desPath){
    FileInputStream input= null;
    FileOutputStream out = null;
    try {
        //创建File类对象
        File file1=new File(initPath);
        File file2=new File(desPath);
        //创建输入流和输出流
        input = new FileInputStream(file1);
        out = new FileOutputStream(file2);
        //数据的读入和读出
        byte []buf=new byte[10];
        int len;
        while((len=input.read(buf))!=-1){
            out.write(buf,0,len);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        //输入输出流的关闭
        if(input!=null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(out!=null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 }
 @Test
    public void testCopyFile(){
        long start=System.currentTimeMillis();
        String srcPath="海豚.png";
        String desPath="海豚1.png";
        copyFile(srcPath,desPath);
        long end = System.currentTimeMillis();
        System.out.println("复制所花费的时间是："+(end-start));
        //运行结果为：复制所花费的时间是：265

    }
    @Test
    public void testFileOutputStream(){
        FileOutputStream fos= null;
        try {
            File fs=new File("hell5.txt");
            fos = new FileOutputStream(fs);
            fos.write(97);
            //说明：97对应的字符是 a
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos!=null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

