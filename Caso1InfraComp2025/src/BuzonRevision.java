import java.util.LinkedList;

public class BuzonRevision {
    private int capacidadMax;
    private LinkedList<Producto> productos;

    public BuzonRevision(int capacidadMax) {
        this.capacidadMax = capacidadMax;
        this.productos = new LinkedList<>();
    }

    public synchronized void agregar(Producto producto,int id) throws InterruptedException{   
        if(productos.size() >= capacidadMax){
            System.out.println("Buzon lleno, productor " + id + " esperando ");
            wait();
        }
        System.out.println("Productor " + id + " agrego el producto " + producto.getId() + " al buzon de revision " );     
        productos.add(producto);        
    }

    public synchronized Producto tomar() {
        notify();
        return productos.poll();
    }

    public synchronized boolean estaVacio() {
        return productos.isEmpty();
    }    
}

