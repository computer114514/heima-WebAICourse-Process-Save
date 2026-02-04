package test114514;

public class split {
    public static void main(String[] args) {
        String s="web-114514-1919.hwe:hhh.jj:810-ai";
        String[] split = s.split("\\.");
        for (String one:split){
            System.out.println(one);
        }
//        arrayToString({'a', 'b', 'v'});//错误必须new一个出来
//        arrayToString(['a', 'b', 'v']);//错误js
        arrayToString(new char[]{'a', 'b', 'v'});
    }
    public static void arrayToString(char[] array){
        String s=new String(array);
        System.out.println("字符数组转new String字符串结果是"+s);
    }
}
//split,就是字符串以一个符号分割，返回一个字符串数组。
