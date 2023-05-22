package taller2.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import taller4.excepciones.PrecioPedidoException;

public class Pedido
{
	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> productosPedido;
	private ProductoAjustado productoActual;

	public Pedido(String nombreCliente, String direccionCliente)
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		numeroPedidos = getIdUltimoPedido() + 1;
		this.idPedido = numeroPedidos;
		this.productosPedido = new ArrayList<Producto>();
	}

	public int getIdPedido()
	{
		return idPedido;
	}
	
	public Producto getProductoActual()
	{
		return productoActual;
	}
	
	public void nuevoProductoAjustado(Producto nuevoItem)
	{
		productoActual = new ProductoAjustado((ProductoMenu) nuevoItem);
		try
		{
			revisarPrecioPedido(productoActual);
		} catch (PrecioPedidoException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void adicionarIngredienteAlProducto(Ingrediente ingrediente)
	{
		try
		{
			revisarPrecioPedido(ingrediente);
		} catch (PrecioPedidoException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void eliminarIngredienteDelProducto(Ingrediente ingrediente)
	{
		productoActual.eliminarIngrediente(ingrediente);
	}

	public void agregarProductoAjustado()
	{
		try
		{
			revisarPrecioPedido(productoActual);
		} catch (PrecioPedidoException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void agregarCombo(Combo nuevoCombo)
	{
		try
		{
			revisarPrecioPedido(nuevoCombo);
		} catch (PrecioPedidoException e)
		{
			System.out.println(e.getMessage());;
		}
	}

	public void revisarPrecioPedido(Producto producto) throws PrecioPedidoException {
	    if (producto instanceof Ingrediente) 
	    {
	        productoActual.adicionarIngrediente((Ingrediente) producto);
	        int precioActual = getPrecioTotalPedido();

	        if (precioActual > 150000) 
	        {
	            productoActual.eliminarIngredienteDeAgregados((Ingrediente) producto);
	            throw new PrecioPedidoException("El precio del pedido no puede exceder los 150000 pesos: precio actual es " + precioActual + " al añadir " + producto.getNombre());
	        }
	    } else 
	    {
	        productosPedido.add(producto);
	        int precioActual = getPrecioTotalPedido();
	        if (precioActual > 150000) 
	        {
	            productosPedido.remove(producto);
	            throw new PrecioPedidoException("El precio del pedido no puede exceder los 150000 pesos: precio actual es " + precioActual + " al añadir " + producto.getNombre());
	        }
	    }
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

	public int getPrecioNetoPedido()
	{
		int precioNeto = 0;

		if (!productosPedido.isEmpty())
		{
			for (Producto producto : productosPedido)
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

	public int getPrecioTotalPedido()
	{
		int precioTotal = this.getPrecioNetoPedido() + this.getPrecioIVAPedido();
		return precioTotal;
	}

	public String generarTextoFactura()
	{
		String textoFactura = "";

		textoFactura += "Cliente: " + this.nombreCliente + "\n";
		textoFactura += "Dirección cliente: " + this.direccionCliente + "\n";
		textoFactura += "Id del pedido: " + this.getIdPedido() + "\n";
		textoFactura += "Items:\n";

		int precioIVA = this.getPrecioIVAPedido();
		int precioNeto = this.getPrecioNetoPedido();
		int precioTotal = this.getPrecioTotalPedido();

		for (Producto producto : productosPedido)
		{
			textoFactura += producto.generarTextoFactura();
			textoFactura += "\n";
		}
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
