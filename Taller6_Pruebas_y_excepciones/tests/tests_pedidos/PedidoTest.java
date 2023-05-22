package tests_pedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import taller2.modelo.Ingrediente;
import taller2.modelo.Pedido;
import taller2.modelo.ProductoMenu;
import taller4.excepciones.PrecioPedidoException;

public class PedidoTest
{
	@Test
	public void throwsPrecioPedidoExceptionNuevoIngrediente()
	{
		Pedido pedido = new Pedido("David", "Calle 17");
		pedido.nuevoProductoAjustado(new ProductoMenu("Producto menu", 70000));
		pedido.adicionarIngredienteAlProducto(new Ingrediente("Ingrediente 1", 30000));
		Ingrediente ing2 = new Ingrediente("Ingrediente 2", 100000);
		
		assertThrows(PrecioPedidoException.class, () -> {
			pedido.revisarPrecioPedido(ing2);
			});
		
		// assertEquals(140000, pedido.getPrecioNetoPedido());
		
	}
	
	@Test
	public void throwsPrecioPedidoExceptionNuevoProducto()
	{
		Pedido pedido = new Pedido("David", "Calle 17");
		pedido.nuevoProductoAjustado(new ProductoMenu("Producto menu 1", 70000));
		pedido.agregarProductoAjustado();
		pedido.nuevoProductoAjustado(new ProductoMenu("Producto menu 2", 60000));
		
		assertThrows(PrecioPedidoException.class, () -> {
			pedido.revisarPrecioPedido(pedido.getProductoActual());
		});
	
		assertEquals(70000, pedido.getPrecioNetoPedido());
	}
	
	@Test
	public void savesFactura()
	{
		Pedido pedido = new Pedido("David", "Calle 17");
		pedido.nuevoProductoAjustado(new ProductoMenu("Producto menu 1", 20000));
		pedido.agregarProductoAjustado();
		pedido.nuevoProductoAjustado(new ProductoMenu("Producto menu 2", 20000));
		pedido.agregarProductoAjustado();
		
		pedido.guardarFactura();
		int id = pedido.getIdPedido();
		File f = new File("facturas/"+id+".txt");
		assertTrue(f.exists() && f.isFile());
		String facturaLeida = "";
	   
		try {
	        Scanner myReader = new Scanner(f);
	        while (myReader.hasNextLine()) {
	          String data = myReader.nextLine();
	          facturaLeida += "\n" + data;
	        }
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
		
		String facturaGenerada = pedido.generarTextoFactura().strip();
		facturaLeida = facturaLeida.strip();
		assertTrue(facturaGenerada.equals(facturaLeida));
	}
	
}
