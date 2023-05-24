package PartB;

public class X extends Function{
    private String x;
    public X(){
        this.x = "x";
    }

    public String getX() {
        return x;
    }

    /**
     * @param x is a real number
     * @return
     */
    @Override
    public double valueAt(double x) {
        return x;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "x";
    }

    /**
     * @return
     */
    @Override
    public Function derivative() {
        return new Constant(1);
    }
}
