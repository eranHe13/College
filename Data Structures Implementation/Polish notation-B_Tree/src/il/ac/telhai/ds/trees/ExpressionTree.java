package il.ac.telhai.ds.trees;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
public class ExpressionTree extends FullBinaryTree<String>{

    ExpressionTree(String k) {
        super(k);
    }

    ExpressionTree(String k, BinaryTreeI<String> l, BinaryTreeI<String> r) {
        super(k, l, r);
    }

    /*
     * Read the stream tokenizer until EOF and construct
     *  the expression tree corresponding to it.
     * The input contains a prefix expression.
     */
    public static ExpressionTree createTree(StreamTokenizer tokenizer) throws IOException {
        if(tokenizer.ttype == StreamTokenizer.TT_EOF){
                return null;
        }
        tokenizer.nextToken();
        if(tokenizer.ttype == StreamTokenizer.TT_NUMBER){
            return new ExpressionTree(String.valueOf((int)tokenizer.nval));
        }
        else{
            char c = (char)tokenizer.ttype;
            return new ExpressionTree(String.valueOf(c),createTree(tokenizer) ,createTree(tokenizer) );
        }
    }

    /*
     * Returns the infix expression corresponding to the current tree (*)
     */
    public String infix() {

       return inOrder(" ", " ");
    }

    /*
     * Returns the prefix expression corresponding to the current tree (*)
     */
    public String prefix() {
        return preOrder();
    }

    /*
     * Evaluates the expression corresponding to the current tree
     * and returns its value
     */
    public double evaluate() {
        double res = 0;
        if(isLeaf()){
            return Double.parseDouble(getValue());
        }
        ExpressionTree left = (ExpressionTree) getLeft();
        ExpressionTree right = (ExpressionTree) getRight();
        double r_left = left.evaluate();
        double r_right = right.evaluate();
        switch (getValue()) {
            case "+":
                res = r_left + r_right;
                break;
            case "*":
                res = r_left * r_right;
                break;
            case "-":
                res = r_left - r_right;
                break;
            case "/":
                res = r_left / r_right;
                break;
        }
        return res;
    }

    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder sb = new StringBuilder();
        if (isLeaf()) return getValue();
        sb.append("(");
        sb.append(getLeft().inOrder(separationBeforeVal, separationAfterVal));
        sb.append(separationBeforeVal);
        sb.append(getValue());
        sb.append(separationAfterVal);
        sb.append(getRight().inOrder(separationBeforeVal, separationAfterVal));
        sb.append(")");

        return sb.toString();
    }

}
