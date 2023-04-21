package semestre202310;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame
{
	private PanelTextos panelIzquierda;

	public Ventana()
	{
		setLayout(new BorderLayout());

		panelIzquierda = new PanelTextos();
		panelIzquierda.setBackground(Color.CYAN);
		panelIzquierda.setOpaque(true);
		add(panelIzquierda, BorderLayout.CENTER);
		
		// El panelDerecha no está directamente en la ventana.
		// Lo metemos dentro del panelVerde
		PanelBotones panelDerecha = new PanelBotones(this);
		panelDerecha.setBackground(Color.YELLOW);
		panelDerecha.setOpaque(true);
		
		// El panel verde usa GridBagLayout para centrar su contenido verticalmente
		JPanel panelVerde = new JPanel();
		panelVerde.setBackground(Color.GREEN);
		panelVerde.setOpaque(true);
		panelVerde.setLayout(new GridBagLayout());
		panelVerde.add(panelDerecha);
		
		add(panelVerde, BorderLayout.EAST);
		
		setTitle("Mi Ventana");
		setSize(700, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void agregarTexto(String nuevoTexto)
	{
		panelIzquierda.agregarTexto(nuevoTexto);
	}
	
	public static void main(String[] args)
	{
		new Ventana();
	}

	public void agregarClick(Click i)
	{
		panelIzquierda.agregarClick(i);
		
	}
}
