package interfaz;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelTablero extends JPanel
{
	private static final String IMAGE_FILE_PATH = "data/top10.csv";

	private MainWindow father;
	private int dimension;
	
	public PanelTablero(MainWindow father, int dimension)
	{
		this.father = father;
		this.dimension = dimension;
		setLayout(new GridLayout(dimension, dimension));
	}
}
