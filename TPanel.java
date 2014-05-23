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
	this.setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	System.out.println(gameCopy);

	g.setColor(Color.DARK_GRAY);
       	g.drawRect(0,0,wellWidth,wellHeight);

	for (int i = 0; i < 20; i++) {
	    for (int j = 0; j < 10; j++) {
		if (gameCopy.getWell()[i+2][j] != 0) {
		    Color brushColor;
		    
		    switch (gameCopy.getWell()[i+2][j]) {
			
		    case 1:
			brushColor = Color.CYAN;
			break;
	

		    case 2:
			brushColor = Color.YELLOW;
			break;

		    case 3:
			brushColor = Color.MAGENTA;
			break;

		    case 4:
			brushColor = Color.BLUE;
			break;

		    case 5:
			brushColor = Color.ORANGE;
			break;

		    case 6:
			brushColor = Color.GREEN;
			break;

		    case 7:
			brushColor = Color.RED;
			break;
			
		    default:
			brushColor = Color.BLACK;
		    }
		    
		    g.setColor(brushColor);
		    g.fillRect(origin[1]+36*(j),origin[0]+36*i,36,36);
		}
	    }
	}

	g.setColor(Color.DARK_GRAY);
	
	for (int i = 0; i < 20; i++) {
	    g.drawLine(origin[1],origin[0]+36*i,origin[1]+wellWidth,origin[0]+36*i);
	}

	for (int i = 0; i < 10; i++) {
	    g.drawLine(origin[1]+36*i,origin[0],origin[1]+36*i,origin[0]+wellHeight);
	}
					 
    }
}
