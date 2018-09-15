import java.lang.*;
import java.io.*;
import java.util.Scanner;


public class TTTGame {
	private Board board;
	private int step;
	private int count;
	private int sign;

	public TTTGame() {
		step = 0;
		Board board = new Board(3);

		Scanner scanner = new Scanner(System.in);
    	System.out.print("wanna to play X or O : ");
    	String choice = scanner.next();

    	if (choice.equals("X") || choice.equals("x")) {

    		sign = -1;
    		System.out.println("you(user) play first");
    		System.out.print("Please input your step : ");
    		String Sinitial = scanner.next();
    		int stepInit = Integer.parseInt(Sinitial);
    		if (stepInit < 1 || stepInit > 9)
    			throw new IllegalArgumentException("input must be 1-9");
    		int i = (stepInit - 1)/3;
    		int j = (stepInit - 1)%3;
    		board.setElement(i, j, "X");
    		board.PrintBoard();
    		step++;


    		for (int count = 0; count < 8; count++) {
     			int ACT = MinMax(board);
    			System.out.println("AI step : " + ACT);
    			i = (ACT - 1)/3;
    			j = (ACT - 1)%3;
    			board.setElement(i, j, "O");
    			step++;
    			board.PrintBoard();
    			if (TerminalTest(board) == 1) {
    				System.out.println("AI win");
    				break;
    			} else if (TerminalTest(board) == -1) {
    				System.out.println("you win");
    				break;
    			} else if (TerminalTest(board) == 0) {
    				System.out.println("TIE");
    				break;
    			}   	
    			
    			System.out.print("Please input your step : ");
    			String S = scanner.next();
    			int inputStep = Integer.parseInt(S);
    			i = (inputStep - 1)/3;
    			j = (inputStep - 1)%3;
    			board.setElement(i, j, "X");
    			step++;
    			board.PrintBoard();
    			if (TerminalTest(board) == 1) {
    				System.out.println("AI win");
    				break;
    			} else if (TerminalTest(board) == -1) {
    				System.out.println("you win");
    				break;
    			} else if (TerminalTest(board) == 0) {
    				System.out.println("TIE");
    				break;
    			}

    		}
    		System.out.println("Terminated at step  " + step);



    	} else if (choice.equals("O") || choice.equals("o")) {
    		sign = 1;
    		System.out.println("I (AI) play first");
    		int stepInit = 1;
    		step++;
    		int i = (stepInit - 1)/3;
    		int j = (stepInit - 1)%3;
    		board.setElement(i, j, "X");
    		board.PrintBoard();

    		for (int count = 0; count < 8; count++) {
    			System.out.print("Please input your step : ");
    			String S = scanner.next();
    			int inputStep = Integer.parseInt(S);
    			i = (inputStep - 1)/3;
    			j = (inputStep - 1)%3;
    			board.setElement(i, j, "O");
    			step++;
    			board.PrintBoard();
    			if (TerminalTest(board) == 1) {
    				System.out.println("AI win");
    				break;
    			} else if (TerminalTest(board) == -1) {
    				System.out.println("You win");
    				break;
    			} else if (TerminalTest(board) == 0) {
    				System.out.println("TIE");
    				break;
    			}

    			int ACT = MinMax(board);
    			System.out.println("AI step : " + ACT);
    			i = (ACT - 1)/3;
    			j = (ACT - 1)%3;
    			board.setElement(i, j, "X");
    			step++;
    			board.PrintBoard();
    			if (TerminalTest(board) == 1) {
    				System.out.println("AI win");
    				break;
    			} else if (TerminalTest(board) == -1) {
    				System.out.println("You win");
    				break;
    			} else if (TerminalTest(board) == 0) {
    				System.out.println("TIE");
    				break;
    			}
    		}

    		System.out.println("Terminated at step  " + step);

    	} else if (choice.equals("T"))  {
    		int ACT = MinMax(board);
    		System.out.println("AI step : " + ACT);
    		int i = (ACT - 1)/3;
    		int j = (ACT - 1)%3;
    		board.setElement(i, j, "O");
    		board.PrintBoard();
    	} else {
    		throw new IllegalArgumentException("input must be X or O");
    	}
	}


	public int MinMax(Board board) {
		int branch = openSpace(board);
		int bran = 0;
		int Res = 0;
		Double utility_pre = Double.NEGATIVE_INFINITY;
		Double utility;
		Board[] actions = new Board[branch];

		for (int n = 0; n < 9; n++) {
			int i = (n)/3;
    		int j = (n)%3;
			if (board.getElement(i, j).equals("*")) {
				actions[bran] = new Board(board);
				if (sign == 1)
    				actions[bran].setElement(i, j, "X");
    			else
    				actions[bran].setElement(i, j, "O");
//    			System.out.println("$$$$$$$  LEAD  $$$$$$$");
//				actions[bran].PrintBoard();
				// above show all the branches
				utility = MinValue(actions[bran]);
//				System.out.println("utility : " + utility);
				bran++;
				if (utility > utility_pre) {
				utility_pre = utility;
				Res = n + 1;
//				System.out.println("Res : " + Res);
				}
			}	
		}
		return Res;
	}


