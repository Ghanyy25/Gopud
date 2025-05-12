package GoFood.Model;

import java.util.*;

public class Manager extends User {
    public String pass;

    public Manager(String username) {
        super(username);
        this.pass = "1234";
    }

    public void menuManager(List<Menu> menu) {
        Scanner scanner = new Scanner(System.in);
        int pilih;
        do {
            System.out.println("\n--- Menu Manager ---");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Edit Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Lihat Menu");
            System.out.println("0. Kembali");
            System.out.print("Pilih opsi: ");
            pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1:
                    System.out.print("Nama menu: ");
                    String nama = scanner.nextLine();
                    System.out.print("Harga: ");
                    double harga = scanner.nextDouble();
                    menu.add(new Menu(nama, harga));
                    System.out.println("Menu berhasil ditambahkan.");
                    break;

                case 2:
                    tampilkanMenu(menu);
                    System.out.print("Nomor menu yang ingin diedit: ");
                    int indexEdit = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (indexEdit >= 0 && indexEdit < menu.size()) {
                        System.out.print("Nama baru: ");
                        menu.get(indexEdit).setNama(scanner.nextLine());
                        System.out.print("Harga baru: ");
                        menu.get(indexEdit).setHarga(scanner.nextDouble());
                        System.out.println("Menu berhasil diubah.");
                    } else {
                        System.out.println("Nomor tidak valid.");
                    }
                    break;

                case 3:
                    tampilkanMenu(menu);
                    System.out.print("Nomor menu yang ingin dihapus: ");
                    int indexHapus = scanner.nextInt() - 1;
                    if (indexHapus >= 0 && indexHapus < menu.size()) {
                        menu.remove(indexHapus);
                        System.out.println("Menu berhasil dihapus.");
                    } else {
                        System.out.println("Nomor tidak valid.");
                    }
                    break;

                case 4:
                    tampilkanMenu(menu);
                    break;
            }
        } while (pilih != 0);
    }

    private void tampilkanMenu(List<Menu> menu) {
        System.out.println("\n--- Daftar Menu ---");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
    }
}
