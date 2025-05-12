package GoFood;

import GoFood.Model.Menu;
import GoFood.Model.Order;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainGui {
    private static List<Menu> daftarMenu = new ArrayList<>();
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static JFrame frame;
    private static JTextArea customerMenuArea = new JTextArea();    
    private static JTextArea managerMenuArea = new JTextArea();    

    public static void main(String[] args) {
        // Menu awal
        daftarMenu.add(new Menu("Nasi Goreng", 15000));
        daftarMenu.add(new Menu("Es Teh", 5000));
        daftarMenu.add(new Menu("Ayam Bakar", 20000));

        frame = new JFrame("Sistem Pemesanan Makanan");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(menuUtamaPanel(), "menuUtama");
        mainPanel.add(customerPanel(), "customer");
        mainPanel.add(managerPanel(), "manager");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Panel menu utama
    private static JPanel menuUtamaPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JLabel label = new JLabel("Pilih Peran:", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnCustomer = new JButton("Customer");
        JButton btnManager = new JButton("Manager");

        btnCustomer.addActionListener(e -> {
            updateMenuText(customerMenuArea);
            cardLayout.show(mainPanel, "customer");
        });

        btnManager.addActionListener(e -> {
            JPanel loginPanel = new JPanel(new GridLayout(2, 2));
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();

            loginPanel.add(new JLabel("Username:"));
            loginPanel.add(usernameField);
            loginPanel.add(new JLabel("Password:"));
            loginPanel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(frame, loginPanel,
                    "Login Manager", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("1234")) {
                    updateMenuText(managerMenuArea);
                    cardLayout.show(mainPanel, "manager");
                } else {
                    JOptionPane.showMessageDialog(frame, "Login gagal. Username atau password salah.");
                }
            }
        });

        panel.add(label);
        panel.add(btnCustomer);
        panel.add(btnManager);
        return panel;
    }

    // Panel customer
    private static JPanel customerPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        customerMenuArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(customerMenuArea);

        JTextField inputId = new JTextField();
        JButton btnPesan = new JButton("Pesan");
        JButton btnSelesai = new JButton("Selesai");
        JButton btnBack = new JButton("Kembali");

        List<Menu> cart = new ArrayList<>();

        btnPesan.addActionListener(e -> {
            try {
                int nomor = Integer.parseInt(inputId.getText());
                int index = nomor - 1;
                if (index >= 0 && index < daftarMenu.size()) {
                    Menu item = daftarMenu.get(index);
                    cart.add(item);
                    JOptionPane.showMessageDialog(frame, item.getNama() + " ditambahkan.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Nomor menu tidak valid.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan nomor menu yang valid.");
            }
        });

        btnSelesai.addActionListener(e -> {
            String nama = JOptionPane.showInputDialog("Nama Anda:");
            if (nama != null && !nama.isEmpty()) {
                Order order = new Order(nama, cart);
                StringBuilder struk = new StringBuilder("=== Struk Pemesanan ===\n");
                struk.append("Customer: ").append(nama).append("\n");
                for (Menu item : cart) {
                    struk.append("- ").append(item.getNama()).append(" (Rp").append(item.getHarga()).append(")\n");
                }
                struk.append("Total: Rp").append(order.getTotalHarga()).append("\n");
                JOptionPane.showMessageDialog(frame, struk.toString());
                cart.clear();
            }
        });

        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "menuUtama"));

        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        inputPanel.add(new JLabel("Masukkan ID Menu:"));
        inputPanel.add(inputId);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnPesan);
        buttonPanel.add(btnSelesai);
        buttonPanel.add(btnBack);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    // Panel manager
    private static JPanel managerPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        managerMenuArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(managerMenuArea);

        JButton btnTambah = new JButton("Tambah Menu");
        JButton btnHapus = new JButton("Hapus Menu");
        JButton btnBack = new JButton("Kembali");

        btnTambah.addActionListener(e -> {
            String nama = JOptionPane.showInputDialog("Nama menu:");
            String hargaStr = JOptionPane.showInputDialog("Harga:");
            try {
                double harga = Double.parseDouble(hargaStr);
                daftarMenu.add(new Menu(nama, harga));
                updateMenuText(managerMenuArea);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Harga tidak valid.");
            }
        });

        btnHapus.addActionListener(e -> {
            String nomorStr = JOptionPane.showInputDialog("Nomor menu yang dihapus:");
            try {
                int nomor = Integer.parseInt(nomorStr);
                int index = nomor - 1;
                if (index >= 0 && index < daftarMenu.size()) {
                    daftarMenu.remove(index);
                    updateMenuText(managerMenuArea);
                } else {
                    JOptionPane.showMessageDialog(frame, "Nomor tidak ditemukan.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Nomor tidak valid.");
            }
        });

        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "menuUtama"));

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnTambah);
        btnPanel.add(btnHapus);
        btnPanel.add(btnBack);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    private static void updateMenuText(JTextArea area) {
        StringBuilder sb = new StringBuilder("--- Menu ---\n");
        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu item = daftarMenu.get(i);
            sb.append((i + 1)).append(". ").append(item).append("\n");
        }
        area.setText(sb.toString());
    }
}
