package taller2.modificacion;

public class Bebida implements ProductoModificacion
{
	private String nombre;
	private int precio;
	private int calorias;

	public Bebida(String nombre, int precio, int calorias)
	{
		this.nombre = nombre;
		this.precio = precio;
		this.calorias = calorias;
	}

	@Override
	public int getPrecio()
	{
		return precio;
	}

	@Override
	public String getNombre()
	{
		return nombre;
	}

	@Override
	public int getCalorias()
	{
		return calorias;
	}

	@Override
	public String generarTextoFactura()
	{
		String textoFactura = this.getNombre() + " ---> " + this.getPrecio();
		textoFactura += "\n\t Calorías ---> " + this.getCalorias(); 
		return textoFactura;
	}

}
