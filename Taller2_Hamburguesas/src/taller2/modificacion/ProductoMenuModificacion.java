package taller2.modificacion;

public class ProductoMenuModificacion implements ProductoModificacion
{
	private String nombre;
	private int precioBase;
	private int calorias;
	
	public ProductoMenuModificacion(String nombre, int precioBase, int calorias)
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias = calorias;
	}

	@Override
	public int getPrecio()
	{
		return precioBase;
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
