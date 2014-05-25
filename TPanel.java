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
	
	if (this.gameCopy.gameIsOver()) {
	    
	    int S = 10;
	    this.origin = new int[]{24*5,92};
	    g2.setColor(Color.white);
            g2.fillRect(this.origin[1],this.origin[0],20*S,S*11);
            g2.setColor(Color.black);
            //G
            g2.fillRect(this.origin[1]+S,this.origin[0]+S,2*S,3*S);
            g2.fillRect(this.origin[1]+3*S,this.origin[0]+S,S,2*S);
            g2.fillRect(this.origin[1]+4*S,this.origin[0],S,5*S);
            //A
            g2.fillRect(this.origin[1]+6*S,this.origin[0]+S,2*S,S);
            g2.fillRect(this.origin[1]+6*S,this.origin[0]+3*S,2*S,2*S);
            g2.fillRect(this.origin[1]+9*S,this.origin[0],S,5*S);
            //M
            g2.fillRect(this.origin[1]+11*S,this.origin[0]+S,S,4*S);
            g2.fillRect(this.origin[1]+13*S,this.origin[0]+S,S,4*S);
            g2.fillRect(this.origin[1]+15*S,this.origin[0],S,5*S);
            //E
            g2.fillRect(this.origin[1]+17*S,this.origin[0]+S,3*S,S);
            g2.fillRect(this.origin[1]+17*S,this.origin[0]+3*S,3*S,S);
            //Nline
            g2.fillRect(this.origin[1],this.origin[0]+5*S,20*S,S);
            //O
            g2.fillRect(this.origin[1]+S,this.origin[0]+7*S,2*S,3*S);
            g2.fillRect(this.origin[1]+4*S,this.origin[0]+6*S,S,5*S);
            //V
            g2.fillRect(this.origin[1]+5*S,this.origin[0]+8*S,S,3*S);
            g2.fillRect(this.origin[1]+6*S,this.origin[0]+9*S,S,2*S);
            g2.fillRect(this.origin[1]+6*S,this.origin[0]+6*S,3*S,2*S);
            g2.fillRect(this.origin[1]+7*S,this.origin[0]+8*S,S,S);
            g2.fillRect(this.origin[1]+8*S,this.origin[0]+9*S,S,2*S);
            g2.fillRect(this.origin[1]+9*S,this.origin[0]+8*S,S,3*S);
            g2.fillRect(this.origin[1]+10*S,this.origin[0]+6*S,S,5*S);
            //E
            g2.fillRect(this.origin[1]+12*S,this.origin[0]+7*S,3*S,S);
            g2.fillRect(this.origin[1]+12*S,this.origin[0]+9*S,3*S,S);
            g2.fillRect(this.origin[1]+15*S,this.origin[0]+6*S,S,5*S);
            //R
            g2.fillRect(this.origin[1]+17*S,this.origin[0]+7*S,2*S,S);
            g2.fillRect(this.origin[1]+17*S,this.origin[0]+9*S,2*S,2*S);
            g2.fillRect(this.origin[1]+19*S,this.origin[0]+8*S,S,S);
	    
	} else {
	    
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
	
	    g2.setFont(new Font("Futura", Font.PLAIN, 16));

	    g2.setColor(Color.WHITE);
	    g2.drawString("Hold", this.blockSize, this.blockSize);
	    g2.drawString("Score: " + this.gameCopy.getScore(), this.blockSize * 6, this.blockSize);
	    g2.drawString("Next", this.blockSize * 27/2, this.blockSize);

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
	this.repaint();
    }
	
}
