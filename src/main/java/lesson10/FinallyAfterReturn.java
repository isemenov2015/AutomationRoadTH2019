package lesson10;

public class FinallyAfterReturn {
    public static String getSurname(String nameAndSurname){
        String surname = nameAndSurname.split(" ")[1];
        try {
            return surname;
        } finally {
            System.out.println(surname);
        }
    }

    public static void main(String[] args) {
        getSurname("John Bolt");
    }
}
