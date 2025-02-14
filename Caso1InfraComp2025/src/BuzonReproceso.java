import java.util.LinkedList;

public class BuzonReproceso {
    private LinkedList<Producto> productos;

    public BuzonReproceso() {
        this.productos = new LinkedList<>();
    }

    public synchronized void agregar(Producto producto) {
        productos.addFirst(producto);
    }

    public synchronized Producto tomar() {
        return productos.removeFirst();
    }

    public synchronized boolean estaVacio() {
        return productos.isEmpty();
    }
}
