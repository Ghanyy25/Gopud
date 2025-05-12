package program.GoFood;

import program.GoFood.Model.Menu;
import program.GoFood.Model.Order;
import program.GoFood.Model.Customer;
import program.GoFood.Model.Manager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainGui {
    private static List<Menu> daftarMenu = new ArrayList<>();
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static JFrame frame;

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

        btnCustomer.addActionListener(e -> cardLayout.show(mainPanel, "customer"));
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

        JTextArea menuArea = new JTextArea();
        menuArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(menuArea);
        updateMenuText(menuArea);

        JTextField inputId = new JTextField();
        JButton btnPesan = new JButton("Pesan");
        JButton btnSelesai = new JButton("Selesai");
        JButton btnBack = new JButton("Kembali");

        List<Menu> cart = new ArrayList<>();

        btnPesan.addActionListener(e -> {
            try {
                int id = Integer.parseInt(inputId.getText());
                boolean found = false;
                for (Menu item : daftarMenu) {
                    if (item.getId() == id) {
                        cart.add(item);
                        JOptionPane.showMessageDialog(frame, item.getNama() + " ditambahkan.");
                        found = true;
                        break;
                    }
                }
                if (!found) JOptionPane.showMessageDialog(frame, "ID tidak ditemukan.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan ID yang valid.");
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

        JTextArea menuArea = new JTextArea();
        menuArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(menuArea);
        updateMenuText(menuArea);

        JButton btnTambah = new JButton("Tambah Menu");
        JButton btnHapus = new JButton("Hapus Menu");
        JButton btnBack = new JButton("Kembali");

        btnTambah.addActionListener(e -> {
            String nama = JOptionPane.showInputDialog("Nama menu:");
            String hargaStr = JOptionPane.showInputDialog("Harga:");
            try {
                double harga = Double.parseDouble(hargaStr);
                int id = daftarMenu.size() + 1;
                daftarMenu.add(new Menu(nama, harga));
                updateMenuText(menuArea);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Harga tidak valid.");
            }
        });

        btnHapus.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("ID menu yang dihapus:");
            try {
                int id = Integer.parseInt(idStr);
                boolean removed = daftarMenu.removeIf(item -> item.getId() == id);
        
                if (removed) {
                    updateMenuText(menuArea);
                } else {
                    JOptionPane.showMessageDialog(frame, "ID tidak ditemukan.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "ID tidak valid.");
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
        for (Menu item : daftarMenu) {
            sb.append(item.toString()).append("\n");
        }
        area.setText(sb.toString());
    }
}
