import javax.swing.*;
import java.awt.*;

public class TPanel extends JPanel {
    private int[] origin;
    private JLabel scoreLabel, lineLabel, levelLabel;
    private Game gameCopy;
    private int blockSize;
    
    public TPanel(Game game) {
	scoreLabel = new JLabel("Score: ");
	lineLabel = new JLabel("Line: ");
	levelLabel = new JLabel("Level: ");

	scoreLabel.setForeground(Color.LIGHT_GRAY);
	lineLabel.setForeground(Color.LIGHT_GRAY);
	levelLabel.setForeground(Color.LIGHT_GRAY);

	this.add(scoreLabel);
	this.add(lineLabel);
	this.add(levelLabel);
	
	this.gameCopy = game;
	this.blockSize = 36;
	this.origin = new int[]{this.blockSize,this.blockSize};
	this.setSize(10*this.blockSize,20*this.blockSize);
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	this.setBackground(Color.BLACK);
	System.out.println(gameCopy);

	g.setColor(Color.DARK_GRAY);
       	g.drawRect(this.origin[0],this.origin[1],10*this.blockSize,20*this.blockSize);

	for (int i = 0; i < 20; i++) {
	    for (int j = 0; j < 10; j++) {
		if (gameCopy.getWell()[i+2][j] != 0) {
		    
		    g.setColor(this.brushColor(this.gameCopy.getWell()[i+2][j]));
		    g.fillRect(origin[1]+this.blockSize*(j),origin[0]+this.blockSize*i,this.blockSize,this.blockSize);
		}
	    }
	}

	g.setColor(Color.DARK_GRAY);
	
	for (int i = 0; i < 20; i++) {
	    g.drawLine(origin[1],origin[0]+this.blockSize*i,origin[1]+10*this.blockSize,origin[0]+this.blockSize*i);
	}

	for (int i = 0; i < 10; i++) {
	    g.drawLine(origin[1]+this.blockSize*i,origin[0],origin[1]+this.blockSize*i,origin[0]+20*this.blockSize);
	}

	for (int i = 1; i < 4; i++) {
	    g.setColor(this.brushColor(this.gameCopy.getTetrominos()[i].type()));
	    for (int j = 0; j < 4; j++) {
		g.fillRect(this.origin[1]+this.blockSize*10+this.blockSize*(this.gameCopy.getTetrominos()[i].configuration()[j][1]+1)/2,(i-1)*this.blockSize*3+this.origin[0]+this.blockSize*this.gameCopy.getTetrominos()[i].configuration()[j][0]/2,this.blockSize/2,this.blockSize/2);
	    }
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
	this.scoreLabel.setText("Score: " + this.gameCopy.getScore());
	this.levelLabel.setText("Level: " + this.gameCopy.level());
	this.lineLabel.setText("Line: " + this.gameCopy.getLine());
	this.repaint();
    }
	
}
