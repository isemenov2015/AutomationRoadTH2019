package homework4;

import java.util.ArrayList;

public class CountAInString {
    private static ArrayList<Integer> aCounter(String str) {
        ArrayList<Integer> aList = new ArrayList<Integer>();

        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == 'a')
                aList.add(i);
        return aList;
    }

    private static void printOutput(String str) {
        ArrayList<Integer> aList = aCounter(str);

        System.out.println("String: " + str);
        System.out.println("Result. Number of 'a' symbols: " + aList.size() + ", 'a' indexes in a string: " + aList);
        System.out.println("-----------------------------------");
    }

    public static void main(String[] args) {
        printOutput("azzza");
        printOutput("aasss,assdfaasdqweaaa,asdaaaas,adsasdasd,aaa,asdghnzzzzzza,zzzzxxxaaaa,zxvvxvxcvxcv,xcvxv.fsdfsdfeweqwejhsdf sf sdfs a a sdfsdf sdf");
    }
}
