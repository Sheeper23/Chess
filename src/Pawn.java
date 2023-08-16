

public class Pawn implements Piece {
	private int[][] lineOfSight;
	private String color;
	private int row;
	private int col;
	
	public Pawn(String color, int r, int c) {
		lineOfSight = new int[8][8];
		this.color = color;
		row = r;
		col = c;
	}
	
	@Override
	public void move(int r, int c) {
		row = r;
		col = c;
	}

	@Override
	public int[][] getSight() {
		return lineOfSight;
	}

	@Override
	public void calculateSight(Piece[][] board, boolean isSecondary) {
		if (CommonChecks.checkStalwart(board, row, col)) return;
		
		if (color.equals("black")) {
			if (row == 1) {
				if (board[row+1][col] == null) {
					lineOfSight[row+1][col] = 2;
					if (board[row+2][col] == null) {
						lineOfSight[row+2][col] = 2;
					}
				}
				if (col+1 <= 7 && board[row+1][col+1] != null && !board[row+1][col+1].getColor().equals(color)) {
					lineOfSight[row+1][col+1] = 1;
				}
				if (col-1 >= 0 && board[row+1][col-1] != null && !board[row+1][col-1].getColor().equals(color)) {
					lineOfSight[row+1][col-1] = 1;
				}
			}
			else if (row <= 6) {
				if (board[row+1][col] == null) {
					lineOfSight[row+1][col] = 2;
				}
				if (col+1 <= 7 && board[row+1][col+1] != null && !board[row+1][col+1].getColor().equals(color)) {
					lineOfSight[row+1][col+1] = 1;
				}
				if (col-1 >= 0 && board[row+1][col-1] != null && !board[row+1][col-1].getColor().equals(color)) {
					lineOfSight[row+1][col-1] = 1;
				}
			}
		}
		else {
			if (row == 6) {
				if (board[row-1][col] == null) {
					lineOfSight[row-1][col] = 2;
					if (board[row-2][col] == null) {
						lineOfSight[row-2][col] = 2;
					}
				}
				if (col+1 <= 7 && board[row-1][col+1] != null && !board[row-1][col+1].getColor().equals(color)) {
					lineOfSight[row-1][col+1] = 1;
				}
				if (col-1 >= 0 && board[row-1][col-1] != null && !board[row-1][col-1].getColor().equals(color)) {
					lineOfSight[row-1][col-1] = 1;
				}
				
			}
			else if (row >= 1) {
				if (board[row-1][col] == null) {
					lineOfSight[row-1][col] = 2;
				}
				if (col+1 <= 7 && board[row-1][col+1] != null && !board[row-1][col+1].getColor().equals(color)) {
					lineOfSight[row-1][col+1] = 1;
				}
				if (col-1 >= 0 && board[row-1][col-1] != null && !board[row-1][col-1].getColor().equals(color)) {
					lineOfSight[row-1][col-1] = 1;
				}
			}
		}
		
		if (isSecondary) return;
		
		for (int k = 0; k < lineOfSight.length; k++) {
			for (int l = 0; l < lineOfSight[0].length; l++) {
				Piece[][] moveSimulation = new Piece[8][8];
				for (int i = 0; i < moveSimulation.length; i++) {
					for (int j = 0; j < moveSimulation[0].length; j++) {
						moveSimulation[i][j] = board[i][j];
					}
				}
				if (lineOfSight[k][l] != 0) {
					moveSimulation[row][col] = null;
					moveSimulation[k][l] = new Pawn(color, k, l);
					int kingY = 0;
					int kingX = 0;
					oute: for (int m = 0; m < moveSimulation.length; m++) {
						for (int n = 0; n < moveSimulation[0].length; n++) {
							if (moveSimulation[m][n] != null && moveSimulation[m][n].getColor().equals(color) && moveSimulation[m][n] instanceof King) {
								kingY = m;
								kingX = n;
								break oute;
							}
						}
					}
					//System.out.println("king on space " + kingY + " " + kingX);
					//System.out.println("testing LOS of space " + k + " " + l);
					
					outer: for (int i = 0; i < moveSimulation.length; i++) {
						for (int j = 0; j < moveSimulation[0].length; j++) {
							if (moveSimulation[i][j] != null && !moveSimulation[i][j].getColor().equals(color)) {
								moveSimulation[i][j].calculateSight(moveSimulation, true);
								//System.out.println("calc sight for piece on space " + i + " " + j);
								if (moveSimulation[i][j].getSight()[kingY][kingX] == 1) {
									lineOfSight[k][l] = 0;
									//System.out.println("violation on square " + k + " " + l);
									moveSimulation[i][j].clearSight();
									break outer;
								}
								moveSimulation[i][j].clearSight();
							}
						}
					}
					//System.out.println();
					
				}
			}
		}
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getCol() {
		return col;
	}
	
	public String toString() {
		return "Pawn";
	}
	
	@Override
	public void clearSight() {
		// TODO Auto-generated method stub
		for (int i = 0; i < lineOfSight.length; i++) {
			for (int j = 0; j < lineOfSight[0].length; j++) {
				lineOfSight[i][j] = 0;
			}
		}
	}

}
