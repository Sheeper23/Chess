

public class Fen {
	public static void load(Piece[][] board, String fen) { // very simple for now
		int row = 0;
		int col = 0;
		for (int i = 0; i < fen.length(); i++) {
			String cur = ""+fen.charAt(i);
			
			try {
				for (int j = 0; j < Integer.parseInt(cur); j++) {
					board[row][col] = null;
					col++;
				}
			} catch (NumberFormatException e) {
				switch(cur) {
				case "r":
					board[row][col] = new Rook("black", row, col);
					col++;
					break;
				case "n":
					board[row][col] = new Knight("black", row, col);
					col++;
					break;
				case "b":
					board[row][col] = new Bishop("black", row, col);
					col++;
					break;
				case "q":
					board[row][col] = new Queen("black", row, col);
					col++;
					break;
				case "k":
					board[row][col] = new King("black", row, col);
					col++;
					break;
				case "u":
					board[row][col] = new Unicorn("black", row, col);
					col++;
					break;
				case "p":
					board[row][col] = new Pawn("black", row, col);
					col++;
					break;
				case "w":
					board[row][col] = new Warden("black", row, col);
					col++;
					break;
				case "R":
					board[row][col] = new Rook("white", row, col);
					col++;
					break;
				case "N":
					board[row][col] = new Knight("white", row, col);
					col++;
					break;
				case "B":
					board[row][col] = new Bishop("white", row, col);
					col++;
					break;
				case "Q":
					board[row][col] = new Queen("white", row, col);
					col++;
					break;
				case "K":
					board[row][col] = new King("white", row, col);
					col++;
					break;
				case "U":
					board[row][col] = new Unicorn("white", row, col);
					col++;
					break;
				case "P":
					board[row][col] = new Pawn("white", row, col);
					col++;
					break;
				case "W":
					board[row][col] = new Warden("white", row, col);
					col++;
					break;
				case "/":
					row++;
					col = 0;
					break;
				}
			}
			
			
			
		}
	}
}
