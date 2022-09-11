package practice.src.ioexercise1;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * @author Jin
 * @Date 2022年09月2022/9/11日9:12
 * RandomAccessFile的使用
 *  （1）RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 *  （2）RandomAccssFile既可以作为一个输入流，又可以作为一个输出流
 *  （3）如果RandomAccessFile作为输出流，写出到的文件如果不存在，则在执行过程中自动创建
 *       如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）
 *  （4）可以通过相关操作，实现RandomAccessFile"插入"数据的效果
 */
public class RandomAccessFileTest {
    @Test
    public void test()  {
        RandomAccessFile raf= null;
        RandomAccessFile raf1= null;
        try {
            raf = new RandomAccessFile(new File("hello1.txt"),"r");
            raf1 = new RandomAccessFile(new File("hello9.txt"),"rw");

            byte[]buf=new byte[1024];
            int len;
            while ((len=raf.read(buf))!=-1) {
                raf1.write(buf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf1 != null) {
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 使用RandomAccessFile在指定位置插入内容
     * 主要使用函数：
     *    seek()
     *    功能：可以将指针移动到待插入位置
     * */
    @Test
    public void testRandomAccessFile(){
        RandomAccessFile raf1= null;
        try {
            raf1 = new RandomAccessFile("hello.txt","rw");
            raf1.seek(3);
            //将指针移动到角标为3的位置
            raf1.write("xyz".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(raf1!=null) {
                    raf1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 使用RandomAccessFile实现数据的插入效果
     * */
    @Test
    public void test3()  {
        RandomAccessFile raf1= null;
        try {
            raf1 = new RandomAccessFile("hello5.txt","rw");
            raf1.seek(3);
            //将指针调到角标为3的位置

            //保存指针3后面的所有数据到builder中
            StringBuffer builder=new StringBuffer((int) new File("hello5.txt").length());
            byte[] buffer=new byte[20];
            int len;
            while((len=raf1.read(buffer))!=-1){
                builder.append(new String(buffer,0,len));
            }
            //调回指针，写入“xyz”
            raf1.seek(3);
            raf1.write("xyz".getBytes());
            //将builder中的内容写入
            raf1.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(raf1!=null) {
                    raf1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
