package taller2.modelo;

public class ProductoMenu implements Producto
{
	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String nombre, int precioBase)
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
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
	public String generarTextoFactura()
	{
		String textoFactura = this.getNombre() + " ---> " + this.getPrecio();
		return textoFactura;
	}

}
