package GoFood.Model;

import java.util.*;

public class Order {
    private String namaCustomer;
    private List<Menu> items;
    private double totalHarga;

    public Order(String namaCustomer, List<Menu> items) {
        this.namaCustomer = namaCustomer;
        this.items = new ArrayList<>(items);
        hitungTotalHarga();
    }

public double getTotalHarga() {
    return totalHarga;
}

    public void hitungTotalHarga() {
        totalHarga = 0;
        for (Menu item : items) {
            totalHarga += item.getHarga();
        }
    }

    public void printStruk() {
        System.out.println("\n--- Struk Pemesanan ---");
        System.out.println("Customer: " + namaCustomer);
        for (Menu item : items) {
            System.out.println("- " + item.getNama() + " (Rp" + item.getHarga() + ")");
        }
        System.out.println("Total Harga: Rp" + totalHarga);
        System.out.println("------------------------");
    }
}
