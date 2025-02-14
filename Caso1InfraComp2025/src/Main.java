public class Main {
    public static void main(String[] args) {
        int numProductores = 3;
        int numEquiposCalidad = 3;
        int totalMeta = 10;
        int limiteBuzonRevision = 5;

        BuzonRevision buzonRevision = new BuzonRevision(limiteBuzonRevision);
        BuzonReproceso buzonReproceso = new BuzonReproceso();
        Deposito deposito = new Deposito();

        for (int i = 0; i < numProductores; i++) {
            new Productor(i, buzonRevision, buzonReproceso).start();
        }

        for (int i = 0; i < numEquiposCalidad; i++) {
            new EquipoCalidad(i, buzonRevision, buzonReproceso, deposito, totalMeta).start();
        }
    }
}
