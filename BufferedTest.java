package ioexercise1;

import org.testng.annotations.Test;

import java.io.*;
/**
 * @author Jin
 * @Date 2022年09月2022/9/8日13:20
 * 处理流之一：缓冲流的使用
 * （1）缓冲流
 *     BufferedInputStream
 *     BufferedOutputStream
 *     BufferedReader
 *     BufferedWWriter
 * （2）作用：提高流的读取和写入的速度
 *     原因：内部提供了一个缓冲区
 *     说明：①在进行数据读入的时候，会先将字节流中的读取的数据先存储到缓冲流，然后
 *           当数据到到一定数量时，全部输出，依次进行，直到文件内容全部读入。
 *          ②缓冲流在使用时只需要创建对象进行包装一下字节流对象就可以使用
 * （3）处理流：就是“套接”在已有的流的基础上
 */
public class BufferedTest {
    /**
     * 实现非文本文件的复制
     *步骤如下：
     *      （1）创建File类的对象来进行存储路径
     *      （2）创建两个流（输入流和输入流）
     *      （3）创建两个缓冲流（包裹输入流和输出流）
     *      （4）通过循环来执行读取和输入操作
     *      （5）关闭缓冲流（在关闭缓冲流的同时，内部流也会自动关闭）
     */
    @Test
    public void BufferedStreamTest(){
        BufferedInputStream bis= null;
        BufferedOutputStream bos= null;
        try {
            //（1）造文件
            File srcfile=new File("海豚.png");
            File desfile=new File("海豚8.png");
            //（2）造流
            // 2.1 造节点流
            FileInputStream fis=new FileInputStream(srcfile);
            FileOutputStream fos =new FileOutputStream(desfile);
            // 2.2 造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //（3）复制的细节：读取、写入
            byte[]buffer =new byte[10];
            int len;
            while((len=bis.read(buffer))!=-1){
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //（4）资源关闭（先关闭外层的流，再关闭内层的流）
                if(bis!=null) {
                    bis.close();
                }
                if(bos!=null) {
                    bos.close();
                }
                //说明：关闭外层流的同时，内层流也会自动关闭（无需再手动关闭内层流）
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /* 使用缓冲流来实现文件的复制*/
    public void copyFileWithBuffered(String srcPath,String desPath){
        BufferedInputStream bis= null;
        BufferedOutputStream bos= null;
        try {
            //（1）造文件
            File srcfile=new File(srcPath);
            File desfile=new File(desPath);
            //（2）造流
            // 2.1 造节点流
            FileInputStream fis=new FileInputStream(srcfile);
            FileOutputStream fos =new FileOutputStream(desfile);
            // 2.2 造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //（3）复制的细节：读取、写入
            byte[]buffer =new byte[10];
            int len;
            while((len=bis.read(buffer))!=-1){
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //（4）资源关闭（先关闭外层的流，再关闭内层的流）
                if(bis!=null) {
                    bis.close();
                }
                if(bos!=null) {
                    bos.close();
                }
                //说明：关闭外层流的同时，内层流也会自动关闭（无需再手动关闭内层流）
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testCopyBuffered(){
        long start =System.currentTimeMillis();
        String srcPath="海豚.png";
        String desPath="海豚3.png";
        copyFileWithBuffered(srcPath,desPath);
        long end = System.currentTimeMillis();
        System.out.println("执行复制所需要的时间是："+(end-start));
        //运行结果是：执行复制所需要的时间是：8
    }
    /**
     *使用BufferedReader和BufferedWriter来实现对文本文件的复制
     */
    @Test
    public void testBufferedWaR()  {
        BufferedReader rd= null;
        BufferedWriter wd= null;
        try {
            //创建文件和相应的流
            rd = new BufferedReader(new FileReader(new File("hello.txt")));
            wd = new BufferedWriter(new FileWriter(new File("hello2.txt")));
            //方式一：通过字符数组来进行读取和写入数据（其中包含换行符）
///            char[]cf=new char[10];
///            int len;
///            while((len=rd.read(cf))!=-1){
///                wd.write(cf,0,len);
///            }
            //方式二：通过Stirng来进行读取和写入数据
            String data;
            while((data=rd.readLine())!=null){
                //写法一：
                wd.write(data+"\n");
                //写法二：
///             wd.write(data);   //说明：不包含换行符
///             wd.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                //关闭缓冲流
                if(rd!=null){
                    try {
                        rd.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(wd!=null){
                    try {
                        wd.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

}
