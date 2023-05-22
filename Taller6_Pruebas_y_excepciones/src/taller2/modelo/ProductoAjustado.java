package taller2.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto
{
	private ProductoMenu base;
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;

	public ProductoAjustado(ProductoMenu base)
	{
		this.base = base;
		this.agregados = new ArrayList<Ingrediente>();
		this.eliminados = new ArrayList<Ingrediente>();
	}

	public void adicionarIngrediente(Ingrediente ingrediente)
	{
		agregados.add(ingrediente);
	}

	public void eliminarIngrediente(Ingrediente ingrediente)
	{
		eliminados.add(ingrediente);
	}

	@Override
	public int getPrecio()
	{
		int precio = 0;

		if (base != null)
			precio += base.getPrecio();

		for (Ingrediente ingrediente : agregados)
			precio += ingrediente.getCostoAdicional();

		return precio;
	}

	@Override
	public String getNombre()
	{
		// TODO revisar
		String nombre = "";

		if (base != null)
			nombre += base.getNombre();

		if (!eliminados.isEmpty())
		{
			nombre += " sin";
			for (Ingrediente ingrediente : eliminados)
				nombre += " " + ingrediente.getNombre() + ",";
		}
		nombre = nombre.substring(0, nombre.length() - 1) + ".";

		if (!agregados.isEmpty())
		{
			nombre += " Con adición de";
			for (Ingrediente ingrediente : agregados)
				nombre += " " + ingrediente.getNombre() + ",";
		}
		nombre = nombre.substring(0, nombre.length() - 1) + ".";

		return nombre;
	}
	
	public ArrayList<Ingrediente> getAgregados()
	{
		return this.agregados;
	}

	public ArrayList<Ingrediente> getEliminados()
	{
		return this.eliminados;
	}
	@Override
	public String generarTextoFactura()
	{
		// TODO revisar
		String textoFactura = "";

		if (base != null)
			textoFactura += base.generarTextoFactura();

		if (!agregados.isEmpty())
		{
			textoFactura += "\nAdiciones:";
			for (Ingrediente ingrediente : agregados)
				textoFactura += "\n\t" + ingrediente.getNombre() + " ---> " + ingrediente.getCostoAdicional();
		}

		if (!eliminados.isEmpty())
		{
			textoFactura += "\nEliminaciones:";
			for (Ingrediente ingrediente : eliminados)
				textoFactura += "\n\t" + ingrediente.getNombre() + " ---> 0";
		}

		return textoFactura;
	}

}
