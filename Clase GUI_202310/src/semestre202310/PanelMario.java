package semestre202310;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PanelMario extends JPanel
{
	private int x = 0;
	private int y = 400;

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.CYAN);
		g2d.fillRect(x += 10,  y-=10, 50, 50);
	}
}
