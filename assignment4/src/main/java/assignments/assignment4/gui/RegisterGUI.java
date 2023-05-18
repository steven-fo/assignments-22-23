package assignments.assignment4.gui;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
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
        GridBagConstraints gbc = new GridBagConstraints();
        nameLabel = new JLabel("Masukkan nama Anda:");
        nameTextField = new JTextField();
        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        phoneTextField = new JTextField();
        passwordLabel = new JLabel("Masukkan password Anda:");
        passwordField = new JPasswordField();
        registerButton = new JButton("Register", null);
        backButton = new JButton("Kembali", null);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainPanel.add(nameLabel, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(nameTextField, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 2;
        mainPanel.add(phoneLabel, gbc);

        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(phoneTextField, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 4;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(passwordField, gbc);

        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.CENTER;
        mainPanel.add(registerButton, gbc);

        gbc.gridy = 7;
        mainPanel.add(backButton, gbc);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // TODO
        String inputNama = nameTextField.getText();
        String inputNoHp = phoneTextField.getText();
        String inputPassword = new String(passwordField.getPassword());
        if (inputNama.equals("") || inputNoHp.equals("") || inputPassword.equals("")) {
            JOptionPane.showMessageDialog(mainPanel, "Semua field diatas wajib diisi!", "Empty Field", JOptionPane.ERROR_MESSAGE);
        } else if (!NotaGenerator.isNumeric(inputNoHp)) {
            JOptionPane.showMessageDialog(mainPanel, "Nomor handphone harus berisi angka!", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
        } else {
            Member member = loginManager.register(inputNama, inputNoHp, inputPassword);
            if (member == null) {
                JOptionPane.showMessageDialog(mainPanel, "User dengan nama "+inputNama+" dan nomor hp "+inputNoHp+" sudah ada!", "Registration Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Berhasil membuat user dengan ID "+member.getId()+"!", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
            }
            MainFrame.getInstance().navigateTo(HomeGUI.KEY);
            nameTextField.setText("");
            phoneTextField.setText("");
            passwordField.setText("");
        }
    }


}
