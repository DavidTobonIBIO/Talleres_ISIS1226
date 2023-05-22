package taller4.excepciones;

public abstract class HamburguesaException extends Exception
{
    final static String ANSI_RESET = "\u001B[0m";
    final static String ANSI_YELLOW = "\u001B[33m";
    
	public HamburguesaException(String mensajeError)
	{
		super(ANSI_YELLOW + mensajeError + ANSI_RESET);
	}
}
