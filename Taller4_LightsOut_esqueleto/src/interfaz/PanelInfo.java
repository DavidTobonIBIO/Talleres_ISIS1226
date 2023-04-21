package interfaz;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelInfo extends JPanel
{
	private static final String JUGADAS = "Jugadas:";
	private static final String JUGADOR = "Jugador:";
	
	private MainWindow father;
	private int contadorJugadas;
	private String jugador;
	private JLabel jugadasLabel;
	private JLabel textoContador;
	private JLabel jugadorLabel;
	private JLabel textoJugador;
	
	public PanelInfo(MainWindow father)
	{
		this.father = father;
		
		setLayout(new GridLayout(1, 4));

		contadorJugadas = 0;
		jugador = "";
		
		jugadasLabel = new JLabel(JUGADAS);
		add(jugadasLabel);
		
		textoContador = new JLabel(Integer.toString(contadorJugadas));
		add(textoContador);
		
		jugadorLabel = new JLabel(JUGADOR);
		add(jugadorLabel);
		
		textoJugador = new JLabel(jugador);
		add(textoJugador);
	}
	
}
