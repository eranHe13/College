package exems;
import java.util.Arrays;


public class DiagonalMatrix implements Matrix {
    private double[] matrix;
    private int middel ;
    private int Msize;
    private boolean isTranspose;

    public DiagonalMatrix(){
        this(MAX_SIZE);
    }

    public DiagonalMatrix(int size){
        if(size <= 0 ){
            System.out.println("size not leagle");
        }
        matrix = new double[(2*size)-1];
        middel = ((size*2)-2)/2;
        Msize = size;
        Arrays.fill(matrix,0);
        isTranspose = false;
    }

    @Override
    public double get(int i, int j) {

        if(i < 1 || i > Msize || j < 1 || j > Msize ){
            throw new IndexOutOfBoundsException();
        }
        if(isTranspose){
            int tmp = i;
            i = j;
            j = tmp;
        }
        double res =  matrix[(middel - ((i-1) - (j-1)))];
        return res;
    }

    @Override
    public void set(int i, int j, double x) {
        if(i < 1 || i > Msize || j < 1 || j > Msize ){
            throw new IndexOutOfBoundsException();
        }
        matrix[(middel - ((i-1) - (j-1)))] = x;
    }

    @Override
    public void transpose() {
        if(isTranspose){
            isTranspose = false;
            return;
        }
        isTranspose = true;
    }

    @Override
    public Matrix getTranspose() {
        Matrix matrixTranspose = new DiagonalMatrix(Msize);
        for (int i = 0; i<(2*Msize-1); i++){
            if( i <= middel) {
                matrixTranspose.set(Msize-i,1,matrix[i]);
            }
            else{
                matrixTranspose.set(1,(Msize-(i+2))*(-1),matrix[i]);
            }
        }
        if(this.isTranspose){
            return matrixTranspose;
        }
        matrixTranspose.transpose();
        return matrixTranspose;
    }

    public String toString(){
        String res = "";
        for (int i = 0; i < Msize; i++) {
            for (int j = middel-i ; j <matrix.length - i; j++) {
                res += matrix[j]+"\t";}
            res = res.substring(0 , res.length()-1);
            res+="\n";
        }

        return res;

    }
}
