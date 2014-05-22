import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main {
    static TPanel panel;
    
    public static void main(String[] args){
	JFrame frame = new JFrame("Tetris");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(480,768);

	Game game = new Game();

	panel = new TPanel(game);
	panel.setBackground(Color.YELLOW);
	panel.setSize(360,720);

	frame.add(panel);
	frame.setVisible(true);

	Tetromino.test();
	
	System.out.println(game.toString());

	Timer timer = new Timer(1000, new TimerListener());
	timer.start();
    }

    static class TimerListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    System.out.println("Test");
	    panel.repaint();
	}
    }   
}