	public Double MaxValue(Board board) {
		if (TerminalTest(board) == 1) {return 1.0;}
		else if (TerminalTest(board) == -1) {return -1.0;}
		else if (TerminalTest(board) == 0)	{return 0.0;}

		Double V = Double.NEGATIVE_INFINITY;
		Double utility;
		int branch = openSpace(board);
		int bran = 0;
		Board[] actions = new Board[branch];
		for (int n = 0; n < 9; n++) {
			int i = (n)/3;
    		int j = (n)%3;
			if (board.getElement(i, j).equals("*")) {
				actions[bran] = new Board(board);
				if (sign == 1)
    				actions[bran].setElement(i, j, "X");
    			else
    				actions[bran].setElement(i, j, "O");
    			utility = MinValue(actions[bran]);
    			bran++;
				if (utility > V) {
					V = utility;
				}
				if (bran == branch)  break;
			}
		}
		return V;
	}

	public Double MinValue(Board board) {
		if (TerminalTest(board) == 1) {return 1.0;}
		else if (TerminalTest(board) == -1) {return -1.0;}
		else if (TerminalTest(board) == 0)	{return 0.0;}

		Double V = Double.POSITIVE_INFINITY;
		Double utility;
		int branch = openSpace(board);
		int bran = 0;
		Board[] actions = new Board[branch];

		for (int n = 0; n < 9; n++) {
			int i = (n)/3;
    		int j = (n)%3;
			if (board.getElement(i, j).equals("*")) {
				actions[bran] = new Board(board);
				if (sign == 1)
    				actions[bran].setElement(i, j, "O");
    			else
    				actions[bran].setElement(i, j, "X");
//    			System.out.println("$$$$$$$  MIN  $$$$$$$" + "branch = " + branch + "bran : " + bran);
//    			actions[bran].PrintBoard();
    			utility = MaxValue(actions[bran]);
//    			System.out.println("Min unitility : " + utility);
    			bran++;
				//utility = 1.0;
				if (utility < V) {
					V = utility;
				}
				if (bran == branch) break;
			}
		}
		return V;
	}

	public int openSpace(Board board) {
		int openspace = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board.getElement(i, j).equals("*")) {
					openspace++;

				}
			}
		}
		return openspace;
	}

	public int TerminalTest(Board board) {
		int openspace = 0;
		String[][] Elements = new String[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Elements[i][j] = (board.getElement(i, j));
				if (Elements[i][j].equals("*"))
					openspace++;
			}
		}

		if ((Elements[0][0].equals("X") && Elements[0][1].equals("X") &&  Elements[0][2].equals("X")) || 
			(Elements[1][0].equals("X") && Elements[1][1].equals("X") &&  Elements[1][2].equals("X")) ||
			(Elements[2][0].equals("X") && Elements[2][1].equals("X") &&  Elements[2][2].equals("X")) ||
			(Elements[0][0].equals("X") && Elements[1][0].equals("X") &&  Elements[2][0].equals("X")) ||
			(Elements[0][1].equals("X") && Elements[1][1].equals("X") &&  Elements[2][1].equals("X")) ||
			(Elements[0][2].equals("X") && Elements[1][2].equals("X") &&  Elements[2][2].equals("X")) ||
			(Elements[0][0].equals("X") && Elements[1][1].equals("X") &&  Elements[2][2].equals("X")) ||
			(Elements[0][2].equals("X") && Elements[1][1].equals("X") &&  Elements[2][0].equals("X"))
			) {
			return (1*sign);
		} else if ( 
			(Elements[0][0].equals("O") && Elements[0][1].equals("O") &&  Elements[0][2].equals("O")) || 
			(Elements[1][0].equals("O") && Elements[1][1].equals("O") &&  Elements[1][2].equals("O")) ||
			(Elements[2][0].equals("O") && Elements[2][1].equals("O") &&  Elements[2][2].equals("O")) ||
			(Elements[0][0].equals("O") && Elements[1][0].equals("O") &&  Elements[2][0].equals("O")) ||
			(Elements[0][1].equals("O") && Elements[1][1].equals("O") &&  Elements[2][1].equals("O")) ||
			(Elements[0][2].equals("O") && Elements[1][2].equals("O") &&  Elements[2][2].equals("O")) ||
			(Elements[0][0].equals("O") && Elements[1][1].equals("O") &&  Elements[2][2].equals("O")) ||
			(Elements[0][2].equals("O") && Elements[1][1].equals("O") &&  Elements[2][0].equals("O"))
			) {
			return (-1*sign);
		} else if (openspace == 0) {
			return 0;
		} else {
			return 666; // go on playing
		}
	}







	public static void main (String args[]) {
		TTTGame newGame = new TTTGame();
	}
}