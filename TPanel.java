import javax.swing.*;
import java.awt.*;

public class TPanel extends JPanel {
    private int[] origin;
    private int wellWidth = 360;
    private int wellHeight = 720;
    private Game gameCopy;
    
    public TPanel(Game game) {
	this.gameCopy = game;
	this.origin = new int[]{0,0};
	setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	System.out.println(gameCopy);

       	g.drawRect(0,0,wellWidth,wellHeight);

	for (int i = 0; i < 20; i++) {
	    for (int j = 0; j < 10; j++) {
		if (gameCopy.getWell()[i+2][j] != 0) {
		    g.fillRect(origin[1]+36*(j),origin[0]+36*i,36,36);
		}
	    }
	}
	
	for (int i = 0; i < 20; i++) {
	    g.drawLine(origin[1],origin[0]+36*i,origin[1]+wellWidth,origin[0]+36*i);
	}

	for (int i = 0; i < 10; i++) {
	    g.drawLine(origin[1]+36*i,origin[0],origin[1]+36*i,origin[0]+wellHeight);
	}
					 
    }
}
