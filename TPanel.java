import javax.swing.*;
import java.awt.*;

public class TPanel extends JPanel {
    private int[] origin;
    private int wellWidth = 360;
    private int wellHeight = 720;
    private JLabel scoreLabel, lineLabel, levelLabel;
    private Game gameCopy;
    private int blockSize;
    
    public TPanel(Game game) {
	scoreLabel = new JLabel("Score: ");
	lineLabel = new JLabel("Line: ");
	levelLabel = new JLabel("Level: ");

	scoreLabel.setForeground(Color.DARK_GRAY);
	lineLabel.setForeground(Color.DARK_GRAY);
	levelLabel.setForeground(Color.DARK_GRAY);

	this.add(scoreLabel);
	this.add(lineLabel);
	this.add(levelLabel);
	
	this.gameCopy = game;
	this.blockSize = 36;
	this.origin = new int[]{this.blockSize,this.blockSize};
	this.setBackground(Color.BLACK);
	this.setSize(10*this.blockSize,20*this.blockSize);
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	System.out.println(gameCopy);

	g.setColor(Color.DARK_GRAY);
       	g.drawRect(this.origin[0],this.origin[1],10*this.blockSize,20*this.blockSize);

	for (int i = 0; i < 20; i++) {
	    for (int j = 0; j < 10; j++) {
		if (gameCopy.getWell()[i+2][j] != 0) {
		    Color brushColor;
		    
		    switch (gameCopy.getWell()[i+2][j]) {
			
		    case -1:
			brushColor = Color.LIGHT_GRAY;
			break;
			
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

	for (int i = 0; i < 3; i++) {
	    
	}
					 
    }

    public void update() {
	this.scoreLabel.setText("Score: " + this.gameCopy.getScore());
	this.levelLabel.setText("Level: " + this.gameCopy.level());
	this.lineLabel.setText("Line: " + this.gameCopy.getLine());
	this.repaint();
    }
	
}
