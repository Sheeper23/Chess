

public class ChessBoard {
	private Piece[][] board;
	
	public ChessBoard() {
		board = new Piece[8][8];
		
		Fen.load(board, "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
		
	}
	
	public Piece[][] getBoard() {
		return board;
	}
	
	public void move(int rowFrom, int colFrom, int rowTo, int colTo) {
		board[rowFrom][colFrom].calculateSight(board, false);
		if (board[rowFrom][colFrom].getSight()[rowTo][colTo] == 1 || board[rowFrom][colFrom].getSight()[rowTo][colTo] == 2) {
			if (board[rowFrom][colFrom] instanceof King && board[rowFrom][colFrom].getSight()[rowTo][colTo] == 2) {
				if (board[rowFrom][colFrom].getSight()[rowTo][colTo-1] == 1) {
					board[rowFrom][colFrom+3].move(rowTo, colTo-1);
					board[rowTo][colTo-1] = board[rowFrom][colFrom+3];
					board[rowFrom][colFrom+3] = null;
				}
				else {
					board[rowFrom][colFrom-4].move(rowTo, colTo+1);
					board[rowTo][colTo+1] = board[rowFrom][colFrom-4];
					board[rowFrom][colFrom-4] = null;
				}
			}
			board[rowFrom][colFrom].move(rowTo, colTo);
			board[rowTo][colTo] = board[rowFrom][colFrom];
			board[rowFrom][colFrom] = null;
			if (ChessMain.turn.equals("white")) {
				ChessMain.turn = "black";
			}
			else {
				ChessMain.turn = "white";
			}
			board[rowTo][colTo].clearSight();
		}
		else {
			board[rowFrom][colFrom].clearSight();
		}
		for (int i = 0; i < board[0].length; i++) {
			if (board[0][i] instanceof Pawn) {
				board[0][i] = new Queen("white", 0, i);
			}
			
		}
		for (int i = 0; i < board[0].length; i++) {
			if (board[7][i] instanceof Pawn) {
				board[7][i] = new Queen("black", 7, i);
			}
			
		}
	}
	
//	public void drawSights(int row, int col) {
//		board[row][col].calculateSight(board);
//		
//	}
	
}
