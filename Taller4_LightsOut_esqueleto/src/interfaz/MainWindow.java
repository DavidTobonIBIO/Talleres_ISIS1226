package interfaz;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Top10;


public class MainWindow extends JFrame
{
	private static final String TOP10_FILE_PATH = "data/top10.csv";

	private Top10 top10;
	private File dataFile;
	private Top10Window ventanaTop10;
	private PanelOpciones panelNorth;
	private PanelTablero panelCenter;
	private PanelJugadores panelEast;
	private PanelInfo panelSouth;
	
	public MainWindow()
	{
		this.dataFile = new File(TOP10_FILE_PATH);
		this.top10 = new Top10();
		top10.cargarRecords(dataFile);
		
		setLayout(new BorderLayout());
		
		
		panelNorth = new PanelOpciones(this);
		add(panelNorth, BorderLayout.NORTH);
		panelCenter = new PanelTablero(this, 5);
		add(panelCenter, BorderLayout.CENTER);
		panelEast = new PanelJugadores(this);
		add(panelEast, BorderLayout.EAST);
		panelSouth = new PanelInfo(this);
		add(panelSouth, BorderLayout.SOUTH);
			
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				salvarTop10();
			}
		});
		
		setTitle("Mi Ventana");
		setSize(700, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void salvarTop10()
	{
		try
		{
			top10.salvarRecords(dataFile);
		} catch (FileNotFoundException | UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		new MainWindow();
	}
}
