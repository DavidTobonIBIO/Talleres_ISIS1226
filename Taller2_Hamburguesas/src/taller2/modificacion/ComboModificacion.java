package taller2.modificacion;

import java.util.ArrayList;

public class ComboModificacion implements ProductoModificacion
{
	private Double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenuModificacion> itemsCombo;

	public ComboModificacion(Double descuento, String nombreCombo)
	{
		this.descuento = descuento;
		this.nombreCombo = nombreCombo;
		this.itemsCombo = new ArrayList<ProductoMenuModificacion>();
	}

	@Override
	public int getPrecio()
	{
		int precio = 0;

		for (ProductoMenuModificacion producto : itemsCombo)
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

	@Override
	public int getCalorias()
	{
		int calorias = 0;

		for (ProductoMenuModificacion producto : itemsCombo)
			calorias += producto.getCalorias();

		return calorias;
	}

	public void agregarItemACombo(ProductoModificacion ItemCombo)
	{
		itemsCombo.add((ProductoMenuModificacion) ItemCombo);
	}

	@Override
	public String generarTextoFactura()
	{
		String textoFactura = this.getNombre() + " ---> " + this.getPrecio();
		textoFactura += "\n\t Calorías ---> " + this.getCalorias(); 
		return textoFactura;
	}
}
