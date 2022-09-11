package practice.src.ioexercise1;

import org.testng.annotations.Test;

import java.io.*;

/**
 * @author Jin
 * @Date 2022年09月2022/9/10日19:52
 * 其他流的使用：
 *      （1）标准的输入输出流
 *      （2）打印流
 *      （3）数据流
 */
public class OtherStreamTest {
    /**
     * 1.标准的输入和输出流
     * 1.1
     * System.in:标准的输入流（默认的是从控制台输入）
     * System.out:标准的输出流（默认的是从控制台输出）
     * 1.2
     * System类的setIn(InputStream is)/setOut(PrintStream)方式指定输出和输出流
     * 练习：
     * 从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续
     * 进行输入操作，直至当输入“e”或者“exit”时，退出程序
     * 方法一：使用Scanner实现，调用next()方法返回一个字符串
     * 方法二：使用System.in实现。System.in---->转换流---->BufferedReader中的readline()
     */
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            while (true) {
                System.out.println("请输入字符串：");
                String data = br.readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序已经结束！！");
                    break;
                }
                String upperCase=data.toUpperCase();
                System.out.println(upperCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 2.打印流： PrintStream 和 PrintWriter
     *  2.1 提供了一系列重载的print()和println()
     * */
    @Test
    public void testPrintStream(){
        PrintStream ps = null;
        try {
            //创建一个输出流
            FileOutputStream fos = new FileOutputStream(new File("D:\\Hello\\hello.txt"));
           // 创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
            ps = new PrintStream(fos,true);
            if (ps != null) {
                // 把标准输出流(控制台输出)改成文件
                System.setOut(ps);
            }
            for (int i = 0; i <= 255; i++) {
                // 输出ASCII字符
                System.out.print((char) i);
                if (i % 50 == 0) {
                    System.out.println(); // 换行
                } }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            } }
    }
    /**
     *  3.数据流
     *  3.1 DataInputStream和DataOutputStream
     *  3.2 作用： 用于读取或写出基本数据类型的变量或字符串
     *  练习：写入并读取数据
     *
     * 说明：
     *  ①通过数据流写入数据后（通过DataOutputStream），查看（通过DataInputStream）来进行查看，并不是通过双击文件来进行查看
     *  ②写入和读出数据的顺序要是一致的，不然会报错EOFException
     * */
    @Test
    public void test() {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("hello3.txt"));
            dos.writeUTF("姚明");
            dos.writeInt(3);
            dos.flush();
            dos.writeBoolean(true);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test4(){
        DataInputStream str= null;
        try {
            str = new DataInputStream(new FileInputStream("hello3.txt"));
            String tr=str.readUTF();
            System.out.println(tr);
            int a=str.readInt();
            System.out.println(a);
            Boolean isFale = str.readBoolean();
            System.out.println(isFale);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(str!=null) {
                try {
                    str.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
