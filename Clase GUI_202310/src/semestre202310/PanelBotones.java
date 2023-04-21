package semestre202310;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class PanelBotones extends JPanel implements ActionListener
{
	private static final String TEXTO = "TEXTO";
	private static final String BOTON_1 = "BOTON 1";
	private static final String BOTON_2 = "BOTON 2";
	private static final String BOTON_3 = "BOTON 3";

	private Ventana padre;
	private JTextField txt;

	public PanelBotones(Ventana laVentana)
	{
		padre = laVentana;

		// Usamos BoxLayout para agregar los botones uno debajo del otro.
		// FlowLayout sólo nos deja ir ubicándolos lado a lado.
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JButton boton1 = new JButton(new ImageIcon("./imagenes/logo.png"));
		boton1.addActionListener(this);
		boton1.setActionCommand(BOTON_1);
		add(boton1);

		JButton boton2 = new JButton("2");
		boton2.addActionListener(this);
		boton2.setActionCommand(BOTON_2);
		add(boton2);

		JButton boton3 = new JButton("3");
		boton3.addActionListener(this);
		boton3.setActionCommand(BOTON_3);
		add(boton3);

		txt = new JTextField("10");
		txt.addActionListener(this);
		txt.setActionCommand(TEXTO);
		add(txt);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand();

		if (comando.equals(BOTON_1))
			padre.agregarClick(new Click(1));
		else if (comando.equals(BOTON_2))
		{
			JFileChooser fc = new JFileChooser(".");
			fc.setMultiSelectionEnabled(false);
			fc.setFileFilter(new FileFilter()
			{

				@Override
				public String getDescription()
				{
					return "Archivos que me interesan";
				}

				@Override
				public boolean accept(File f)
				{
					return f.isDirectory();
				}
			});

			int resultado = fc.showOpenDialog(this);
			if (resultado == JFileChooser.APPROVE_OPTION)
			{
				File archivo = fc.getSelectedFile();
				long espacio = archivo.getTotalSpace();
				padre.agregarClick(new Click((int) espacio));
			}

		}
		else if (comando.equals(BOTON_3))
		{
			String respuesta = JOptionPane.showInputDialog(this, "Deme un número de 1 a 10", "Número",
					JOptionPane.QUESTION_MESSAGE);

			try
			{
				int numero = Integer.parseInt(respuesta);
				padre.agregarClick(new Click(numero));
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "¡Eso no es un número!", "error!!!!", JOptionPane.ERROR_MESSAGE);
			}

		}

		else if (TEXTO.equals(comando))
		{
			padre.agregarTexto(txt.getText());
		}

	}
}
