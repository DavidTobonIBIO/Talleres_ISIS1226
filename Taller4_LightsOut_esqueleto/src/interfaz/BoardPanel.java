package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Tablero;

public class BoardPanel extends JPanel implements MouseListener
{
	private static final Color ON_COLOR = new Color(245,220,50);
	private static final Color OFF_COLOR = Color.BLACK;
	private static final Color LINE_COLOR = new Color(0,181,137);
	private static final String EASY = "Fácil";
	private static final String MEDIUM = "Medio";
	private static final String HARD = "Difícil";
	
    private Window father;
    private Tablero board;
    private int boardSize;
    private String difficulty;

    public BoardPanel(Window father, Color backgroundColor, Tablero board)
    {
        this.father = father;
        this.board = board;
        this.difficulty = EASY;
        setBackground(backgroundColor);
        addMouseListener(this);
    }
    
    public int getBoardSize()
    {
    	return boardSize;
    }
    
    public String getDifficulty()
    {
    	return difficulty;
    }
    
    public void setDifficulty(String difficulty)
    {
    	this.difficulty = difficulty;
    	repaint();
    }
    
    @Override
	public void paint(Graphics g)
	{
    	super.paint(g);
    	
    	Graphics2D g2d = (Graphics2D) g;
    	boolean[][] boardMatrix = board.darTablero();
    	boardSize = boardMatrix.length;
    	
    	for (int i=0; i<boardSize; i++)
    	{
    		for (int j=0; j<boardSize; j++)
    		{
    			int x = (getWidth() / boardSize) * j;
    			int y = (getHeight() / boardSize) * i;
    			int width = getWidth() / boardSize;
    			int height = getHeight() / boardSize;
    			
    			// Si el valor es <true> la luz esta apagada
    			if (boardMatrix[i][j])
    				g2d.setColor(OFF_COLOR);
    			else
    				g2d.setColor(ON_COLOR);
    			
    			g2d.fillRect(x, y, width, height);
    			g2d.setColor(LINE_COLOR);
    			g2d.drawRect(x, y, width, height);
    		}
    	}
    	paintDifficulty(g2d);
	}

	private void paintDifficulty(Graphics2D g2d)
	{
		if (difficulty.equals(MEDIUM))
			paintMedium(g2d);
		else if (difficulty.equals(HARD))
			paintHard(g2d);
	}
	
	// Esconde el estado de la luz de la mitad de la matriz
	private void paintMedium(Graphics2D g2d)
	{
		int half = (int) boardSize / 2;
		
		int x = ((getWidth() / boardSize) * half);
		int y = ((getHeight() / boardSize) * half);
		int width = getWidth() / boardSize;
		int height = getHeight() / boardSize;
		g2d.setColor(LINE_COLOR);
		g2d.fillRect(x, y, width, height);
	}
	
	// esconde el estado de la luz de la mitad de dos extremos
	private void paintHard(Graphics2D g2d)
	{
		paintMedium(g2d);
		
		int x = 0;
		int y = 0;
		int width = getWidth() / boardSize;
		int height = getHeight() / boardSize;
		g2d.setColor(LINE_COLOR);
		g2d.fillRect(x, y, width, height);
		
		x = (getWidth() / boardSize) * (boardSize - 1);
		y = (getHeight() / boardSize) * (boardSize - 1);
		g2d.fillRect(x, y, width, height);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		int clickX = e.getX();
		int clickY = e.getY();
		
		int[] box = coordenatesToBox(clickX, clickY);
		System.out.println(box[0]+ ", " + box[1]);
		father.play(box);
		repaint();
	}

	private int[] coordenatesToBox(int x, int y)
	{
		int boxHeight = getHeight() / boardSize;
		int boxWidth = getWidth() / boardSize;
		int row = (int) (y / boxHeight);
		int col = (int) (x / boxWidth);
		return new int[] {row, col};
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
