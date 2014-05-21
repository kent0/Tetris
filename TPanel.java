import javax.swing.*;
import java.awt.*;

public class TPanel extends JPanel {
    int[] origin;
    final int wellWidth = 360;
    final int wellHeight = 720;
    Game game;
    
    public TPanel(Game game) {
	this.game = game;
	origin = new int[]{0,0};
	setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);

       	g.drawRect(0,0,wellWidth,wellHeight);
	int[][] well = new int[1][1];
	if (well.length == 20 && well[0].length == 10) {
	    for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 10; j++) {
		    if (well[i][j] != 0) {
			g.fillRect(origin[0]+36*i,origin[1]+36*j,36,36);
		    }
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
