import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args){
	JFrame frame = new JFrame("Tetris");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(480,768);

	JPanel panel = new JPanel();
	panel.setBackground(Color.YELLOW);
	panel.setSize(360,720);

	JLabel label = new JLabel("Test Label");
	JButton button = new JButton();

	panel.add(button);
	panel.add(label);
	frame.add(panel);

	frame.setVisible(true);
    }
}
    
