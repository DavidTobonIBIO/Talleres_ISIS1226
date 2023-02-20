package taller2.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante
{

	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<ProductoMenu> menuBase;
	private ArrayList<Combo> combos;
	private ArrayList<Pedido> pedidos;
	private Pedido pedidoEnCurso;

	public Restaurante()
	{
		this.pedidos = new ArrayList<Pedido>();
	}
	public void cargarInformacionRestaurante(String archivoIngredientes, String archivoMenu, String archivoCombos)
	{

		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);

	}

	private void cargarIngredientes(String filePath)
	{
		this.ingredientes = new ArrayList<Ingrediente>();
		try
		{
			File archivo = new File(filePath);
			Scanner lector = new Scanner(archivo);
			while (lector.hasNextLine())
			{
				String linea = lector.nextLine();
				String[] datos = linea.split(";");
				Ingrediente ingrediente = new Ingrediente(datos[0], Integer.parseInt(datos[1]));
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
		this.menuBase = new ArrayList<ProductoMenu>();
		try
		{
			File archivo = new File(filePath);
			Scanner lector = new Scanner(archivo);
			while (lector.hasNextLine())
			{
				String linea = lector.nextLine();
				String[] datos = linea.split(";");
				ProductoMenu producto = new ProductoMenu(datos[0], Integer.parseInt(datos[1]));
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
		this.combos = new ArrayList<Combo>();
		try
		{
			File archivo = new File(filePath);
			Scanner lector = new Scanner(archivo);
			while (lector.hasNextLine())
			{
				String linea = lector.nextLine();
				String[] datos = linea.split(";");
				Double descuento = 1.0 - (Double.parseDouble(datos[1].replace("%", "")) / 100);
				Combo combo = new Combo(descuento, datos[0]);

				String nombreProducto1 = datos[2];
				String nombreProducto2 = datos[3];
				String nombreProducto3 = datos[4];
				int encontrados = 0;
				for (Producto elementoMenu : menuBase)
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

	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		this.pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
	}

	public void cerrarYGuardarPedido()
	{
		pedidoEnCurso.guardarFactura();
		pedidos.add(pedidoEnCurso);
		System.out.println("ID del pedido: " + pedidoEnCurso.getIdPedido());
		System.out.println("Pedido realizado.\n");
	}

	public void consultarInfoPedido(String idPedido)
	{
		File directorio = new File("facturas");
		String[] hijos = directorio.list();

		if (hijos == null)
		{
			System.out.println("No se han realizado pedidos");
		} 
		else
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
		for (Producto base : this.menuBase)
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
		for (Ingrediente ingrediente : this.ingredientes)
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
		for (Ingrediente ingrediente : this.ingredientes)
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
		for (Combo combo : this.combos)
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

	public ArrayList<Ingrediente> getIngredientes()
	{
		return ingredientes;
	}

	public ArrayList<ProductoMenu> getMenuBase()
	{
		return menuBase;
	}

	public ArrayList<Combo> getCombos()
	{
		return combos;
	}

	public Pedido getPedidoEnCurso()
	{
		return pedidoEnCurso;
	}

	public void mostrarMenu()
	{
		System.out.println("\nMENÚ");
		for (ProductoMenu producto : this.getMenuBase())
			System.out.println(producto.getNombre() + " " + producto.getPrecio());
		System.out.println("\nCOMBOS:");
		for (Combo combo : this.getCombos())
			System.out.println(combo.getNombre() + " " + combo.getPrecio());
		System.out.println("\nADICIONES:");
		for (Ingrediente ingrediente : this.getIngredientes())
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
