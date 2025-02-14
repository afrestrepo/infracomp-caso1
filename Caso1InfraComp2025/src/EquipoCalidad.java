
public class EquipoCalidad extends Thread {

    private int id;
    private BuzonRevision buzonRevision;
    private BuzonReproceso buzonReproceso;
    private Deposito deposito;
    private int totalMeta;
    private int fallosPermitidos;
    private int fallosActuales;
    private boolean fin;

    public EquipoCalidad(int id, BuzonRevision buzonRevision, BuzonReproceso buzonReproceso, Deposito deposito, int totalMeta) {
        this.id = id;
        this.buzonRevision = buzonRevision;
        this.buzonReproceso = buzonReproceso;
        this.deposito = deposito;
        this.totalMeta = totalMeta;
        this.fallosPermitidos = (int) Math.floor(totalMeta * 0.1);
        this.fallosActuales = 0;
        this.fin = false;
    }

    public void run() {
        while (!fin) {
            while (buzonRevision.estaVacio() && deposito.getTotal() < totalMeta) {
                try {
                    System.out.println("Buzon vacio, Equipo de calidad " + id + " esperando.");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Producto producto = buzonRevision.tomar();
            synchronized (deposito) {

                if (deposito.getTotal() >= totalMeta) {
                    System.out.println("meta alcanzada, Equipo de calidad " + id + " rechazó el producto " + producto.getId());
                    producto.marcarComoFin();
                    buzonReproceso.agregar(producto);

                } else {
                    revisarProducto(producto);
                }
            }
            if (buzonRevision.estaVacio() && deposito.getTotal() >= totalMeta) {
                fin = true;
                break;
            }

        }
    }

    private void revisarProducto(Producto producto) {
        int numero = (int) (Math.random() * 100);
        if (numero % 7 == 0 && fallosActuales < fallosPermitidos) {
            fallosActuales++;
            System.out.println("Equipo de calidad " + id + " rechazó el producto " + producto.getId());

            buzonReproceso.agregar(producto);

        } else {
            System.out.println("Equipo de calidad " + id + " aprobó el producto " + producto.getId());

            deposito.agregar(producto);

        }
    }
}
