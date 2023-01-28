package il.ac.telhai.ds.stack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class EvaluatePostfix {

	private static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
	private static Stack<Double> myStack = new DLinkedListStack<Double>();
	
	public static void main(String[] args) throws IOException {
		tokenizer.slashSlashComments(false);
		tokenizer.ordinaryChar('/');
		int count = 0;
		while(true){
			tokenizer.nextToken();
			if(tokenizer.ttype == StreamTokenizer.TT_EOF){
				break;
			}
			if(tokenizer.ttype == StreamTokenizer.TT_WORD){
				if(tokenizer.sval.equals("quit")){
					break;
				} else{
					System.err.println(tokenizer);
					System.err.println(myStack);
					System.exit(1);
				}
			}
			if(tokenizer.ttype == StreamTokenizer.TT_NUMBER){
				myStack.push(tokenizer.nval);
				count++;
				continue;
			}
			char c = (char) tokenizer.ttype;
			Double y = myStack.pop();
			Double x = myStack.pop();
			if(x == null || y == null){
				System.err.println(tokenizer);
				System.err.println(myStack);
				System.exit(1);
			}
			count--;
			switch (c){
				case '+' :
					myStack.push(x+y);
					break;
				case '-':
					myStack.push(x-y);
					break;
				case '*' :
					myStack.push(x*y);
					break;
				case '/':
					myStack.push(x/y);
					break;
			}
		}
			if(count == 0){
				System.err.println(tokenizer);
				System.err.println(myStack);
				System.exit(1);
			}
			double res = myStack.pop();
			count--;
			if(count != 0 ){
				System.err.println(tokenizer);
				System.err.println(myStack);
				System.exit(1);
			}
			System.out.println(res);


		
		//TODO add your code here.

	}

}
