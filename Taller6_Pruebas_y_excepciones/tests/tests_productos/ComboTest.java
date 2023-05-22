package tests_productos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import taller2.modelo.Combo;
import taller2.modelo.Producto;
import taller2.modelo.ProductoMenu;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComboTest 
{
    private static Combo combo;

    @Test
    @Order(1)
    public void shouldAddItemsToCombo() 
    {
        combo = new Combo(0.75, "combo corral");

        Producto p1 = new ProductoMenu("corral", 10000);
        Producto p2 = new ProductoMenu("papas", 7000);
        Producto p3 = new ProductoMenu("gaseosa", 3000);

        combo.agregarItemACombo(p1);
        combo.agregarItemACombo(p2);
        combo.agregarItemACombo(p3);

        assertEquals(3, combo.getItemsCombo().size());
    }

    @Test
    @Order(2)
    public void shouldGenerateFacturaWithPrice() 
    {
        assertTrue(combo.generarTextoFactura().equals("combo corral ---> 15000"));
    }
}