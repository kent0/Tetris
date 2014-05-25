import javax.swing.*;
import java.awt.*;
import java.awt.RenderingHints;

public class TPanel extends JPanel {
    private int[] origin;
    private Game gameCopy;
    private int blockSize;
    
    public TPanel(Game game) {
	this.gameCopy = game;
	this.blockSize = 24;
	this.origin = new int[]{this.blockSize * 2, this.blockSize * 3};
	this.setSize(16 * this.blockSize, 20 * this.blockSize);
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);

	Graphics2D g2 = (Graphics2D) g;
	
	if (gameCopy.getGameIsOver()) {
	    
	    int s = 10;
	    origin = new int[]{24 * 5, 92};
	    
	    g2.setColor(Color.white);
            g2.fillRect(origin[1],origin[0],20*s,s*11);
            g2.setColor(Color.black);
            //G
            g2.fillRect(origin[1]+s,origin[0]+s,2*s,3*s);
            g2.fillRect(origin[1]+3*s,origin[0]+s,s,2*s);
            g2.fillRect(origin[1]+4*s,origin[0],s,5*s);
            //A
            g2.fillRect(origin[1]+6*s,origin[0]+s,2*s,s);
            g2.fillRect(origin[1]+6*s,origin[0]+3*s,2*s,2*s);
            g2.fillRect(origin[1]+9*s,origin[0],s,5*s);
            //M
            g2.fillRect(origin[1]+11*s,origin[0]+s,s,4*s);
            g2.fillRect(origin[1]+13*s,origin[0]+s,s,4*s);
            g2.fillRect(origin[1]+15*s,origin[0],s,5*s);
            //E
            g2.fillRect(origin[1]+17*s,origin[0]+s,3*s,s);
            g2.fillRect(origin[1]+17*s,origin[0]+3*s,3*s,s);
            //Nline
            g2.fillRect(origin[1],origin[0]+5*s,20*s,s);
            //O
            g2.fillRect(origin[1]+s,origin[0]+7*s,2*s,3*s);
            g2.fillRect(origin[1]+4*s,origin[0]+6*s,s,5*s);
            //V
            g2.fillRect(origin[1]+5*s,origin[0]+8*s,s,3*s);
            g2.fillRect(origin[1]+6*s,origin[0]+9*s,s,2*s);
            g2.fillRect(origin[1]+6*s,origin[0]+6*s,3*s,2*s);
            g2.fillRect(origin[1]+7*s,origin[0]+8*s,s,s);
            g2.fillRect(origin[1]+8*s,origin[0]+9*s,s,2*s);
            g2.fillRect(origin[1]+9*s,origin[0]+8*s,s,3*s);
            g2.fillRect(origin[1]+10*s,origin[0]+6*s,s,5*s);
            //E
            g2.fillRect(origin[1]+12*s,origin[0]+7*s,3*s,s);
            g2.fillRect(origin[1]+12*s,origin[0]+9*s,3*s,s);
            g2.fillRect(origin[1]+15*s,origin[0]+6*s,s,5*s);
            //R
            g2.fillRect(origin[1]+17*s,origin[0]+7*s,2*s,s);
            g2.fillRect(origin[1]+17*s,origin[0]+9*s,2*s,2*s);
            g2.fillRect(origin[1]+19*s,origin[0]+8*s,s,s);
	    
	} else {
	    
	    setBackground(Color.BLACK);
	    System.out.println(gameCopy);

	    g2.setColor(Color.DARK_GRAY);
	    g2.drawRect(origin[1],origin[0], 10 * blockSize,
			20 * blockSize);

	    for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 10; j++) {
		    if (gameCopy.getWell()[i+2][j] != 0) {
		    
			g2.setColor(brushColor(gameCopy.getWell()[i + 2][j]));
			g2.fillRect(origin[1] + blockSize * j,
				    origin[0] + blockSize * i,
				    blockSize,blockSize);
		    }
		}
	    }

	    g2.setColor(Color.DARK_GRAY);
	
	    for (int i = 0; i < 20; i++) {
		g2.drawLine(origin[1],origin[0] + blockSize * i,
			    origin[1] + 10 * blockSize,
			    origin[0] + blockSize * i);
	    }

	    for (int i = 0; i < 10; i++) {
		g2.drawLine(origin[1] + blockSize * i,
			    origin[0],
			    origin[1] + blockSize * i,
			    origin[0] + 20 *blockSize);
	    }

	    for (int i = 1; i < 4; i++) {
		g2.setColor(brushColor(gameCopy.getTetrominos()[i].type()));
		for (int j = 0; j < 4; j++) {
		    g2.fillRect(origin[1] + blockSize * 10 +
				blockSize * (gameCopy.getTetrominos()[i].configuration()[j][1] + 1) / 2,
				(i - 1) * blockSize * 3 +
				origin[0] + blockSize *
				gameCopy.getTetrominos()[i].configuration()[j][0] / 2,
				blockSize / 2, blockSize / 2);
		}
	    }

	    g2.setColor(brushColor(gameCopy.getTetrominos()[gameCopy.getTetrominos().length - 1].type()));

	    for (int j = 0; j < 4; j++) {
		g2.fillRect(blockSize *
			    (gameCopy.getTetrominos()[4].configuration()[j][1] + 1) / 2,
			    origin[0] + blockSize *
			    gameCopy.getTetrominos()[4].configuration()[j][0] / 2,
			    blockSize / 2, blockSize / 2);
		if (gameCopy.getTetrominos()[4].type() == 0) {
		    break;
		}
	    }

	    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2.setFont(new Font("Futura", Font.PLAIN, 16));
	    g2.setColor(Color.WHITE);
	    g2.drawString("Hold", blockSize, blockSize);
	    g2.drawString("Score: " + gameCopy.getScore(),
			  blockSize * 6, blockSize);
	    g2.drawString("Next", blockSize * 27/2, blockSize);

	}

    }
    
    public Color brushColor(int brushType) {
		    
	switch (brushType) {
			
	case -1:
	    return Color.LIGHT_GRAY;
			
	case 1:
	    return Color.CYAN;
   
	case 2:
	    return Color.YELLOW;

	case 3:
	    return Color.MAGENTA;

	case 4:
	    return Color.BLUE;

	case 5:
	    return Color.ORANGE;

	case 6:
	    return Color.GREEN;

	case 7:
	    return Color.RED;
			
	default:
	    return Color.BLACK;
	}
    }

    public void update() {
	repaint();
    }
}
