import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MonitorPembelianTask extends Thread {

    @Override
    public void run() {
        int i = 0;
        try (Connection conn = DBConnector.connect()) {
            if (conn == null) {
                System.out.println("Tidak bisa memantau, DB offline!");
                return;
            }

            for (i = 0; i < 3; i++) {
                System.out.println("\n[MONITOR] Daftar Transaksi Pembelian:");
                System.out.println("------------------------------------------------");

                String sql = "SELECT * FROM pembelian";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    if (i == 2) {
                        System.out.println("• " + rs.getString("pembeli") + " membeli " + rs.getString("obat") +
                        " x" + rs.getInt("jumlah") + " [" + "SELESAI" + "]");
                    } else {
                        System.out.println("• " + rs.getString("pembeli") + " membeli " + rs.getString("obat") +
                        " x" + rs.getInt("jumlah") + " [" + rs.getString("status") + "]");
                    }
                }
                System.out.println("------------------------------------------------");
                Thread.sleep(1500);
            }

            System.out.println("\n[MONITOR] Pemantauan selesai");
        } catch (Exception e) {
            System.out.println("Error memantau: " + e.getMessage());
        }
    }
}