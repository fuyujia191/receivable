package cn.jwinni.springboot.common;

public class StringUtil {

    //string[]转int[]
    public static Integer[] StringToInt(String[] arrs) {
        Integer[] ints = new Integer[arrs.length];
        for (int i = 0; i < arrs.length; i++) {

            ints[i] = Integer.parseInt(arrs[i]);
        }
        return ints;
    }
}
