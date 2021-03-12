package com.zjn.jvm;

/**
 * TimeStamp
 *
 * @author zjn
 * @date 2020/8/12
 **/
public class TimeStamp {

    public static void main(String[] args) {
        //0315549215542(1980年)
        //1593750914860
        //1593750932565
        //0031622400000(一年)
        //一年有365*24*60*1000毫秒(这个值超过了int最大值，所以用int算对)
        long t1 = System.currentTimeMillis();
        long oneYears = 366L*24L*60L*60L*1000L;
        int oneYears2 = 366*24*60*60*1000;
        System.out.println(oneYears);
        System.out.println(oneYears2);
        System.out.println((t1/oneYears));
        System.out.println((t1/oneYears2));
        System.out.println("0000004C".toUpperCase());
    }
}
