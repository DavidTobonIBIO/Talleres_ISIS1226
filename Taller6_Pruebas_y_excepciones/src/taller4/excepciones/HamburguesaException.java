package taller4.excepciones;

public abstract class HamburguesaException extends Exception
{
	public HamburguesaException(String mensajeError)
	{
		super(mensajeError);
	}
}
