package tests_productos;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import taller2.modelo.ProductoMenu;

public class ProductoMenuTest
{
	@Test
	public void shouldCreateProductoMenu()
	{
		ProductoMenu productoMenu = new ProductoMenu("corral", 14000);
		assertTrue(productoMenu.generarTextoFactura().equals("corral ---> 14000"));
	}
}
