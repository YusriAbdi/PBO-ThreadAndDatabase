public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("\n===== SISTEM PEMBELIAN OBAT =====\n");
        PesanObatTask p1 = new PesanObatTask("Abdi", "Ibuprofen", 1, "Menunggu");
        PesanObatTask p2 = new PesanObatTask("Wahyu", "Paracetamol", 2, "Menunggu");
        PesanObatTask p3 = new PesanObatTask("Izzat", "Vitamin C", 3, "Menunggu");

        MonitorPembelianTask monitor = new MonitorPembelianTask();

        p1.start();
        p2.start();
        p3.start();
        monitor.start();
    }
}
