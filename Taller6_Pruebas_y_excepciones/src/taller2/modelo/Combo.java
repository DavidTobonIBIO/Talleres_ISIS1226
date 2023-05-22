package taller2.modelo;

import java.util.ArrayList;

public class Combo implements Producto
{
	private Double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> itemsCombo;

	public Combo(Double descuento, String nombreCombo)
	{
		this.descuento = descuento;
		this.nombreCombo = nombreCombo;
		this.itemsCombo = new ArrayList<ProductoMenu>();
	}
	
	public ArrayList<ProductoMenu> getItemsCombo()
	{
		return itemsCombo;
	}
	
	@Override
	public int getPrecio()
	{
		int precio = 0;
		
		for (ProductoMenu producto : itemsCombo)
			precio += producto.getPrecio();
		
		double precioConDescuento = (double) precio * descuento;
		int precioRedondeado = (int) Math.round(precioConDescuento);
		return precioRedondeado;
	}

	@Override
	public String getNombre()
	{
		return nombreCombo;
	}
	
	public void agregarItemACombo(Producto itemCombo)
	{
		itemsCombo.add((ProductoMenu) itemCombo);
	}
	
	@Override
	public String generarTextoFactura()
	{
		String textoFactura = this.getNombre() + " ---> " + this.getPrecio();
 		return textoFactura;
	}
}
