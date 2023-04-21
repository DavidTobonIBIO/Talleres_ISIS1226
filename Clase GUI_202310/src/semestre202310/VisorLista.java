package semestre202310;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class VisorLista extends JLabel implements ListCellRenderer<Click>
{
	private Click modelo;

	public VisorLista()
	{
		super();
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.CYAN);
		g2d.fillRect(10,  10, 50, 50);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Click> list, Click value, int index,
			boolean isSelected, boolean cellHasFocus)
	{
		modelo = value;
		/*
		 * setOpaque(true); if (isSelected) setBackground(Color.BLACK); else
		 * setBackground(Color.RED);
		 * 
		 * int numero = value.getNumero(); String estrellas = ""; for(int i = 0;
		 * i<numero; i++) { estrellas += "*"; } setText(estrellas);
		 */
		repaint();
		return this;
		// return super.getListCellRendererComponent(list, value, index, isSelected,
		// cellHasFocus);
	}
}
