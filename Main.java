import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main {
    
    public static void main(String[] args){
	JFrame frame = new JFrame("Tetris");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(480,768);

	Game game = new Game();

	TPanel panel = new TPanel(game);
	panel.setBackground(Color.YELLOW);
	panel.setSize(360,720);

	frame.add(panel);
	frame.setVisible(true);

	Tetromino.test();
	
	System.out.println(game.toString());
    }
}
    
