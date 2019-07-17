package lesson11;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SecondExamPart {

    int uniqueElements(int[] values) {
        Set<Integer> valuesSet = new HashSet<Integer>();

        for (int value : values)
            valuesSet.add(value);
        return valuesSet.size();
    }

    String getFilename(String fullName) {
        if (fullName.indexOf("\\") > -1) {
            String[] chunks = fullName.split("\\\\");
            fullName = chunks[chunks.length - 1];
        }
        if (fullName.indexOf("\\.") > -1) {
            fullName = fullName.split("\\.")[0];
        }
        return fullName;
    }

    String replaceWithBrackets(String text) {
        int counter = 0;
        while (text.indexOf("##") > -1) {
            String replacement = counter % 2 == 0 ? "<" : ">";
            text = text.replaceFirst("##", replacement);
            counter++;
        }
        return text;
    }

    private static String toSnakeCase(String input) {
        String result = "";
        System.out.println(input);
        // Start from second letter in order not to convert first letter of a word
        result += input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            char chr = input.charAt(i);
            if (Character.isUpperCase(chr))
                result += "_" + Character.toLowerCase(chr);
            else
                result += chr;
        }
        return result;
    }

    String[] convertToSnakeCase(String[] namesArray) {
        String[] result = namesArray.clone();
        for (int i = 0; i < namesArray.length; i++) {
            result[i] = toSnakeCase(namesArray[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        SecondExamPart instance = new SecondExamPart();
        String[] names = {"aName", "NAME", "Name", "namE"};
        System.out.println(Arrays.toString(instance.convertToSnakeCase(names)));
    }
}
