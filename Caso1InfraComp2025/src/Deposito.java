
import java.util.LinkedList;

public class Deposito {

    private LinkedList<Producto> productos;
    private int total;

    public Deposito() {
        this.productos = new LinkedList<>();
        this.total = 0;
    }

    public synchronized void agregar(Producto producto) {
        productos.add(producto);
        total++;
        System.out.println("Producto " + producto.getId() + " en el deposito, " + total + " productos en total.---------------------------");
    }

    public synchronized int getTotal() {
        return total;
    }
}
