package ioexercise1;
import org.testng.annotations.Test;

import java.io.*;

/**
 *@author Jin
 *@Date 2022年09月2022/9/7日15:02
 * （一）流的分类
 *  （1）根据操作数据单位：字节流、字符流
 *  （2）根据数据的流向：输入流、输出流
 *  （3）根据流的对象：节点流、 处理流
 * （二）流的体系结构
 *    抽象基类                节点流（或文件流）                               缓冲流（处理流的一种）
 *    InputStream          FileInputStream (read(byte[]buffer))          BufferedInputStream (read(byte[]buffer))
 *    OutputStream         FileOutputStream (write(byte[]buffer,0,len))  BufferedOutputStream (write(byte[]buffer,0,len))
 *    Reader               FileReader (read(char[] cbuf))                BufferedReader  (read(char[] cbuf))/readline()
 *    Writer               FileWriter (write(char[]cbuf,0,len))          BufferedWriter  (write(char[]cbuf,0,len))
 *
 */
public class FileReaderWriterTest {
    public static void main(String[] args) {
        File file = new File("day01\\hello.txt");
        System.out.println(file.getAbsolutePath());
        //输出结果：F:\Java\IOStream\day\hello.txt
    }
    /**
     * FileReader测试：
     *
     * 步骤如下：
     *        （1）在当前工程下创建一个文件hello.txt()
     *        （2）实例化File类的对象，指明要读出的文件（路径是相对于当前module）
     *        （3）创建一个具体的流对象-->FileReader
     *        （4）通过循环进行数据读入--->需要通过强制类型转化（将整型数据转化为字符数据）
     *        （5）流的关闭（必须）-->数据库连接、输入输出流、Socket连接无法通过垃圾回收机机制进行回收
     *
     * 异常处理的选择：
     *       方式一：通过throws直接抛出异常，不会再执行 ” 流关闭 “操作，从而导致内存泄漏问题
     *       方式二：通过try-catch-finally来进行处理异常，“ 流处理 ”一定可以执行关闭操作
     * */
    @Test
    public void testFileReader() {
        FileReader tr= null;
        try {
            File file = new File("hello1.txt");
            tr = new FileReader(file);
            int data;
            while((data=tr.read())!=-1){
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭对应的流
            try {
                if(tr!=null) {
                    //条件判断是为了防止出现空指针异常
                    tr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testFileReader2(){
        FileReader tr= null;
        try {
            File file = new File("hello.txt");
            tr = new FileReader(file);
            char[]cbuf=new char[5];
            int len;
            while((len=tr.read(cbuf))!=-1){

                //错误写法（一）：
///                for (int i = 0; i < cbuf.length; i++) {
///                    System.out.print(cbuf[i]);
///               }
///             输出结果：helloworldturld
///错误原因解释：由于每次输出的均是cbuf中存储的所有元素，在读入数据时是通过覆盖的方式（重点）来进行的,如果读入文件字符个数不是5的整数倍，就会输出错误

                //订正：根据每次读入的字符个数进行输出
                for (int i = 0; i < len; i++) {
                    System.out.print(cbuf[i]);
                }

                //正确方式二：通过String对象来进行接收字符数组（根据读入的字符个数）
                String str=new String(cbuf,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭对应的流
            try {
                if(tr!=null) {
                    //条件判断是为了防止出现空指针异常
                    tr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * FileWriter的测试：
     * 步骤如下：
     *      （1）实例化File类的对象，指明要写入的文件（路径是相对于当前module）
     *      （2）创建一个具体的流对象-->FileWriter
     *      （3）数据写入
     *      （4）流的关闭（必须）-->数据库连接、输入输出流、Socket连接无法通过垃圾回收机机制进行回收
     * 说明：
     *      ① 输出操作中，对应的File如果不存在，在数据写入的过程中自动创建该文件
     *      ③ 在多次执行程序时：
     *          如果是构造器FileWriter(file,false)/FileWriter(file),会直接覆盖原文件数据
     *          如果是构造器FileWriter(file,true),不会覆盖原文件数据，会多次写入重复数据
     * */
    @Test
    public void testFileWriter() throws IOException {
            //创建文件类对象
            File file=new File("hello1.txt");
            //创建流对象
            FileWriter tr = new FileWriter(file,true);
            //写入数据
            tr.write("Welcome to China".toCharArray());
            tr.write(" Jerry",0,4);
            //关闭流
            tr.close();
    }
    /**
     * 文件数据的读出和写入
     * 步骤如下：
     *     （1）创建两个File类对象，分别指明要读出的文件和待读入的文件
     *     （2）创建输入输出流对象（注意输入流的构造器形式）
     *     （3）读出数据并写入数据
     *     （4）关闭输入输出流
     *
     * 注意：不能够使用字符流来进行处理图片、视频等字节数据
     * */
    @Test
    public void testFileRaW()  {
        FileReader du= null;
        FileWriter wr= null;
        try {
            //创建File类的对象，指明读入和读出的文件
            File file=new File("hello.txt");
            File file1=new File("hello1.txt");
            //创建输入流和输出流的对象
            du = new FileReader(file);
            wr = new FileWriter(file1,true);
            //读数据并且写入数据
            char [] tu=new char[5];
            int number;
            while((number=du.read(tu))!=-1) {
                String sr = new String(tu, 0, number);
                wr.write(sr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭输入输出流
                if(du!=null) {
                    du.close();
                }
                if(wr!=null){
                    wr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test(){
        System.out.println("Hello World" );
    }
}
