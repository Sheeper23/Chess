

import java.util.*;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessPanel extends JPanel {
	private ChessBoard board;
	private int[][] shownSight;
	private Point currentPt;
	private Point prevPt;
	private final int DIMENS = 1000;
	
	public ChessPanel() {
		board = new ChessBoard();
		shownSight = new int[8][8];
		ClickListener clickListener = new ClickListener();
		DragListener dragListener = new DragListener();
		setLayout(new GridLayout(8, 8, 0, 0));
		addMouseListener(clickListener);
		addMouseMotionListener(dragListener);
		updateBoard();
	}
	
	public int getDIMENS() {
		return DIMENS;
	}
	
	public void updateBoard() {
		removeAll();
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[0].length; j++) {
				JLabel pane;
				if (board.getBoard()[i][j] != null) {
					try {
						pane = new JLabel(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream(board.getBoard()[i][j].getColor() + board.getBoard()[i][j].toString() + ".png")).getScaledInstance(DIMENS/8, DIMENS/8, Image.SCALE_SMOOTH)));
					} catch (IOException e) {
						pane = new JLabel();
						e.printStackTrace();
					}
				}
				else {
					pane = new JLabel();
				}
				if ((i + j) % 2 == 0) {
					pane.setBackground(new Color(236, 235, 206));
					
					if (board.getBoard()[i][j] != null && CommonChecks.checkStalwart(board.getBoard(), i, j)) {
						pane.setBackground(new Color(118, 118, 231));
					}
					
					if (shownSight[i][j] != 0) {
						pane.setBackground(new Color(243, 189, 177));
					}
					if (prevPt != null && (int)((prevPt.getY()+(DIMENS/46.2))/(DIMENS/8)) == i && (int)(prevPt.getX()/(DIMENS/8)) == j) {
						pane.setBackground(new Color(230, 228, 138));
					}
					
				}
				else {
					pane.setBackground(new Color(105, 139, 80));
					
					if (board.getBoard()[i][j] != null && CommonChecks.checkStalwart(board.getBoard(), i, j)) {
						pane.setBackground(new Color(53, 70, 168));
					}
					
					if (shownSight[i][j] != 0) {
						pane.setBackground(new Color(170, 144, 113));
					}
					if (prevPt != null && (int)((prevPt.getY()+(DIMENS/46.2))/(DIMENS/8)) == i && (int)(prevPt.getX()/(DIMENS/8)) == j) {
						pane.setBackground(new Color(190, 201, 88));
					}
				}
				
				pane.setOpaque(true);
				add(pane);
			}
		}
		revalidate();
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//whiteKing.paintIcon(this, g, (int)imageCorner.getX(), (int)imageCorner.getY());
		
		
		//g.drawImage(whiteKing.getImage(), (int)imageCorner.getX(), (int)imageCorner.getY(), null);
	}
	
	private class ClickListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (prevPt == null && board.getBoard()[(int)((e.getPoint().getY()+(DIMENS/46.2))/(DIMENS/8))][(int)(e.getPoint().getX()/(DIMENS/8))] != null && board.getBoard()[(int)((e.getPoint().getY()+(DIMENS/46.2))/(DIMENS/8))][(int)(e.getPoint().getX()/(DIMENS/8))].getColor().equals(ChessMain.turn)) {
					prevPt = e.getPoint();
					board.getBoard()[(int)((prevPt.getY()+(DIMENS/46.2))/(DIMENS/8))][(int)(prevPt.getX()/(DIMENS/8))].calculateSight(board.getBoard(), false);
					for (int i = 0; i < board.getBoard()[(int)((prevPt.getY()+(DIMENS/46.2))/(DIMENS/8))][(int)(prevPt.getX()/(DIMENS/8))].getSight().length; i++) {
						for (int j = 0; j < board.getBoard()[(int)((prevPt.getY()+(DIMENS/46.2))/(DIMENS/8))][(int)(prevPt.getX()/(DIMENS/8))].getSight()[0].length; j++) {
							shownSight[i][j] = board.getBoard()[(int)((prevPt.getY()+(DIMENS/46.2))/(DIMENS/8))][(int)(prevPt.getX()/(DIMENS/8))].getSight()[i][j];
							//System.out.print(shownSight[i][j] + " ");
						}
						//System.out.println();
					}
					//System.out.println();
					
					
					updateBoard();
					board.getBoard()[(int)((prevPt.getY()+(DIMENS/46.2))/(DIMENS/8))][(int)(prevPt.getX()/(DIMENS/8))].clearSight();
					for (int i = 0; i < shownSight.length; i++) {
						for (int j = 0; j < shownSight[0].length; j++ ) {
							shownSight[i][j] = 0;
						}
					}
					
					//board.drawSights((int)(prevPt.getY()/(DIMENS/8)), (int)(prevPt.getX()/(DIMENS/8)));
			}
			else if (prevPt != null && currentPt == null) {
				currentPt = e.getPoint();
				
				board.move((int)((prevPt.getY()+(DIMENS/46.2))/(DIMENS/8)), (int)(prevPt.getX()/(DIMENS/8)), (int)((currentPt.getY()+(DIMENS/46.2))/(DIMENS/8)), (int)(currentPt.getX()/(DIMENS/8)));
				
				
				currentPt = null;
				prevPt = null;
				updateBoard();
			}
			else {
				currentPt = null;
				prevPt = null;
				updateBoard();
			}
			// !((currentPt.getX() >= imageCorner.getX() && currentPt.getX() <= imageCorner.getX()+(DIMENS/8)) && (currentPt.getY() >= imageCorner.getY() && currentPt.getY() <= imageCorner.getY()+(DIMENS/8)))
		}
	}
	
	private class DragListener extends MouseMotionAdapter {
//		public void mouseDragged(MouseEvent e) {
//			currentPt = e.getPoint();
//			if (!(currentPt.getX() < 0 || currentPt.getX() > DIMENS || currentPt.getY() < 0 || currentPt.getY() > DIMENS)) {
//			
//				if ((int)(currentPt.getX() - prevPt.getX()) + (imageCorner.getX()+DIMENS/8) > DIMENS) {
//					if ((int)(currentPt.getY() - prevPt.getY()) + (imageCorner.getY()+DIMENS/8)+(DIMENS/28) > DIMENS) {
//						imageCorner.translate((int)((DIMENS-(DIMENS/8)) - imageCorner.getX()), (int)((DIMENS-(DIMENS/8)) - imageCorner.getY()-(DIMENS/28)));
//					}
//					else if ((int)(currentPt.getY() - prevPt.getY()) + imageCorner.getY() < 0) {
//						imageCorner.translate((int)((DIMENS-(DIMENS/8)) - imageCorner.getX()), (int)(0 - imageCorner.getY()));
//					}
//					else {
//						imageCorner.translate((int)((DIMENS-(DIMENS/8)) - imageCorner.getX()), (int)(currentPt.getY() - prevPt.getY()));
//					}
//				}
//				else if ((int)(currentPt.getX() - prevPt.getX()) + imageCorner.getX() < 0) {
//					if ((int)(currentPt.getY() - prevPt.getY()) + (imageCorner.getY()+DIMENS/8)+(DIMENS/28) > DIMENS) {
//						imageCorner.translate((int)(0 - imageCorner.getX()), (int)((DIMENS-(DIMENS/8)) - imageCorner.getY()-(DIMENS/28)));
//					}
//					else if ((int)(currentPt.getY() - prevPt.getY()) + imageCorner.getY() < 0) {
//						imageCorner.translate((int)(0 - imageCorner.getX()), (int)(0 - imageCorner.getY()));
//					}
//					else {
//						imageCorner.translate((int)(0 - imageCorner.getX()), (int)(currentPt.getY() - prevPt.getY()));
//					}
//				}
//				else {
//					if ((int)(currentPt.getY() - prevPt.getY()) + (imageCorner.getY()+DIMENS/8)+(DIMENS/28) > DIMENS) {
//						imageCorner.translate((int)(currentPt.getX() - prevPt.getX()), (int)((DIMENS-(DIMENS/8)) - imageCorner.getY()-(DIMENS/28)));
//					}
//					else if ((int)(currentPt.getY() - prevPt.getY()) + imageCorner.getY() < 0) {
//						imageCorner.translate((int)(currentPt.getX() - prevPt.getX()), (int)(0 - imageCorner.getY()));
//					}
//					else {
//						imageCorner.translate((int)(currentPt.getX() - prevPt.getX()), (int)(currentPt.getY() - prevPt.getY()));
//					}
//				}
//			
//			}
//			
//			prevPt = currentPt;
//			repaint();
//		}
	}
}
