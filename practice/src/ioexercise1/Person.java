package practice.src.ioexercise1;

import java.io.Serializable;

/**
 * @author Jin
 * @Date 2022年09月2022/9/11日8:29
 * Person类 需要满足如下要求，方可序列化
 *   （1）需要实现接口：Serializable
 *   （2）当前类提供一个全局常量（为了用于标识类）
 *                public static final long serialVersionUID = 4244567L;
 *       说明：
 *        如果类没有显示定义这个静态常量，它的值是Java运行时环境根据类的内部细节自
 *      动生成的。若类的实例变量做了修改，serialVersionUID 可能发生变化。故建议，
 *      显式声明。
 *   （3）除了当前Person类需要实现Serializable接口之外，还必须保证其内部所有属性
 *       也必须是可序列化的（默认情况下，基本数据类型均是可序列化的）
 *
 * 补充说明：
 *     ObjectOutputStream和ObjectInputStream不能序列化static和
 *     transient修饰的成员变量
 *
 */
public class Person implements Serializable {
    public static final long serialVersionUID = 4244567L;
    /**
     * 成员变量
     *    name：用来记录人的名字
     *    age:用来记录人的年龄
     * */
    private String name;
    private int age;
    private Account  acct;
    //构造器
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, Account acct) {
        this.name = name;
        this.age = age;
        this.acct = acct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", acct=" + acct +
                '}';
    }
}
class Account implements Serializable{

    public static final long serialVersionUID = 424567L;
    private  double balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}