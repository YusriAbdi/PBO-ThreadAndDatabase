import java.sql.Connection;
import java.sql.PreparedStatement;

public class PesanObatTask extends Thread {
    private String namaPembeli;
    private String namaObat;
    private int jumlah;
    private String status;

    public PesanObatTask(String pembeli, String obat, int jumlah, String status) {
        this.namaPembeli = pembeli;
        this.namaObat = obat;
        this.jumlah = jumlah;
        this.status = status;
    }

    @Override
    public void run() {
        try (Connection conn = DBConnector.connect()) {
            if (conn == null) {
                System.out.println("DB tidak tersedia!");
                return;
            }
            String query = "INSERT INTO pembelian (pembeli, obat, jumlah, status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, namaPembeli);
            stmt.setString(2, namaObat);
            stmt.setInt(3, jumlah);
            stmt.setString(4, status);
            stmt.executeUpdate();
            System.out.println("[THREAD INPUT] Data tersimpan: " + namaPembeli + " membeli " + namaObat);
            Thread.sleep(1200);
        } catch (Exception e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }
}