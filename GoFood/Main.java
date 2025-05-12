package program.GoFood;

import java.util.*;
import program.GoFood.Model.*;

public class Main {
    private static List<Menu> daftarMenu = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        daftarMenu.add(new Menu( "Nasi Goreng", 15000));
        daftarMenu.add(new Menu("Es Teh", 5000));
        daftarMenu.add(new Menu("Ayam Bakar", 20000));

        while (true) {
            System.out.println("\n=== Sistem Pemesanan Makanan ===");
            System.out.println("1. Login sebagai Customer");
            System.out.println("2. Login sebagai Manager");
            System.out.println("3. Keluar");
            System.out.print("Pilih peran: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama Anda: ");
                    String namaCustomer = scanner.nextLine();
                    Customer customer = new Customer(namaCustomer);
                    customer.lihatMenu(daftarMenu);
                    customer.pesan(daftarMenu);
                    break;
                case 2:
                    System.out.print("Masukkan nama Manager: ");
                    String namaManager = scanner.nextLine();
                    Manager manager = new Manager(namaManager);
                    manager.menuManager(daftarMenu);
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan sistem ini!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}