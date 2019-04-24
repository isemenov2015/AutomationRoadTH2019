package lesson7;

public class StringParser {
    public static void main(String[] args) {
        int integerValue = 98;
        double doubleValue = 0.87635;
        long longValue = 3728378L;
        boolean boolValue = false;

        String strInt = String.valueOf(integerValue);
        String strDouble = String.valueOf(doubleValue);
        String strLong = String.valueOf(longValue);
        String strBool = String.valueOf(boolValue);

        System.out.println("String from int: " + strInt + ", int from String: " + Integer.parseInt(strInt));
        System.out.println("String from double: " + strDouble + ", double from String: " + Double.parseDouble(strDouble));
        System.out.println("String from long: " + strLong + ", long from String: " + Long.parseLong(strLong));
        System.out.println("String from boolean: " + strBool + ", boolean from String: " + Boolean.parseBoolean(strBool));
    }
}
