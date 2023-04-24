package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import uniandes.dpoo.taller4.modelo.RegistroTop10;

public class Top10Dialog extends JDialog
{
	private static final Font FONT = new Font("Arial", Font.BOLD, 16);
	private static final Color GREEN = new Color(94,161,145);

	public Top10Dialog(Collection<RegistroTop10> top10Records, Color foregroundColor, Color backgroundColor)
	{
		getContentPane().setBackground(backgroundColor);
		getContentPane().setForeground(foregroundColor);
		
		createRecordsList(top10Records);
		
		setTitle("Registros del TOP 10");
		setSize(150, 300);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void createRecordsList(Collection<RegistroTop10> top10Records)
	{
		DefaultListModel<String> model = new DefaultListModel<String>();
				
		model.addElement(" #  Nombre pts ");
		
		String labelText;
		String name;
		String pts;
		int i = 1;
		for (RegistroTop10 registro : top10Records)
		{			
			if (i<=3)
				labelText = "*";
			else
				labelText = " ";
			
			labelText += Integer.toString(i);
						
			if (labelText.length() < 3)
				labelText += "    ";
			else
				labelText += "   ";
			
			name = registro.darNombre();
			pts = Integer.toString(registro.darPuntos());			
			labelText += name + "    " + pts;
			
			model.addElement(labelText);
			i++;
		}

		JList<String> top10RecordsList = new JList<String>(model);
		top10RecordsList.setBackground(getContentPane().getBackground());
		top10RecordsList.setForeground(GREEN);
		top10RecordsList.setFont(FONT);
		
		JScrollPane scroll = new JScrollPane(top10RecordsList);
		scroll.setBackground(getContentPane().getBackground());
		scroll.setForeground(GREEN);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scroll);
		
	}
}
