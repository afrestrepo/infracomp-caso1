
public class Productor extends Thread {

    private int id;
    private BuzonRevision buzonRevision;
    private BuzonReproceso buzonReproceso;
    private boolean fin;

    public Productor(int id, BuzonRevision buzonRevision, BuzonReproceso buzonReproceso) {
        this.id = id;
        this.buzonRevision = buzonRevision;
        this.buzonReproceso = buzonReproceso;
        this.fin = false;
    }

    public void run() {
        while (!fin) {
            Producto producto = null;

            if (!buzonReproceso.estaVacio()) {
                producto = buzonReproceso.tomar();
            }

            if (producto != null) {
                if (producto.esFin()) {
                    System.out.println("Productor " + id + " recibió el mensaje FIN y termina su ejecución ");
                    fin = true;
                    break;
                } else {
                    reprocesarProducto(producto);
                }
            } else {
                producto = generarProducto();
            }

            try {
                buzonRevision.agregar(producto, id);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    private Producto generarProducto() {
        Producto producto = new Producto();        
        System.out.println("Productor " + id + " está creando el producto " + producto.getId());
        return producto;
    }

    private void reprocesarProducto(Producto producto) {
        System.out.println("Productor " + id + " está reprocesando el producto " + producto.getId());
    }
}
