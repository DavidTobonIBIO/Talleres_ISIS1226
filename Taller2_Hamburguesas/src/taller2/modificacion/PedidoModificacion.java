package taller2.modificacion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PedidoModificacion
{
	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<ProductoModificacion> productosPedido;
	private ProductoAjustadoModificacion productoActual;

	public PedidoModificacion(String nombreCliente, String direccionCliente)
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		numeroPedidos = getIdUltimoPedido() + 1;
		this.idPedido = numeroPedidos;
		this.productosPedido = new ArrayList<ProductoModificacion>();
	}

	public int getIdPedido()
	{
		return idPedido;
	}
	
	public ArrayList<ProductoModificacion> getItems()
	{
		return this.productosPedido;
	}

	public void nuevoProductoAjustado(ProductoModificacion nuevoItem)
	{
		productoActual = new ProductoAjustadoModificacion((ProductoMenuModificacion) nuevoItem);
	}

	public void adicionarIngredienteAlProducto(IngredienteModificacion ingrediente)
	{
		productoActual.adicionarIngrediente(ingrediente);
	}

	public void eliminarIngredienteDelProducto(IngredienteModificacion ingrediente)
	{
		productoActual.eliminarIngrediente(ingrediente);
	}

	public void agregarProductoAjustado()
	{
		productosPedido.add(productoActual);
	}

	public void agregarCombo(ComboModificacion nuevoCombo)
	{
		productosPedido.add(nuevoCombo);
	}
	
	public void agregarBebida(Bebida nuevaBebida)
	{
		productosPedido.add(nuevaBebida);
	}

	public void guardarFactura()
	{
		String textoFactura = this.generarTextoFactura();
		String nombreArchivo = "facturas/" + this.getIdPedido() + ".txt";
		File archivoFactura = new File(nombreArchivo);
		FileWriter escritor = null;
		try
		{
			escritor = new FileWriter(archivoFactura);
			escritor.write(textoFactura);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				escritor.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private int getPrecioNetoPedido()
	{
		int precioNeto = 0;

		if (!productosPedido.isEmpty())
		{
			for (ProductoModificacion producto : productosPedido)
			{
				precioNeto += producto.getPrecio();
			}
		}

		return precioNeto;

	}

	private int getPrecioIVAPedido()
	{
		int precioNeto = this.getPrecioNetoPedido();
		double precioIVA = (double) precioNeto * 0.19;
		int IVARedondeado = (int) Math.round(precioIVA);

		return IVARedondeado;
	}

	private int getPrecioTotalPedido()
	{
		int precioTotal = this.getPrecioNetoPedido() + this.getPrecioIVAPedido();
		return precioTotal;
	}
	
	private int getCaloriasTotales()
	{
		int calorias = 0;

		if (!productosPedido.isEmpty())
		{
			for (ProductoModificacion producto : productosPedido)
			{
				calorias += producto.getCalorias();
			}
		}

		return calorias;
	}

	private String generarTextoFactura()
	{
		String textoFactura = "";

		textoFactura += "Cliente: " + this.nombreCliente + "\n";
		textoFactura += "Dirección cliente: " + this.direccionCliente + "\n";
		textoFactura += "Id del pedido: " + this.getIdPedido() + "\n";
		textoFactura += "Items:\n";

		int precioIVA = this.getPrecioIVAPedido();
		int precioNeto = this.getPrecioNetoPedido();
		int precioTotal = this.getPrecioTotalPedido();
		int caloriasTotales = this.getCaloriasTotales();

		for (ProductoModificacion producto : productosPedido)
		{
			textoFactura += producto.generarTextoFactura();
			textoFactura += "\n";
		}
		textoFactura += "Calorías Totales ---> " + caloriasTotales + "\n";
		textoFactura += "Precio Neto ---> " + precioNeto + "\n";
		textoFactura += "IVA ---> " + precioIVA + "\n";
		textoFactura += "Precio Total Pedido ---> " + precioTotal;

		return textoFactura;

	}
	
	private int getIdUltimoPedido()
	{
		int idUltimoPedido = -1;
		File directorio = new File("facturas");
		String[] hijos = directorio.list();

		if (hijos != null)
		{
			// Los ids se enumeran empezando desde 0
			idUltimoPedido = hijos.length - 1;
		}
		return idUltimoPedido;
	}
}
