package interfaz;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelJugadores extends JPanel
{
	private static final String NUEVO = "NUEVO";
	private static final String REINICIAR = "REINICIAR";
	private static final String TOP_10 = "TOP 10";
	private static final String CAMBIAR_JUGADOR = "CAMBIAR JUGADOR";
	
	private MainWindow father;
	private JButton newButton;
	private JButton restartButton;
	private JButton top10Button;
	private JButton changePlayerButton;
	
	
	public PanelJugadores(MainWindow father)
	{
		this.father = father;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		newButton = new JButton(NUEVO);
		add(newButton);
		restartButton = new JButton(REINICIAR);
		add(restartButton);
		top10Button = new JButton(TOP_10);
		add(top10Button);
		changePlayerButton = new JButton(CAMBIAR_JUGADOR);
		add(changePlayerButton);

	}
}
