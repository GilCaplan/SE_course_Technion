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
     * @param x
     * @return
     */
    @Override
    public int valueAt(int x) {
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
