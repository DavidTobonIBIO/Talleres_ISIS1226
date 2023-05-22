package taller2.modificacion;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RestauranteModificacion
{

	private ArrayList<IngredienteModificacion> ingredientes;
	private ArrayList<ProductoMenuModificacion> menuBase;
	private ArrayList<ComboModificacion> combos;
	private ArrayList<PedidoModificacion> pedidos;
	private ArrayList<Bebida> bebidas;
	private PedidoModificacion pedidoEnCurso;

	public RestauranteModificacion()
	{
		this.pedidos = new ArrayList<PedidoModificacion>();
	}

	public void cargarInformacionRestaurante(String archivoIngredientes, String archivoMenu, String archivoCombos,
			String archivoBebidas)
	{

		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
		cargarBebidas(archivoBebidas);

	}

	private void cargarIngredientes(String filePath)
	{
		this.ingredientes = new ArrayList<IngredienteModificacion>();
		try
		{
			File archivo = new File(filePath);
			Scanner lector = new Scanner(archivo);
			while (lector.hasNextLine())
			{
				String linea = lector.nextLine();
				String[] datos = linea.split(";");
				IngredienteModificacion ingrediente = new IngredienteModificacion(datos[0], Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));
				ingredientes.add(ingrediente);
			}
			lector.close();
		} catch (FileNotFoundException e)
		{
			System.out.println("File " + filePath + "not found.");
			e.printStackTrace();
		}

	}

	private void cargarMenu(String filePath)
	{
		this.menuBase = new ArrayList<ProductoMenuModificacion>();
		try
		{
			File archivo = new File(filePath);
			Scanner lector = new Scanner(archivo);
			while (lector.hasNextLine())
			{
				String linea = lector.nextLine();
				String[] datos = linea.split(";");
				ProductoMenuModificacion producto = new ProductoMenuModificacion(datos[0], Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));
				menuBase.add(producto);
			}
			lector.close();
		} catch (FileNotFoundException e)
		{
			System.out.println("File " + filePath + "not found.");
			e.printStackTrace();
		}

	}

	private void cargarCombos(String filePath)
	{
		this.combos = new ArrayList<ComboModificacion>();
		try
		{
			File archivo = new File(filePath);
			Scanner lector = new Scanner(archivo);
			while (lector.hasNextLine())
			{
				String linea = lector.nextLine();
				String[] datos = linea.split(";");
				Double descuento = 1.0 - (Double.parseDouble(datos[1].replace("%", "")) / 100);
				ComboModificacion combo = new ComboModificacion(descuento, datos[0]);

				String nombreProducto1 = datos[2];
				String nombreProducto2 = datos[3];
				String nombreProducto3 = datos[4];
				int encontrados = 0;
				for (ProductoModificacion elementoMenu : menuBase)
				{
					if ((nombreProducto1.equals(elementoMenu.getNombre()))
							|| (nombreProducto2.equals(elementoMenu.getNombre()))
							|| (nombreProducto3.equals(elementoMenu.getNombre())))
					{
						encontrados++;
						combo.agregarItemACombo(elementoMenu);
					}

					if (encontrados == 3)
						break;
				}
				combos.add(combo);
			}
			lector.close();
		} catch (FileNotFoundException e)
		{
			System.out.println("File " + filePath + "not found.");
			e.printStackTrace();
		}
	}

	public void cargarBebidas(String filePath)
	{
		this.bebidas = new ArrayList<Bebida>();
		try
		{
			File archivo = new File(filePath);
			Scanner lector = new Scanner(archivo);
			while (lector.hasNextLine())
			{
				String linea = lector.nextLine();
				String[] datos = linea.split(";");
				Bebida producto = new Bebida(datos[0], Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));
				bebidas.add(producto);
			}
			lector.close();
		} catch (FileNotFoundException e)
		{
			System.out.println("File " + filePath + "not found.");
			e.printStackTrace();
		}

	}

	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		this.pedidoEnCurso = new PedidoModificacion(nombreCliente, direccionCliente);
	}

	public void cerrarYGuardarPedido()
	{
		pedidoEnCurso.guardarFactura();
		if (existePedidoIdentico())
			System.out.println("MODIFICACION: Un pedido idéntico ya se ha realizado...");
		else
			pedidos.add(pedidoEnCurso);
		System.out.println("ID del pedido: " + pedidoEnCurso.getIdPedido());
		System.out.println("Pedido realizado.\n");
	}

	private boolean existePedidoIdentico()
	{
		boolean existeIdentico = false;

		ArrayList<ProductoModificacion> itemsPedidoEnCurso = pedidoEnCurso.getItems();

		for (PedidoModificacion pedidoEnRevision : pedidos)
		{
			ArrayList<ProductoModificacion> itemsPedidoEnRevision = pedidoEnRevision.getItems();
			if (itemsPedidoEnCurso.size() == itemsPedidoEnRevision.size())
			{
				int coincidencias = 0;

				for (ProductoModificacion itemPedidoEnCurso : itemsPedidoEnCurso)
				{
					for (ProductoModificacion itemPedidoEnRevision : itemsPedidoEnRevision)
					{
						if ((itemPedidoEnCurso instanceof ProductoAjustadoModificacion)
								&& (itemPedidoEnRevision instanceof ProductoAjustadoModificacion))
						{
							if (compararProductosAjustados((ProductoAjustadoModificacion)itemPedidoEnCurso, (ProductoAjustadoModificacion) itemPedidoEnRevision))
							{
								coincidencias++;
							}
						} else if (itemPedidoEnCurso.equals(itemPedidoEnRevision))
						{
							coincidencias++;
						}
					}
				}
				if (coincidencias == itemsPedidoEnCurso.size())
				{
					existeIdentico = true;
					break;
				}
			}
		}

		return existeIdentico;
	}

	private boolean compararProductosAjustados(ProductoAjustadoModificacion itemPedidoEnCurso,
			ProductoAjustadoModificacion itemPedidoEnRevision)
	{
		boolean iguales = false;

		ArrayList<IngredienteModificacion> agregadosEnCurso = itemPedidoEnCurso.getAgregados();
		ArrayList<IngredienteModificacion> agregadosEnRevision = itemPedidoEnRevision.getAgregados();
		ArrayList<IngredienteModificacion> eliminadosEnCurso = itemPedidoEnCurso.getEliminados();
		ArrayList<IngredienteModificacion> eliminadosEnRevision = itemPedidoEnRevision.getEliminados();
		String nombreEnCurso = itemPedidoEnCurso.getNombre();
		String nombreEnRevision = itemPedidoEnRevision.getNombre();

		if ((agregadosEnCurso.size() == agregadosEnRevision.size())
				&& (eliminadosEnCurso.size() == eliminadosEnRevision.size())
				&& (nombreEnCurso.equals(nombreEnRevision)))
		{
			int coincidenciasAgregados = 0;
			int coincidenciasEliminados = 0;

			for (IngredienteModificacion ingredienteEnCurso1 : agregadosEnCurso)
			{
				for (IngredienteModificacion ingredienteEnRevision1 : agregadosEnRevision)
				{
					if (ingredienteEnCurso1.equals(ingredienteEnRevision1))
						coincidenciasAgregados++;
				}
				if (coincidenciasAgregados == agregadosEnCurso.size())
				{
					for (IngredienteModificacion ingredienteEnCurso2 : eliminadosEnCurso)
					{
						for (IngredienteModificacion ingredienteEnRevision2 : eliminadosEnRevision)
						{
							if (ingredienteEnCurso2.equals(ingredienteEnRevision2))
								coincidenciasEliminados++;
						}
					}
				}
			}

			if ((coincidenciasAgregados == agregadosEnCurso.size())
					&& (coincidenciasEliminados == eliminadosEnCurso.size()))
				iguales = true;
		}
		return iguales;
	}

	public void consultarInfoPedido(String idPedido)
	{
		File directorio = new File("facturas");
		String[] hijos = directorio.list();

		if (hijos == null)
		{
			System.out.println("No se han realizado pedidos");
		} else
		{
			for (int i = 0; i < hijos.length; i++)
			{
				String nombreArchivo = hijos[i];
				if (nombreArchivo.equals(idPedido + ".txt"))
				{
					try
					{
						File archivo = new File("facturas/" + nombreArchivo);
						Scanner lector = new Scanner(archivo);
						while (lector.hasNextLine())
						{
							String linea = lector.nextLine();
							System.out.println(linea);
						}
						lector.close();
					} catch (FileNotFoundException e)
					{
						System.out.println("File " + " facturas/" + nombreArchivo + "not found.");
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void agregarProductoBase(String nombreProductoAgregar)
	{
		boolean encontrado = false;
		for (ProductoModificacion base : this.menuBase)
		{
			String nombreBase = base.getNombre();
			if (nombreBase.equals(nombreProductoAgregar))
			{
				encontrado = true;
				pedidoEnCurso.nuevoProductoAjustado(base);
				break;
			}
		}
		if (!encontrado)
			System.out.println("No se encontró el producto.");
	}

	public void adicionarIngredienteAlProducto(String nombreIngredienteAgregar)
	{
		boolean encontrado = false;
		for (IngredienteModificacion ingrediente : this.ingredientes)
		{
			String nombreIngrediente = ingrediente.getNombre();
			if (nombreIngrediente.equals(nombreIngredienteAgregar))
			{
				encontrado = true;
				pedidoEnCurso.adicionarIngredienteAlProducto(ingrediente);
				break;
			}
		}
		if (!encontrado)
			System.out.println("No se encontró el ingrediente.");
	}

	public void eliminarIngredienteDelProducto(String nombreIngredienteEliminar)
	{
		boolean encontrado = false;
		for (IngredienteModificacion ingrediente : this.ingredientes)
		{
			String nombreIngrediente = ingrediente.getNombre();
			if (nombreIngrediente.equals(nombreIngredienteEliminar))
			{
				encontrado = true;
				pedidoEnCurso.eliminarIngredienteDelProducto(ingrediente);
				break;
			}
		}
		if (!encontrado)
			System.out.println("No se encontró el ingrediente.");
	}

	public void confirmarProducto()
	{
		pedidoEnCurso.agregarProductoAjustado();
	}

	public void agregarComboAlPedido(String nombreComboAgregar)
	{
		boolean encontrado = false;
		for (ComboModificacion combo : this.combos)
		{
			String nombreCombo = combo.getNombre();
			if (nombreCombo.equals(nombreComboAgregar))
			{
				encontrado = true;
				pedidoEnCurso.agregarCombo(combo);
				break;
			}
		}
		if (!encontrado)
			System.out.println("No se encontró el combo.");
	}

	public void agregarBebidaAlPedido(String nombreBebidaAgregar)
	{
		boolean encontrado = false;
		for (Bebida bebida : this.bebidas)
		{
			String nombreBebida = bebida.getNombre();
			if (nombreBebida.equals(nombreBebidaAgregar))
			{
				encontrado = true;
				pedidoEnCurso.agregarBebida(bebida);
				break;
			}
		}
		if (!encontrado)
			System.out.println("No se encontró la bebida.");
	}

	public ArrayList<IngredienteModificacion > getIngredientes()
	{
		return ingredientes;
	}

	public ArrayList<ProductoMenuModificacion> getMenuBase()
	{
		return menuBase;
	}

	public ArrayList<ComboModificacion> getCombos()
	{
		return combos;
	}

	public ArrayList<PedidoModificacion> getPedidos()
	{
		return pedidos;
	}

	public ArrayList<Bebida> getBebidas()
	{
		return bebidas;
	}

	public PedidoModificacion getPedidoEnCurso()
	{
		return pedidoEnCurso;
	}

	public void mostrarMenu()
	{
		System.out.println("\nMENÚ");
		for (ProductoMenuModificacion producto : this.getMenuBase())
			System.out.println(producto.getNombre() + " " + producto.getPrecio());
		System.out.println("\nBEBDIAS");
		for (Bebida bebida : this.getBebidas())
			System.out.println(bebida.getNombre() + " " + bebida.getPrecio());
		System.out.println("\nCOMBOS:");
		for (ComboModificacion combo : this.getCombos())
			System.out.println(combo.getNombre() + " " + combo.getPrecio());
		System.out.println("\nADICIONES:");
		for (IngredienteModificacion ingrediente : this.getIngredientes())
			System.out.println(ingrediente.getNombre() + " " + ingrediente.getCostoAdicional());
		try
		{
			Thread.sleep(500);
		} catch (InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
	}

}
