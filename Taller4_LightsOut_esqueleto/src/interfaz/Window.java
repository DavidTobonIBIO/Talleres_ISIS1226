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

import com.formdev.flatlaf.FlatLightLaf;

import uniandes.dpoo.taller4.modelo.Top10;

public class Window extends JFrame
{
	private static final String DATA_FILE_PATH = "data/top10.csv";
	private static final Color FOREGROUND_COLOR = Color.WHITE;
	private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
	private File dataFile;
	private Top10 top10;
	private OptionsPanel northPanel;
//	private BoardPanel centerPanel;
	private PlayersPanel playersPanel;
	private JPanel eastPanel;
	private InfoPanel southPanel;
	
	public Window()
	{
		dataFile = new File(DATA_FILE_PATH);
		top10 = new Top10();
		loadTop10();
		
		setLayout(new BorderLayout());
		
		northPanel = new OptionsPanel(this, FOREGROUND_COLOR, BACKGROUND_COLOR);
		add(northPanel, BorderLayout.NORTH);
		
//		centerPanel = new BoardPanel();
//		add(centerPanel, BorderLayout.CENTER);
		
		playersPanel = new PlayersPanel(this, FOREGROUND_COLOR, BACKGROUND_COLOR);
		eastPanel = new JPanel();
		eastPanel.setLayout(new GridBagLayout());
		eastPanel.setBackground(BACKGROUND_COLOR);
		eastPanel.add(playersPanel);
		add(eastPanel, BorderLayout.EAST);
		
		southPanel = new InfoPanel(this, FOREGROUND_COLOR, BACKGROUND_COLOR);
		add(southPanel, BorderLayout.SOUTH);
		
		// Esto se usa para que al cerrar la ventana se salven los resultados
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				saveTop10();
			}
		});
		
		
		setTitle("Mi Ventana");
		setSize(1200, 900);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// Carga los datos del top 10
	private void loadTop10()
	{
		top10.cargarRecords(dataFile);
	}
	
	// Salva los datos del top 10
	protected void saveTop10()
	{
		try
		{
			top10.salvarRecords(dataFile);
		} 
		catch (FileNotFoundException | UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		FlatLightLaf.install();
		new Window();
	}
}
