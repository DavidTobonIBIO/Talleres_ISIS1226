/**
 * 
 */
package taller2.consola;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import taller2.modelo.Restaurante;

/**
 * @author david
 *
 */
public class Aplicacion
{

	private Restaurante restaurante;

	public void ejecutarOpcion()
	{
		System.out.println("Pedidos Restaurante de Hamburguesas");
		System.out.println("Cargando información del restaurante ...");
		cargarInfo();
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenuAplicacion();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1 && restaurante != null)
					ejecutarMostrarMenuRestaurante();
				else if (opcion_seleccionada == 2 && restaurante != null)
					ejecutarIniciarPedido();
				else if (opcion_seleccionada == 3 && restaurante != null)
					ejecutarConsultarInfoPedido();
				else if (opcion_seleccionada == 4 && restaurante != null)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				} else
					System.out.println("\nPor favor seleccione una opción válida.");

			} catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

	private void ejecutarConsultarInfoPedido()
	{
		String idPedidoConsultar = input("Ingrese el id del pedido que desea consultar: ");
		restaurante.consultarInfoPedido(idPedidoConsultar);
	}

	public void mostrarMenuAplicacion()
	{
		System.out.println("\nOpciones de la aplicacion\n");
		System.out.println("1. Ver Menu");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Consultar la informacion de un pedido dado su id");
		System.out.println("4. Salir de la aplicación\n");
	}

	private void ejecutarMostrarMenuRestaurante()
	{
		restaurante.mostrarMenu();
	}

	private void ejecutarIniciarPedido()
	{
		String nombreCliente = input("Ingresar nombre del cliente: ");
		String direccionCliente = input("Ingresar dirección del cliente: ");
		restaurante.iniciarPedido(nombreCliente, direccionCliente);
		boolean continuar = true;
		while (continuar)
		{
			System.out.println("Opciones del pedido:");
			System.out.println("1. Ver Menu");
			System.out.println("2. Añadir producto al pedido");
			System.out.println("3. Añadir combo al pedido");
			System.out.println("4. Finalizar pedido");

			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
			if (opcion_seleccionada == 1)
				ejecutarMostrarMenuRestaurante();
			else if (opcion_seleccionada == 2)
				ejecutarAgregarProductoAlPedido();
			else if (opcion_seleccionada == 3)
				ejecutarAgregarComboAlPedido();
			else if (opcion_seleccionada == 4)
			{
				ejecutarCerrarYGuardarPedido();
				continuar = false;
			} else
				System.out.println("\nPor favor seleccione una opción válida.");
		}

	}

	private void ejecutarCerrarYGuardarPedido()
	{
		restaurante.cerrarYGuardarPedido();
	}
	
	private void ejecutarAgregarProductoAlPedido()
	{
		boolean continuar = true;
		ejecutarAgregarProductoBaseAlPedido();
		
		while (continuar)
		{
			System.out.println("Añadir y editar producto:");
			System.out.println("1. Adicionar ingrediente al producto");
			System.out.println("2. Eliminar ingrediente del producto");
			System.out.println("3. Confirmar producto");
			
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
			
			if (opcion_seleccionada == 1)
				ejecutarAdicionarIngredienteAlProducto();
			else if (opcion_seleccionada == 2)
				ejecutarEliminarIngredienteDelProducto();
			else if (opcion_seleccionada == 3)
			{
				ejecutarConfirmarProducto();
				continuar = false;
				System.out.println("Producto agregado al pedido");
			}
			else
				System.out.println("Por favor ingrese una opción válida.");
		}
	}
	
	private void ejecutarAgregarProductoBaseAlPedido()
	{
		String nombreProductoAgregar = input("Ingrese el nombre del producto que desea agregar al pedido: ");
		restaurante.agregarProductoBase(nombreProductoAgregar);
	}
	private void ejecutarAdicionarIngredienteAlProducto()
	{
		String nombreIngrediente = input("Ingresar nombre del ingrediente a adicionar: ");
		restaurante.adicionarIngredienteAlProducto(nombreIngrediente);
	}
	
	private void ejecutarEliminarIngredienteDelProducto()
	{
		String nombreIngrediente = input("Ingresar nombre del ingrediente a eliminar: ");
		restaurante.eliminarIngredienteDelProducto(nombreIngrediente);
	}

	private void ejecutarAgregarComboAlPedido()
	{
		String nombreCombo = input("Ingresar nombre del combo: ");
		restaurante.agregarComboAlPedido(nombreCombo);

	}
	
	private void ejecutarConfirmarProducto()
	{
		restaurante.confirmarProducto();
	}

	private void cargarInfo()
	{
		String archivoIngredientes = "data/ingredientes.txt";
		String archivoMenu = "data/menu.txt";
		String archivoCombos = "data/combos.txt";

		restaurante = new Restaurante();
		restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	}

	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args)
	{
		Aplicacion consola = new Aplicacion();
		consola.ejecutarOpcion();
	}

}
