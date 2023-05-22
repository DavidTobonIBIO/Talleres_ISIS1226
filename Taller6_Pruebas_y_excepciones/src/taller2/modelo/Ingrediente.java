package taller2.modelo;

public class Ingrediente implements Producto
{
	private String nombre;
	private int costoAdicional;

	public Ingrediente(String nombre, int costoAdicional)
	{
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
	}

	public String getNombre()
	{
		return nombre;
	}

	public int getPrecio()
	{
		return costoAdicional;
	}

	@Override
	public String generarTextoFactura()
	{
		return null;
	}

}
