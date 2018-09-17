import java.lang.*;
import java.io.*;
import java.util.Scanner;


public class AdvancedTTT {
	private NBoard nboard;
	private int step;
	private int sign;
	private int cutoff = 8;

	public AdvancedTTT() {
		step = 0; 
		nboard = new NBoard(3);
		Scanner scanner = new Scanner(System.in);
    	System.out.print("wanna to play X or O : ");
    	String choice = scanner.next();

    	if (choice.equals("X") || choice.equals("x")) {

    		sign = -1;
    		System.out.println("First state : ");
    		nboard.PrintNBoard();
    		System.out.println("you(user) play first");
    		System.out.print("Please input the grid(1-9) you want to play : ");
    		String gridinitial = scanner.next();
    		int ACT = Integer.parseInt(gridinitial);
    		if (ACT < 1 || ACT > 9)
    			throw new IllegalArgumentException("input must be 1-9");
    		int i = (ACT - 1)/3;
    		int j = (ACT - 1)%3;

    		for (int count = 0; count < 9; count++) {
    			System.out.print("Please input your step(1-9) in grid " + ACT + ":  ");
    			Board board = nboard.getBoard(i, j);
	    		String Sinitial = scanner.next();
	    		int inputStep = Integer.parseInt(Sinitial);
	    		if (inputStep < 1 || inputStep > 9)
	    			throw new IllegalArgumentException("input must be 1-9");
	    		i = (inputStep - 1)/3;
	    		j = (inputStep - 1)%3;
	
	    		board.setElement(i, j, "X");
	    		nboard.PrintNBoard();
	    		step++;

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

    			System.out.println("AI play in grid " + inputStep);
    			board = nboard.getBoard(i, j);
    			ACT = MinMax(nboard, i, j);
    			System.out.println("AI step : " + ACT);
    			i = (ACT - 1)/3;
    			j = (ACT - 1)%3;
    			board.setElement(i, j, "O");
    			nboard.PrintNBoard();
    			step++;
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
    		int inputStep = 5;
    		int i = (inputStep - 1)/3;
    		int j = (inputStep - 1)%3;

    		System.out.println("First state : ");
    		nboard.PrintNBoard();
    		System.out.println("I (AI) play first");

    		for (int count = 0; count < 30; count++) {
     			System.out.println("AI play in grid " + inputStep);

     			Board board = nboard.getBoard(i, j);
    			int ACT = MinMax(nboard, i, j);
    			System.out.println("AI step : " + ACT);
    			i = (ACT - 1)/3;
    			j = (ACT - 1)%3;
    			board.setElement(i, j, "X");
    			nboard.PrintNBoard();
    			step++;
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
  			
    			board = nboard.getBoard(i, j);
    			System.out.print("Your play in grid  " + ACT +  ", Please input your step : ");
    			String S = scanner.next();
    			inputStep = Integer.parseInt(S);
    			i = (inputStep - 1)/3;
    			j = (inputStep - 1)%3;
    			board.setElement(i, j, "O");
    			nboard.PrintNBoard();
    			step++;
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
    	} else {
    		throw new IllegalArgumentException("input must be X or O");
    	}
	}

	public int MinMax(NBoard nboard, int gridI, int gridJ) {
		Board board = nboard.getBoard(gridI, gridJ);
		Board thisstep;
		int branch = openSpace(board);
		int bran = 0;
		int Res = 0;
		int depth = 0;
		Double utility_pre = Double.NEGATIVE_INFINITY;
		Double utility;
		NBoard[] Nactions = new NBoard[branch];

		for (int n = 0; n < 9; n++) {
			int i = (n)/3;
    		int j = (n)%3;
			if (board.getElement(i, j).equals("*")) {
				Nactions[bran] = new NBoard(nboard);
				if (sign == 1) {
					thisstep = Nactions[bran].getBoard(gridI, gridJ);
    				thisstep.setElement(i, j, "X");
					if (TerminalTest(thisstep) == 1) {utility = 200.0; return n+1;}
					else if (TerminalTest(thisstep) == -1) {utility = -200.0; return n+1;}
					else if (TerminalTest(thisstep) == 0)	{utility = 0.0; return n+1;}
				}
    			else {
					thisstep = Nactions[bran].getBoard(gridI, gridJ);
    				thisstep.setElement(i, j, "O");
    				if (TerminalTest(thisstep) == 1) {utility = 200.0; return n+1;}
					else if (TerminalTest(thisstep) == -1) {utility = -200.0; return n+1;}
					else if (TerminalTest(thisstep) == 0)	{utility = 0.0; return n+1;}
    			}

//    			System.out.println("&&&&&&&&&&&&& MinMaxValue &&&&&&&&&&&&&");
//    			Nactions[bran].PrintNBoard();

				utility = MinValue(Nactions[bran], i, j,
					Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, depth);

//				if (sign == 1) {
//					if (TerminalTest(thisstep) == 1) {utility = 200.0;}
//					else if (TerminalTest(thisstep) == -1) {utility = -200.0;}
//					else if (TerminalTest(thisstep) == 0)	{utility = 0.0;}
//				} else {
//					if (TerminalTest(thisstep) == 1) {utility = 200.0;}
//					else if (TerminalTest(thisstep) == -1) {utility = -200.0;}
//					else if (TerminalTest(thisstep) == 0)	{utility = 0.0;}
//				}

//				System.out.println("utility = " + utility + "bran = " + bran);
				bran++;
				if (utility > utility_pre) {
				utility_pre = utility;
				Res = n + 1;
				}
			}	
		}
		return Res;
	}



	public Double MaxValue(NBoard nboard, int gridI, int gridJ, Double alpha, Double beta, int inputM) {
		int depth = inputM + 1;
		Board board = nboard.getBoard(gridI, gridJ);
		Board thisstep;
//		System.out.println("Board at MaxValue");
//		board.PrintBoard();

		if (depth < cutoff) {
			if (TerminalTest(board) == 1) {return 200.0;}
			else if (TerminalTest(board) == -1) {return -200.0;}
			else if (TerminalTest(board) == 0)	{return 0.0;}
		} else {
			return Heuristic(board);
		}

		Double V = Double.NEGATIVE_INFINITY;
		Double utility;
		int branch = openSpace(board);
		int bran = 0;
		NBoard[] Nactions = new NBoard[branch];

		for (int n = 0; n < 9; n++) {
			int i = (n)/3;
    		int j = (n)%3;
			if (board.getElement(i, j).equals("*")) {
				Nactions[bran] = new NBoard(nboard);
				if (sign == 1) {
					thisstep = Nactions[bran].getBoard(gridI, gridJ);
    				thisstep.setElement(i, j, "X");
    				if (TerminalTest(thisstep) == 1) {return 200.0;}
					else if (TerminalTest(thisstep) == -1) {return -200.0;}
					else if (TerminalTest(thisstep) == 0)	{return 0.0;}
				}
    			else {
    				thisstep = Nactions[bran].getBoard(gridI, gridJ);
    				thisstep.setElement(i, j, "O");
    				if (TerminalTest(thisstep) == 1) {return 200.0;}
					else if (TerminalTest(thisstep) == -1) {return -200.0;}
					else if (TerminalTest(thisstep) == 0)	{return 0.0;}
				}

    			utility = MinValue(Nactions[bran], i, j, alpha, beta, depth);
//    			System.out.println("&&&&&&&&&&&&& MaxValue &&&&&&&&&&&&&");
//    			Nactions[bran].PrintNBoard();
//    			System.out.println("Maxutility = " + utility + "depth = " + depth);
    			bran++;

				if (utility > V) {
					V = utility;
				}

            	if (V >= beta)
                	return V;
            	alpha = Math.max(alpha, V);

				if (bran == branch)  break;


			}
		}
		return V;
	}

	public Double MinValue(NBoard nboard, int gridI, int gridJ, Double alpha, Double beta, int inputM) {
		int depth = inputM + 1;
		Board board = nboard.getBoard(gridI, gridJ);
		Board thisstep;
//		System.out.println("Board at MinValue");
//		board.PrintBoard();

		if (depth < cutoff) {
			if (TerminalTest(board) == 1) {return 200.0;}
			else if (TerminalTest(board) == -1) {return -200.0;}
			else if (TerminalTest(board) == 0)	{return 0.0;}
		} else {
			return Heuristic(board);
		}

		Double V = Double.POSITIVE_INFINITY;
		Double utility;
		int branch = openSpace(board);
		int bran = 0;
		NBoard[] Nactions = new NBoard[branch];

		for (int n = 0; n < 9; n++) {
			int i = (n)/3;
    		int j = (n)%3;
			if (board.getElement(i, j).equals("*")) {
				Nactions[bran] = new NBoard(nboard);
				if (sign == 1) {
					thisstep = Nactions[bran].getBoard(gridI, gridJ);
    				thisstep.setElement(i, j, "O");
    				if (TerminalTest(thisstep) == 1) {return 200.0;}
					else if (TerminalTest(thisstep) == -1) {return -200.0;}
					else if (TerminalTest(thisstep) == 0)	{return 0.0;}
				}
    			else {
    				thisstep = Nactions[bran].getBoard(gridI, gridJ);
    				thisstep.setElement(i, j, "X");
    				if (TerminalTest(thisstep) == 1) {return 200.0;}
					else if (TerminalTest(thisstep) == -1) {return -200.0;}
					else if (TerminalTest(thisstep) == 0)	{return 0.0;}
    			}

    			utility = MaxValue(Nactions[bran], i, j, alpha, beta, depth);
//    			System.out.println("&&&&&&&&&&&&& MinValue &&&&&&&&&&&&&");
//    			Nactions[bran].PrintNBoard();
//    			System.out.println("Minutility = " + utility + "depth = " + depth);
    			bran++;
				if (utility < V) {
					V = utility;
				}

				if (V <= alpha)
                	return V;
            	beta = Math.min(beta, V);

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
		} else if (openspace == 1) {
			return 0;
		} else {
			return 666; // go on playing
		}
	}

	public Double Heuristic(Board board) {
		Double Res = 0.0;
		String[][] Elements = new String[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Elements[i][j] = (board.getElement(i, j));
			}
		}

		Res = Res + getScore(Elements[0][0], Elements[0][1], Elements[0][2]);
		Res = Res + getScore(Elements[1][0], Elements[1][1], Elements[1][2]);
		Res = Res + getScore(Elements[2][0], Elements[2][1], Elements[2][2]);
		Res = Res + getScore(Elements[0][0], Elements[1][0], Elements[2][0]);
		Res = Res + getScore(Elements[0][1], Elements[1][1], Elements[2][1]);
		Res = Res + getScore(Elements[0][2], Elements[1][2], Elements[2][2]);
		Res = Res + getScore(Elements[0][0], Elements[1][1], Elements[2][2]);
		Res = Res + getScore(Elements[0][2], Elements[1][1], Elements[2][0]);

//		System.out.println("Board at Heuristic");
//		board.PrintBoard();
		return Res;
	}

	private Double getScore(String s1, String s2, String s3) {
		Double XScore = 0.;
		Double OScore = 0.;

		if 		(s1.equals("X"))	{XScore++;}
		else if (s1.equals("O"))	{OScore++;}
		if 		(s2.equals("X"))	{XScore++;}
		else if (s2.equals("O"))	{OScore++;}
		if 		(s3.equals("X"))	{XScore++;}
		else if (s3.equals("O"))	{OScore++;}

		if 			(OScore == 0 && XScore == 3)		{return  200.0*sign;}
		else if 	(OScore == 0 && XScore == 2)		{return  2.0*sign;}
		else if 	(OScore == 0 && XScore == 1)		{return  1.0*sign;}
		else if 	(XScore == 0 && OScore == 3)		{return -200.0*sign;}
		else if 	(XScore == 0 && OScore == 2)		{return -2.0*sign;}
		else if 	(XScore == 0 && OScore == 1)		{return -1.0*sign;}
		else 	return 0.0;
	}



	public static void main(String args[]) {
		AdvancedTTT newGame = new AdvancedTTT();
	}
}