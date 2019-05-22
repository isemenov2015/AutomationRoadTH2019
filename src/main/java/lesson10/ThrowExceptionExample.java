package lesson10;

import lesson7.Box;

public class ThrowExceptionExample {
    static Box box;
    public static void makeSomeMagic() {
        if(box == null){
            throw new NullPointerException("BOX не инициализирован!");
        }
    }


}
