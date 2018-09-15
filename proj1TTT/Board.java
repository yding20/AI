public class Board {
	private String[][] board;
	private int N;

	public Board(int N) {
		this.N = N;
		board = new String[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				board[i][j] = "*";
// Test Case
//		board[0][0] = "O";
//		board[0][1] = "*";
//		board[0][2] = "*";
//		board[1][0] = "*";
//		board[1][1] = "X";
//		board[1][2] = "*";
//		board[2][0] = "X";
//		board[2][1] = "*";
//		board[2][2] = "*";
	}//

	public Board(Board inputboard) {
		N = inputboard.getDimension();
		board = new String[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				board[i][j] = inputboard.getElement(i, j);
	}

	public String getElement(int i, int j) {
		return board[i][j];
	}

	public void setElement(int i, int j, String s) {
		board[i][j] = s;
	}

	public void PrintBoard() {
		//System.out.println("$$$$$$$$$$$$$$$$$$$$");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}

	public int getDimension() {
		return N;
	}
}