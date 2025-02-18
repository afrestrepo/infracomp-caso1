import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);

        System.out.print("Ingrese el numero de operarios: ");
        int operarios = consola.nextInt();       

        System.out.print("Ingrese la meta total de productos: ");
        int totalMeta = consola.nextInt();

        System.out.print("Ingrese el límite del buzon de revision: ");
        int limiteBuzonRevision = consola.nextInt();

        BuzonRevision buzonRevision = new BuzonRevision(limiteBuzonRevision);
        BuzonReproceso buzonReproceso = new BuzonReproceso();
        Deposito deposito = new Deposito();

        for (int i = 0; i < operarios; i++) {
            new Productor(i, buzonRevision, buzonReproceso).start();
        }

        for (int i = 0; i < operarios; i++) {
            new EquipoCalidad(i, buzonRevision, buzonReproceso, deposito, totalMeta).start();
        }
    }
}
