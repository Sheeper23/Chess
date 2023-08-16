

import java.awt.GridLayout;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class ChessWindow extends JFrame {
	private ChessPanel gridPanel;
	
	public ChessWindow() {
		super("Chess");
		gridPanel = new ChessPanel();
		
		
		add(gridPanel);
		
		
		setSize(gridPanel.getDIMENS(), gridPanel.getDIMENS());
        setLocation(450, 50);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
}
