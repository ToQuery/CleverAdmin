package com.toquery.cleverweb.bug;

/**
 *
 * Java8运行显示
 * evaluated
 *
 * Java9。10 编译运行显示
 * evaluated
 * evaluated
 *
 *
 * @author toquery
 * @version 1
 */
public class Java92 {

    public static void main(String[] args) {
        String[] array = { "" };
        array[test()] += "a";
//        for (String item:array ) {
//            System.out.println(item);
//        }
    }
    static int test() {
        System.out.println("evaluated");
        return 0;
    }
}
