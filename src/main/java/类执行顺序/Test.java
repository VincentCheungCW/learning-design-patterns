package 类执行顺序;

/**
 * Created by Jiang on 2019-10-31.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("new 一个子类时的执行顺序：");
        new HelloB();
    }
}
