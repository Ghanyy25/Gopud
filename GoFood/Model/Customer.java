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
        for (Menu item : menu) {
            System.out.println(item);
        }
    }

    public void pesan(List<Menu> menu) {
        Scanner scanner = new Scanner(System.in);
        int pilih;
        do {
            System.out.print("Masukkan ID menu yang ingin dipesan (0 untuk selesai): ");
            pilih = scanner.nextInt();
            for (Menu item : menu) {
                if (item.getId() == pilih) {
                    cart.add(item);
                    System.out.println(item.getNama() + " ditambahkan ke keranjang.");
                }
            }
        } while (pilih != 0);

        Order order = new Order(username, cart);
        order.printStruk();

    }
}
