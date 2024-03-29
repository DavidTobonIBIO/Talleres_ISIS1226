package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PlayersPanel extends JPanel implements ActionListener
{
	private static final String NEW = "NUEVO";
	private static final String RESTART = "REINICIAR";
	private static final String TOP_10 = "TOP 10";
	private static final String CHANGE_PLAYER = "CAMBIAR JUGADOR";
	private static final Color BUTTON_COLOR = new Color(54,121,105);
	private static final Font FONT = new Font("Arial", Font.PLAIN, 20);
	
	private Window father;
	private JButton newGameButton;
	private JButton restartButton;
	private JButton top10Button;
	private JButton changePlayerButton;

	public PlayersPanel(Window father, Color foregroundColor, Color backgroundColor)
	{
		this.father = father;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(backgroundColor);
		setForeground(foregroundColor);
		setOpaque(true);
		
		newGameButton = new JButton(NEW);
		newGameButton.addActionListener(this);
		newGameButton.setActionCommand(NEW);
		newGameButton.setForeground(getForeground());
		newGameButton.setBackground(BUTTON_COLOR);
		newGameButton.setFont(FONT);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(backgroundColor);
		panel1.add(newGameButton);
		add(panel1);
		
		restartButton = new JButton(RESTART);
		restartButton.addActionListener(this);
		restartButton.setActionCommand(RESTART);
		restartButton.setForeground(getForeground());
		restartButton.setBackground(BUTTON_COLOR);
		restartButton.setFont(FONT);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(backgroundColor);
		panel2.add(restartButton);
		add(panel2);
		
		top10Button = new JButton(TOP_10);
		top10Button.addActionListener(this);
		top10Button.setActionCommand(TOP_10);
		top10Button.setForeground(getForeground());
		top10Button.setBackground(BUTTON_COLOR);
		top10Button.setFont(FONT);
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(backgroundColor);
		panel3.add(top10Button);
		add(panel3);
		
		changePlayerButton = new JButton(CHANGE_PLAYER);
		changePlayerButton.addActionListener(this);
		changePlayerButton.setActionCommand(CHANGE_PLAYER);
		changePlayerButton.setForeground(getForeground());
		changePlayerButton.setBackground(BUTTON_COLOR);
		changePlayerButton.setFont(FONT);
		
		JPanel panel4 = new JPanel();
		panel4.setBackground(backgroundColor);
		panel4.add(changePlayerButton);
		add(panel4);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		if (command.equals(NEW))
			father.newBoard();
		else if (command.equals(RESTART))
			father.restartBoard();
		else if (command.equals(TOP_10))
			father.showTop10();
		else
			father.changePlayer();
		
	}
}
