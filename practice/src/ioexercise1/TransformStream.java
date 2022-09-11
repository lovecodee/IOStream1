package practice.src.ioexercise1;

import org.testng.annotations.Test;

import java.io.*;

/**
 * @author Jin
 * @Date 2022年09月2022/9/8日14:54
 * 处理流之二：转换流的使用
 * （1）转换流:属于字符流
 *     InputStreamReader：将一个字节的输入流转化为字符的输入流
 *     OutputStreamWrite：将一个字符的输出流转化为字节的输出流
 * （2）提供字节流与字符流之间的转换
 *
 * （3）解码：字节、字节数组   ---->  字符数组、字符串
 *     编码：字符数组、字符串  ---->  字节、字节数组
 * （4）字符集
 *      ASCII：美国标准信息交换码。
 *         用一个字节的7位可以表示。
 *      ISO8859-1：拉丁码表。欧洲码表
 *         用一个字节的8位表示。
 *      GB2312：中国的中文编码表。最多两个字节编码所有字符
 *      GBK：中国的中文编码表升级，融合了更多的中文文字符号。最多两个字节编码
 *      Unicode：国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符码。所有的文字都用两个字节来表示。
 *      UTF-8：变长的编码方式，可用1-4个字节来表示一个字符。
 *
 */
public class TransformStream {
    @Test
    public void test1() throws IOException {

            FileInputStream fis =new FileInputStream("hello1.txt");
           InputStreamReader isr = new InputStreamReader(fis,"GBK");
            //说明：参数2指明了使用哪个字符集，取决于文件hello.txt保存时使用的字符集
           char[]tr=new char[1024];
           int len;
           while((len=isr.read(tr))!=-1){
               String str=new String(tr,0,len);
               System.out.print(str);
           }
            if(isr!=null){
                    isr.close();
            }
        }
       /**
        * 综合使用InputStreamReader 和 OutputStreamWriter
        * */
        @Test
        public void test(){
            InputStreamReader isr= null;
            OutputStreamWriter osw= null;
            try {
                //创建文件类
                File file1=new File("hello1.txt");
                File file2=new File("hello2.txt");
                //创建输入和输出流
                FileInputStream fis = new FileInputStream(file1);
                FileOutputStream fos= new FileOutputStream(file2);

                isr = new InputStreamReader(fis);
                osw = new OutputStreamWriter(fos,"GBK");

                //读写过程
                char[] tr=new char[20];
                int len=0;
                while((len=isr.read(tr))!=-1){
                    osw.write(tr,0,len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    isr.close();
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //运行结果：Welcome to China Jerry�й�(由于编码格式的不同所产生)
        }
}


