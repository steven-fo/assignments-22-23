package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        // Create components
        GridBagConstraints gbc = new GridBagConstraints();
        idLabel = new JLabel("Masukkan ID Anda:");
        idTextField = new JTextField();
        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login", null);
        backButton = new JButton("Kembali", null);

        // Add listener to buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });

        // Set layout for components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainPanel.add(idLabel, gbc);
        
        gbc.gridy = 1;
        mainPanel.add(idTextField, gbc);

        gbc.gridy = 2;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridy = 3;
        mainPanel.add(passwordField, gbc);

        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 4;
        gbc.weightx = 0;
        mainPanel.add(loginButton, gbc);

        gbc.gridy = 5;
        mainPanel.add(backButton, gbc);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        idTextField.setText("");
        passwordField.setText("");
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        // TODO
        String inputId = idTextField.getText();
        String inputPassword = new String(passwordField.getPassword());
        boolean statusLogin = MainFrame.getInstance().login(inputId, inputPassword);
        if (!statusLogin) {
            JOptionPane.showMessageDialog(mainPanel, "ID atau password invalid!", "Invalid ID or Password", JOptionPane.ERROR_MESSAGE);
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        }
        idTextField.setText("");
        passwordField.setText("");
    }
}
