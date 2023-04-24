package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InfoPanel extends JPanel
{
	private static final String PLAYS = "Jugadas:";
	private static final String PLAYER = "Jugador:";
	private static final String POINTS = "Puntaje actual:";
	private static final Font FONT = new Font("Arial", Font.PLAIN, 16);
	
	private Window father;
	
	private JLabel playsLabel;
	private JTextField playsField;
	private int plays;
	
	private JLabel playerLabel;
	private JTextField playerField;
	private String playerName;
	
	private JLabel pointsLabel;
	private JTextField pointsField;
	private int points;
	
	public InfoPanel(Window father, Color foregroundColor, Color backgroundColor)
	{
		this.father = father;
		playerName = "";
		
		setLayout(new GridLayout(1, 4));
		setBackground(backgroundColor);
		setForeground(foregroundColor);
		setOpaque(true);
		
		playsLabel = new JLabel(PLAYS);
		playsLabel.setForeground(getForeground());
		playsLabel.setFont(FONT);
		add(playsLabel);
		
		playsField = new JTextField("0");
		playsField.setEditable(false);
		playsField.setBackground(getBackground());
		playsField.setForeground(getForeground());
		playsField.setFont(FONT);
		add(playsField);
		
		
		playerLabel = new JLabel(PLAYER);
		playerLabel.setForeground(getForeground());
		playerLabel.setFont(FONT);
		add(playerLabel);
		
		playerField = new JTextField(playerName);
		playerField.setEditable(false);
		playerField.setForeground(getForeground());
		playerField.setBackground(getBackground());
		playerField.setFont(FONT);
		add(playerField);
		

		pointsLabel = new JLabel(POINTS);
		pointsLabel.setForeground(getForeground());
		pointsLabel.setFont(FONT);
		add(pointsLabel);

		pointsField = new JTextField(Integer.toString(father.getPoints()));
		pointsField.setEditable(false);
		pointsField.setForeground(getForeground());
		pointsField.setBackground(getBackground());
		pointsField.setFont(FONT);
		add(pointsField);
	}
	
	public String getPlayerName()
	{
		return playerName;
	}
	
	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
		playerField.setText(playerName);
	}
	
	public int getPlays()
	{
		return plays;
	}
	
	public void setPlays(int plays)
	{
		this.plays = plays;
		playsField.setText(Integer.toString(plays));
	}

	public int getPoints()
	{
		return points;
	}

	public void setPoints(int points)
	{
		this.points = points;
		pointsField.setText(Integer.toString(points));
	}
	
	
}
