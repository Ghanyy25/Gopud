package program.GoFood.Model;

import java.util.*;

public class Manager extends User {
    public Manager(String username) {
        super(username);
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
                    System.out.print("ID menu yang ingin diedit: ");
                    int idEdit = scanner.nextInt();
                    scanner.nextLine();
                    for (Menu item : menu) {
                        if (item.getId() == idEdit) {
                            System.out.print("Nama baru: ");
                            item.setNama(scanner.nextLine());
                            System.out.print("Harga baru: ");
                            item.setHarga(scanner.nextDouble());
                            System.out.println("Menu berhasil diubah.");
                        }
                    }
                    break;
                case 3:
                    System.out.print("ID menu yang ingin dihapus: ");
                    int idHapus = scanner.nextInt();
                    menu.removeIf(item -> item.getId() == idHapus);
                    System.out.println("Menu berhasil dihapus.");
                    break;
                case 4:
                    for (Menu item : menu) {
                        System.out.println(item);
                    }
                    break;
            }
        } while (pilih != 0);
        scanner.close();
    }
}
