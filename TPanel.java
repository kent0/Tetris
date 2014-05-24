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
	this.origin = new int[]{this.blockSize*2,this.blockSize*3};
	this.setSize(16*this.blockSize,20*this.blockSize);
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);

	Graphics2D g2 = (Graphics2D) g;
	
	this.setBackground(Color.BLACK);
	System.out.println(gameCopy);

	g2.setColor(Color.DARK_GRAY);
       	g2.drawRect(this.origin[1],this.origin[0],10*this.blockSize,20*this.blockSize);

	for (int i = 0; i < 20; i++) {
	    for (int j = 0; j < 10; j++) {
		if (gameCopy.getWell()[i+2][j] != 0) {
		    
		    g2.setColor(this.brushColor(this.gameCopy.getWell()[i+2][j]));
		    g2.fillRect(origin[1]+this.blockSize*(j),origin[0]+this.blockSize*i,this.blockSize,this.blockSize);
		}
	    }
	}

	g2.setColor(Color.DARK_GRAY);
	
	for (int i = 0; i < 20; i++) {
	    g2.drawLine(origin[1],origin[0]+this.blockSize*i,origin[1]+10*this.blockSize,origin[0]+this.blockSize*i);
	}

	for (int i = 0; i < 10; i++) {
	    g2.drawLine(origin[1]+this.blockSize*i,origin[0],origin[1]+this.blockSize*i,origin[0]+20*this.blockSize);
	}

	for (int i = 1; i < 4; i++) {
	    g2.setColor(this.brushColor(this.gameCopy.getTetrominos()[i].type()));
	    for (int j = 0; j < 4; j++) {
		g2.fillRect(this.origin[1]+this.blockSize*10+this.blockSize*(this.gameCopy.getTetrominos()[i].configuration()[j][1]+1)/2,(i-1)*this.blockSize*3+this.origin[0]+this.blockSize*this.gameCopy.getTetrominos()[i].configuration()[j][0]/2,this.blockSize/2,this.blockSize/2);
	    }
	}

	g2.setColor(this.brushColor(this.gameCopy.getTetrominos()[this.gameCopy.getTetrominos().length-1].type()));

	for (int j = 0; j < 4; j++) {
	    g2.fillRect(this.blockSize*(this.gameCopy.getTetrominos()[4].configuration()[j][1]+1)/2,this.origin[0]+this.blockSize*this.gameCopy.getTetrominos()[4].configuration()[j][0]/2,this.blockSize/2,this.blockSize/2);
	    if (this.gameCopy.getTetrominos()[4].type() == 0) {
		break;
	    }
	}

	g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	
	g2.setFont(new Font("Futura", Font.PLAIN, 14));

	g2.setColor(Color.WHITE);
	g2.drawString("Hold", this.blockSize, this.blockSize);
	g2.drawString("Score: " + this.gameCopy.getScore(), this.blockSize * 6, this.blockSize);
	g2.drawString("Next", this.blockSize * 14, this.blockSize);

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
	this.repaint();
    }
	
}
