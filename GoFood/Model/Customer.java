package GoFood.Model;

import java.util.*;

public class Customer extends User {
    private List<Menu> cart;

    public Customer(String username) {
        super(username);
        cart = new ArrayList<>();
    }

    public void lihatMenu(List<Menu> menu) {
        System.out.println("\n--- Daftar Menu ---");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
    }

    public void pesan(List<Menu> menu) {
        Scanner scanner = new Scanner(System.in);
        int pilih;
        do {
            System.out.print("Masukkan nomor menu yang ingin dipesan (0 untuk selesai): ");
            pilih = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (pilih >= 0 && pilih < menu.size()) {
                        cart.add(menu.get(pilih));
                    } else {
                        System.out.println("Nomor tidak valid.");
                    }
        } while (pilih >= 0);

        Order order = new Order(username, cart);
        order.printStruk();

    }
}
