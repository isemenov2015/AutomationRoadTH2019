package lesson11;

public class TwoVariablesClass {
    int var1;
    int var2;

    TwoVariablesClass() {
        this.var1 = 0;
        this.var2 = 0;
    }

    public void print() {
        System.out.println("Var1: " + this.var1);
        System.out.println("Var2: " + this.var2);
    }

    public void setVariables(int var1, int var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    public int getSum() {
        return this.var1 + this.var2;
    }

    public int getMax() {
        return var1 > var2 ? var1 : var2;
    }
}
