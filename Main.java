
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main {
    static TPanel panel;
    static Game game;
    
    public static void main(String[] args) {
	JFrame frame = new JFrame("Tetris");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(480,768);

	game = new Game();

	panel = new TPanel(game);
	panel.setBackground(Color.YELLOW);
	panel.setSize(360,720);
	panel.setFocusable(true);
	panel.addKeyListener(new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
		    	switch (e.getExtendedKeyCode()) {
	    
			case KeyEvent.VK_W:
			    break;
			    
			case KeyEvent.VK_A:
			    game.left();
			    panel.repaint();
			    break;
			    
			case KeyEvent.VK_S:
			    game.down();
			    panel.repaint();
			    break;
			    
			case KeyEvent.VK_D:
			    game.right();
			    panel.repaint();
			    break;
			    
			default:
			    break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
		    System.out.println("Pressed " + e.getKeyChar());
		}
	    });

	frame.add(panel);
	frame.setVisible(true);

	Tetromino.test();

	Timer timer = new Timer(1000, new TimerListener());
	timer.start();
    }

    static class TimerListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    panel.repaint();
	    game.tick();
	}
    }

    
}
