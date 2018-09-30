import java.lang.*;
import java.io.*;

public class SystemDemo {

   	public static void main(String[] args) throws Exception {


   		String[][] board = new String[3][3];


   		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = "*";
				System.out.print(board[i][j] + "\t");
			}
			System.out.print("\n");
		}


      	// create a file
      	FileOutputStream f = new FileOutputStream("temp.txt");
 
      	System.setErr(new PrintStream(f));
      
      	// redirect the output

      	for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = "*";
				System.err.print(board[i][j] + "\t");
			}
			System.err.print("\n");
		}

		System.err.print("the final mark");

   	}
} 