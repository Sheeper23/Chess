

public class CommonChecks {
	public static boolean checkStalwart(Piece[][] board, int row, int col) {
		if (row-1 >= 0 && col-1 >= 0 && board[row-1][col-1] instanceof Warden && !board[row][col].getColor().equals(board[row-1][col-1].getColor())) {
			return true;
			
		}
		if (row-1 >= 0 && board[row-1][col] instanceof Warden && !board[row][col].getColor().equals(board[row-1][col].getColor())) {
			return true;
			
		}
		if (row-1 >= 0 && col+1 <= 7 && board[row-1][col+1] instanceof Warden && !board[row][col].getColor().equals(board[row-1][col+1].getColor())) {
			return true;
			
		}
		if (col+1 <= 7 && board[row][col+1] instanceof Warden && !board[row][col].getColor().equals(board[row][col+1].getColor())) {
			return true;
			
		}
		if (row+1 <= 7 && col+1 <= 7 && board[row+1][col+1] instanceof Warden && !board[row][col].getColor().equals(board[row+1][col+1].getColor())) {
			return true;
			
		}
		if (row+1 <= 7 && board[row+1][col] instanceof Warden && !board[row][col].getColor().equals(board[row+1][col].getColor())) {
			return true;
			
		}
		if (row+1 <= 7 && col-1 >= 0 && board[row+1][col-1] instanceof Warden && !board[row][col].getColor().equals(board[row+1][col-1].getColor())) {
			return true;
			
		}
		if (col-1 >= 0 && board[row][col-1] instanceof Warden && !board[row][col].getColor().equals(board[row][col-1].getColor())) {
			return true;
			
		}
		
		return false;
	}
}
