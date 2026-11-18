import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {
    private JTextField txtCustormerName;
    private JComboBox<String> cmbProduct;
    private JTextField txtPrice;
    private JButton btnProcess;

    public ProductApp() {
        setTitle("PRODUCT APP");
        setSize(420, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");

        processItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSale();
            }
        });
        clearItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        editMenu.add(processItem);
        editMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.35;
        mainPanel.add(new JLabel("CustormerName:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.65;
        txtCustormerName = new JTextField(15);
        mainPanel.add(new JLabel("Select Product"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.65;
        String[] mattrasses = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};

        cmbProduct = new JComboBox<>(mattrasses);
        mainPanel.add(cmbProduct, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.35;
        mainPanel.add(new JLabel("Enter Price"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.65;
        txtPrice = new JTextField(15);
        mainPanel.add(txtPrice);

        gbc.gridx = 1;
        gbc.gridy = 3;
        btnProcess = new JButton("PROCESS");
        btnProcess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSale();
            }
        });
        mainPanel.add(btnProcess, gbc);
        add(mainPanel);
    }

    private void processSale() {
        String customerName = txtCustormerName.getText().trim();
        String productName = (String) cmbProduct.getSelectedItem();
        String priceText = txtPrice.getText().trim();

        if (customerName.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Both Custormer Name and Price", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double price = Double.parseDouble(priceText);
            ProductCalculations calc = new ProductCalculations(price);

            double vat = calc.GetVat();
            double commission = calc.GetCommission();
            double total = calc GetTotal();

            String message = "Custormer Name :" + customerName + "\n" + "Product: " + productName + "\n" + "VAT : R" + vat + "\n" + "COMMISSION: R" + commission + "\n" + "TOTAL: R" + total;
            JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);

            saveToFile(customerName, productName, vat, commission, total);
        } catch ()
    }
}