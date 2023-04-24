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

import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

public class Window extends JFrame
{
	private static final String DATA_FILE_PATH = "data/top10.csv";
	private static final Color FOREGROUND_COLOR = Color.WHITE;
	private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
	private static final String ORIGINAL_DIFF = "Fácil";
	private static final int ORIGINAL_SIZE = 5;
	
	private File dataFile;
	private Top10 top10;
	private OptionsPanel northPanel;
	private BoardPanel centerPanel;
	private PlayersPanel playersPanel;
	private JPanel eastPanel;
	private InfoPanel southPanel;
	private Tablero board;
	
	public Window()
	{
		dataFile = new File(DATA_FILE_PATH);
		top10 = new Top10();
		loadTop10();
		
		setLayout(new BorderLayout());
		
		northPanel = new OptionsPanel(this, FOREGROUND_COLOR, BACKGROUND_COLOR);
		add(northPanel, BorderLayout.NORTH);

		board = new Tablero(northPanel.getBoardSize());
		centerPanel = new BoardPanel(this, BACKGROUND_COLOR, board);
		add(centerPanel, BorderLayout.CENTER);
		
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
		
		
		setTitle("Lights Out");
		setSize(1200, 900);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// Metodo para actualizar tablero e informacion cuando se hace una jugada
	public void play(int[] box)
	{
		//board.jugar(box[0], box[1]);
		board.play(box[0], box[1]);
		southPanel.setPlays(board.darJugadas());
		southPanel.setPoints(board.calcularPuntaje());
		
		boardLitUp();
	}
	
	private void boardLitUp()
	{
		if (board.tableroIluminado())
		{
			int points = board.calcularPuntaje();
			System.out.println(points);
			if(top10.esTop10(points))
			{
				System.out.println("Entra");
				top10.agregarRegistro(southPanel.getPlayerName(), points);
				saveTop10();
			}
		}
	}
	
	// nuevo tablero que conserva dificultad y tamaño
	public void newBoard()
	{
		resizeBoard(centerPanel.getBoardSize());
	}
	
	public void restartBoard()
	{
		centerPanel.setDifficulty(ORIGINAL_DIFF);
		resizeBoard(ORIGINAL_SIZE);
	}
	
	public void showTop10()
	{
		new Top10Dialog(top10.darRegistros(), FOREGROUND_COLOR, BACKGROUND_COLOR);
	}
	
	public void changePlayer()
	{
		new ChangePlayerDialog(this, FOREGROUND_COLOR, BACKGROUND_COLOR);
	}
	
	public void setPlayer(String playerName)
	{
		southPanel.setPlayerName(playerName);
	}
	
	public void resizeBoard(int newSize)
	{
		String difficulty = centerPanel.getDifficulty();
		remove(centerPanel);
		board = new Tablero(newSize);
		centerPanel = new BoardPanel(this, BACKGROUND_COLOR, board);
		centerPanel.setDifficulty(difficulty);
		add(centerPanel, BorderLayout.CENTER);
		southPanel.setPoints(getPoints());
		southPanel.setPlays(0);
		setVisible(false);
		setVisible(true);
	}
	
	public void setDifficulty(String diff)
	{
		centerPanel.setDifficulty(diff);
	}
	
	public int getPoints()
	{
		return board.calcularPuntaje();
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
