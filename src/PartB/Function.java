package PartB;

public abstract class Function {
    public abstract int valueAt(int x);
    @Override
    public abstract String toString();
    public abstract Function derivative();
    public abstract int bisectionMethod(int a, int b, int epsilon);
    public abstract int bisectionMethod(int a, int b);
    public abstract int newtonRaphsonMethod(int a, int epsilon);
    public abstract int newtonRaphsonMethod(int a);
    public abstract Function taylorPolynomial(int n);
}
