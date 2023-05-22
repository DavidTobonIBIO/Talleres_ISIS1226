package taller2.modificacion;

public class IngredienteModificacion
{
	private String nombre;
	private int costoAdicional;
	private int calorias;

	public IngredienteModificacion(String nombre, int costoAdicional, int calorias)
	{
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
		this.calorias = calorias;
	}

	public String getNombre()
	{
		return nombre;
	}

	public int getCostoAdicional()
	{
		return costoAdicional;
	}
	
	public int getCalorias()
	{
		return calorias;
	}

}
