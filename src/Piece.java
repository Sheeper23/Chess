

public interface Piece {
	public void move(int r, int c);
	public int[][] getSight();
	public void calculateSight(Piece[][] board, boolean isSecondary);
	public void clearSight();
	public String getColor();
	public int getRow();
	public int getCol();
}
