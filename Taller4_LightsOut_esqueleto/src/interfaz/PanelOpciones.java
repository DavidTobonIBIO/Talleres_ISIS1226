package interfaz;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelOpciones extends JPanel
{
	private static final String SIZE = "Tamaño:";
	private static final String[] SIZES = {"5x5", "6x6", "7x7", "8x8", "9x9", "10x10"};
	private static final String DIFICULTAD = "Dificultad:";
	private static final String EASY_BUTTON = "Fácil";
	private static final String MEDIUM_BUTTON = "Medio";
	private static final String HARD_BUTTON = "Difícil";

	private MainWindow father;
	private JLabel sizeLabel;
	private JComboBox<String> chooseSizeBox;
	private JLabel difficultyLabel;
	private JRadioButton easyDiffButton;
	private JRadioButton mediumDiffButton;
	private JRadioButton hardDiffButton;
	
	public PanelOpciones(MainWindow father)
	{
		this.father = father;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		sizeLabel = new JLabel(SIZE);
		add(sizeLabel);
		
		chooseSizeBox = new JComboBox<>(SIZES);
		add(chooseSizeBox);
		
		difficultyLabel = new JLabel(DIFICULTAD);
		add(difficultyLabel);
		
		easyDiffButton = new JRadioButton(EASY_BUTTON);
		add(easyDiffButton);
		
		mediumDiffButton = new JRadioButton(MEDIUM_BUTTON);
		add(mediumDiffButton);
		
		hardDiffButton = new JRadioButton(HARD_BUTTON);
		add(hardDiffButton);
	}
	
}
