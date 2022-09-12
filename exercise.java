package practice.src.ioexercise1;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Jin
 * @Date 2022年09月2022/9/9日17:47
 */
public class exercise {
    @Test
    public void test(){
        System.out.println("Hello github!!");
    }
    @Test
    public void test3(){
        FileReader tr= null;
        try {
            File file = new File("F:\\hello\\word.txt");
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
}
