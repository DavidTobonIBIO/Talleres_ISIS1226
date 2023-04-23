package interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class OptionsPanel extends JPanel
{
	private static final String SIZE = "Tamaño: ";
	private static final String[] BOARD_SIZES = {"3x3", "4x4", "5x5", "6x6", "7x7", "8x8", "9x9", "10x10"};
	private static final String DIFFICULTY = "    Dificultad: ";
	private static final String EASY = "Fácil";
	private static final String MEDIUM = "Medio";
	private static final String HARD = "Difícil";
	private static final Font FONT = new Font("Arial", Font.PLAIN, 16);
	
	private Window father;
	private JLabel sizeLabel;
	private JComboBox<String> sizesBox;
	private JLabel diffLabel;
	private JRadioButton easyButton;
	private JRadioButton midButton;
	private JRadioButton hardButton;
	private ButtonGroup buttonGroup;
	
	public OptionsPanel(Window father, Color foregroundColor, Color backgroundColor)
	{
		this.father = father;
		
		setLayout(new FlowLayout());
		setBackground(backgroundColor);
		setForeground(foregroundColor);
		setOpaque(true);
		
		sizeLabel = new JLabel(SIZE);
		sizeLabel.setForeground(getForeground());
		sizeLabel.setFont(FONT);
		add(sizeLabel);
		
		sizesBox = new JComboBox<String>(BOARD_SIZES);
		sizesBox.setForeground(getForeground());
		sizesBox.setBackground(getBackground());
		sizesBox.setFont(FONT);
		add(sizesBox);
		
		diffLabel = new JLabel(DIFFICULTY);
		diffLabel.setForeground(getForeground());
		diffLabel.setFont(FONT);
		add(diffLabel);
		
		easyButton = new JRadioButton(EASY);
		easyButton.setForeground(getForeground());
		easyButton.setBackground(getBackground());
		easyButton.setFont(FONT);
		add(easyButton);
		
		midButton = new JRadioButton(MEDIUM);
		midButton.setForeground(getForeground());
		midButton.setBackground(getBackground());
		midButton.setFont(FONT);
		add(midButton);
		
		hardButton = new JRadioButton(HARD);
		hardButton.setForeground(getForeground());
		hardButton.setBackground(getBackground());
		hardButton.setFont(FONT);
		add(hardButton);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(easyButton);
		buttonGroup.add(midButton);
		buttonGroup.add(hardButton);
		
	}
	
}
