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
	private static final Font FONT = new Font("Arial", Font.PLAIN, 16);
	
	private Window father;
	
	private JLabel playsLabel;
	private JTextField playsField;
	private int plays;
	
	private JLabel playerLabel;
	private JTextField playerField;
	private String playerName;
	
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
		
		playsField = new JTextField(Integer.toString(plays));
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
	}
}
