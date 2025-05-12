package GoFood.Model;


public class Menu {
    private static int nextId = 1;
    private int id;
    private String nama;
    private double harga;

    public Menu(String nama, double harga) {
        this.id = nextId++; 
        this.nama = nama;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNama() {
        return nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
    public double getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return nama + " - Rp" + harga;
    }
}


