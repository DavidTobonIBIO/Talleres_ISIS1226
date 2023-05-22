package taller2.modificacion;

import java.util.ArrayList;

public class ProductoAjustadoModificacion implements ProductoModificacion
{
	private ProductoMenuModificacion base;
	private ArrayList<IngredienteModificacion> agregados;
	private ArrayList<IngredienteModificacion> eliminados;

	public ProductoAjustadoModificacion(ProductoMenuModificacion base)
	{
		this.base = base;
		this.agregados = new ArrayList<IngredienteModificacion>();
		this.eliminados = new ArrayList<IngredienteModificacion>();
	}

	public void adicionarIngrediente(IngredienteModificacion ingrediente)
	{
		agregados.add(ingrediente);
	}

	public void eliminarIngrediente(IngredienteModificacion ingrediente)
	{
		eliminados.add(ingrediente);
	}

	@Override
	public int getPrecio()
	{
		int precio = 0;

		if (base != null)
			precio += base.getPrecio();

		for (IngredienteModificacion ingrediente : agregados)
			precio += ingrediente.getCostoAdicional();

		return precio;
	}
	
	@Override 
	public int getCalorias()
	{
		int calorias = 0;
		if (base != null)
			calorias += base.getCalorias();

		for (IngredienteModificacion ingrediente : agregados)
			calorias += ingrediente.getCalorias();
		for (IngredienteModificacion ingrediente: eliminados)
			calorias -= ingrediente.getCalorias();

		return calorias;
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
			for (IngredienteModificacion ingrediente : eliminados)
				nombre += " " + ingrediente.getNombre() + ",";
		}
		nombre = nombre.substring(0, nombre.length() - 1) + ".";

		if (!agregados.isEmpty())
		{
			nombre += " Con adición de";
			for (IngredienteModificacion ingrediente : agregados)
				nombre += " " + ingrediente.getNombre() + ",";
		}
		nombre = nombre.substring(0, nombre.length() - 1) + ".";

		return nombre;
	}
	
	public ArrayList<IngredienteModificacion> getAgregados()
	{
		return this.agregados;
	}

	public ArrayList<IngredienteModificacion> getEliminados()
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
			for (IngredienteModificacion ingrediente : agregados)
			{
				textoFactura += "\n\t" + ingrediente.getNombre() + " ---> " + ingrediente.getCostoAdicional();
				textoFactura += "\n\t\t Calorías ---> " + ingrediente.getCalorias(); 
			}
		}

		if (!eliminados.isEmpty())
		{
			textoFactura += "\nEliminaciones:";
			for (IngredienteModificacion ingrediente : eliminados)
			{
				textoFactura += "\n\t" + ingrediente.getNombre() + " ---> 0";
				textoFactura += "\n\t\t Calorías eliminadas ---> " + ingrediente.getCalorias(); 
			}
		}

		return textoFactura;
	}

}
