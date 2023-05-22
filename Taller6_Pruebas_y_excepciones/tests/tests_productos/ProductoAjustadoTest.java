package tests_productos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import taller2.modelo.Ingrediente;
import taller2.modelo.ProductoAjustado;
import taller2.modelo.ProductoMenu;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductoAjustadoTest
{
	private static ProductoAjustado productoAjustado;
	
	@Test
	@Order(1)
	public void createProductoMenu()
	{
		ProductoMenu productoMenu = new ProductoMenu("corral", 14000);
		productoAjustado = new ProductoAjustado(productoMenu);
	}
	
	@Test
	@Order(2)
	public void shouldAddAndRemoveIngredients()
	{
		Ingrediente ing1 = new Ingrediente("queso", 2000);
		Ingrediente ing2 = new Ingrediente("tomate", 3000);
		Ingrediente ing3 = new Ingrediente("cebolla", 2500);
		productoAjustado.adicionarIngrediente(ing1);
		productoAjustado.eliminarIngrediente(ing2);
		productoAjustado.adicionarIngrediente(ing3);
		productoAjustado.eliminarIngredienteDeAgregados(ing3);
		
		assertEquals(16000, productoAjustado.getPrecio());
		assertFalse(productoAjustado.generarTextoFactura().contains(ing3.getNombre()));
		assertEquals(1, productoAjustado.getEliminados().size());
		assertEquals(1, productoAjustado.getAgregados().size());
	}
	
	@Test
	@Order(3)
	public void checkGenerarFactura()
	{
		System.out.println(productoAjustado.getAgregados().size());
		System.out.println(productoAjustado.getEliminados().size());
		System.out.println(productoAjustado.generarTextoFactura());
		assertTrue(productoAjustado.generarTextoFactura().contains("Eliminaciones"));
		assertTrue(productoAjustado.generarTextoFactura().contains("Adiciones"));		
	}
}
