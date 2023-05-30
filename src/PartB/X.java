package PartB;

public class X extends Function{
    private String x;
    private double num;
    public X(){
        this.x = "x";
        this.num = 1;
    }

    public X(double num){
        this.x = "x";
        this.num = num;
    }
    public String getX() {
        return this.x;
    }

    /**
     * @param x is a real number
     * @return
     */
    @Override
    public double valueAt(double x) {
        return x * this.num;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        if(this.num == 1)
            return "x";
        if((double)((int)this.num) == this.num)
            return (int)this.num+"x";
        return this.num+"x";
    }

    /**
     * @return
     */
    @Override
    public Function derivative() {
        return new Constant(this.num);
    }

    public double getNum() {
        return this.num;
    }
}
