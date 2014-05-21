import javax.swing.*;

public class TPanel extends JPanel {
    
    public TPanel() {
	setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);

	g.drawString("This is a test.",10,10);
    }
}
