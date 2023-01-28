public class SparseMatrix <T> implements Matrix<T>{
    private int size;
    private DLinkedList<SparseMatrixEntry> matrix;
    private T defualtValue;
    boolean isTranspose ;

    public SparseMatrix(T defaultValue){
        this(MAX_SIZE , defaultValue );
    }

    public SparseMatrix(int size , T defaultValue){
        this.size = size;
        this.defualtValue = defaultValue;
        matrix = new DLinkedList<SparseMatrixEntry>();
        isTranspose = false;
    }


    @Override
    public T get(int row, int col) {

        if(row < 1 || row > size || col < 1 || col > size  ) {
            throw new IllegalArgumentException();
        }
        if(matrix.isEmpty()){
            return defualtValue;
        }
        if(isTranspose){
            int swap = row;
            row = col;
            col = swap;
        }
        matrix.goToBeginning();
        SparseMatrixEntry tmp = matrix.getCursor();

        if(!matrix.hasNext()){
            if(tmp.getCol() == col && tmp.getRow() == row){
                return tmp.getValue();
            }
        }
        while (matrix.hasNext()){
            if(tmp.getCol() == col && tmp.getRow() == row){
                return tmp.getValue();
            }
            tmp = matrix.getNext();
        }
        if(tmp.getCol() == col && tmp.getRow() == row){
            return tmp.getValue();
        }
        return defualtValue;

    }

    @Override
    public void set(int row, int col, T element) {
        if(row < 1 || row > size || col < 1 || col > size){
            throw new IndexOutOfBoundsException();
        }
        if(matrix.isEmpty()){
            matrix.insert(new SparseMatrixEntry(row,col,element));
            return;
        }
        SparseMatrixEntry tmp;
        matrix.goToBeginning();
        tmp = matrix.getCursor();
        if(!matrix.hasNext()){
            if (tmp.getRow() == row && tmp.getCol() == col) {
                tmp.setValue(element);
                return;
            }
        }
        while(matrix.hasNext()) {
            if (tmp.getRow() == row && tmp.getCol() == col) {
                tmp.setValue(element);
                return;
            }
            tmp = matrix.getNext();
        }
        if (tmp.getRow() == row && tmp.getCol() == col) {
            tmp.setValue(element);
            return;
        }
        matrix.insert(new SparseMatrixEntry(row,col,element));
    }





    @Override
    public void transpose() {
        if(isTranspose){
            isTranspose = false;
        }
        else {
            isTranspose = true;
        }
    }

    private class SparseMatrixEntry {
        private T value;
        private int row;
        private int col;

        public SparseMatrixEntry(int row, int col, T val) {
            this.value = val;
            this.col = col;
            this.row = row;
        }

        public int getRow() {
            return row;
        }
        public void setRow(int row) {
            this.row = row;
        }
        public int getCol() {
            return col;
        }
        public void setCol(int col) {
            this.col = col;
        }
        public T getValue() {
            return value;
        }
        public void setValue(T newVal) {
            this.value = newVal;
        }
    }

}
