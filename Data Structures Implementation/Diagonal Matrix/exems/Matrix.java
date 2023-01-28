package exems;

interface Matrix {
    static final int MAX_SIZE = 100;

    public double get(int i , int j);

    public void set(int i , int j , double x);

    public void transpose();

    public Matrix getTranspose();


}
