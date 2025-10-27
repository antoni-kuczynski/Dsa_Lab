package pl.edu.pw.ee.aisd2025zex2;

public class HashListChainingMultiplicative<T extends Comparable<T>> extends HashListChaining<T> {
    protected static double A1 = (Math.sqrt(5) - 1) / 2;  //0.61803
    protected static double A2 = (Math.sqrt(7) - 2) / 2;  //0.32287
    protected static double A3 = (Math.sqrt(3) + 1) / 3;  //0.91068


    public double getAConstant() {
        return A1;
    }

    public HashListChainingMultiplicative(int size) {
        super(size);
    }

    public HashListChainingMultiplicative() {
        super();
    }

    @Override
    int countHashId(T value) {
        int hashCode = value.hashCode() & Integer.MAX_VALUE;

        double val = hashCode * getAConstant();
        double decimal = val - Math.floor(val);

        return (int) (mSize * decimal);
    }

}
