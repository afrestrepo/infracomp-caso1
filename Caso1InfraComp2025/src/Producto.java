public class Producto {
    private int id;
    private boolean esFin;    
    private static int contadorId = 0; 

    public Producto() {
        synchronized (Producto.class) { 
            this.id = contadorId;
            contadorId++;
        }
    }
    public int getId() {
        return id;
    }

    public boolean esFin() {
        return esFin;
    }

    public void marcarComoFin() {
        this.esFin = true;
    }
    public void setId(int id) {
        this.id = id;
    }
}

