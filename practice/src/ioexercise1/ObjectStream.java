package practice.src.ioexercise1;

import org.testng.annotations.Test;

import java.io.*;

/**
 * @author Jin
 * @Date 2022年09月2022/9/11日8:00
 * （一）对象流的使用
 *   （1）定义（作用）：
 *             用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可
 *             以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
 *   （2）分类
 *           ObjectInputStream  和 ObjectOutputStream
 *   （3） 序列化与反序列化
 *         要求：对象所属的类是可以序列化的（满足序列化机制）
 *            序列化：用ObjectOutputStream类保存基本类型数据或对象的机制（数据从内存到文件）
 *            反序列化：用ObjectInputStream类读取基本类型数据或对象的机制（数据从文件到内存）
 *   （4）对象的序列化机制；
 *          允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种二进制流持久地
 *        保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。（当其他程序获得了这种二进制流，就可
 *        以恢复成原来的Java对象）
 *
 *
 *  ObjectOutputStream和ObjectInputStream不能序列化static和transient修
 * 饰的成员变量
 *
 *
 */
public class ObjectStream {
    public static void main(String[] args) {
        System.out.println("对象流的介绍与使用！！");
    }
    /**
     * 序列化过程：将内存中的Java对象保存到磁盘中或通过网络传输出去
     * 使用ObjectOutputStream实现
     * */
    @Test
    public void test1(){
        ObjectOutputStream oos= null;
        try {
            //创建对象流
            oos = new ObjectOutputStream(new FileOutputStream(new File("object.dat")));
            //将内容写入文件
            oos.writeObject(new String("欢迎来到北京！"));
            //刷新
            oos.flush();
            oos.writeObject(new Person("张学良",43,new Account(5600)));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭对象流
                if(oos!=null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 反序列化：将磁盘文件中的对象还原为内存中的一个Java对象（使用ObjectInputStream）
     * */
    @Test
    public void testObjectInputStream(){
        ObjectInputStream ois= null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));
            Object obj=ois.readObject();
            //强制转化
            String str=(String)obj;
            Person p2=(Person)ois.readObject();
            System.out.println(str);
            System.out.println(p2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭对象流
                if(ois!=null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
