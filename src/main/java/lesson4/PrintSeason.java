package lesson4;

public class PrintSeason {
    private static String getSeason(int month) {
        switch (month) {
            case 12:
            case 1:
            case 2:
                return "Winter";
            case 3:
            case 4:
            case 5:
                return "Spring";
            case 6:
            case 7:
            case 8:
                return "Summer";
            case 9:
            case 10:
            case 11:
                return "Autumn";
            default:
                return "Apocalypse. Wrong month number";

        }
    }

    public static void main(String[] args) {
        int[] months = {1, 5, 9, 11, 12, 8, 13};

        for (int month : months) {
            System.out.println("Season for month number " + month + " is " + getSeason(month));
        }
    }
}