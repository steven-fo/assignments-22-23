package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private String[] items = {"Express", "Fast", "Reguler"};
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;
    private JPanel mainPanel;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

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
        gbc.insets = new Insets(3, 3, 3, 3);
        paketLabel = new JLabel("Paket Laundry:");
        paketComboBox = new JComboBox<String>(items);
        showPaketButton = new JButton("Show Paket", null);
        beratLabel = new JLabel("Berat Cucian (Kg):");
        beratTextField = new JTextField(8);
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / kg)", null, false);
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4kg pertama, kemudian 500 / kg)", null, false);
        createNotaButton = new JButton("Buat Nota", null);
        backButton = new JButton("Kembali", null);

        showPaketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaket();
            }
        });

        createNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNota();
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
        mainPanel.add(paketLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(paketComboBox, gbc);

        gbc.gridx = 2;
        mainPanel.add(showPaketButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(beratLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(beratTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(setrikaCheckBox, gbc);
        
        gbc.gridy = 3;
        mainPanel.add(antarCheckBox, gbc);

        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        mainPanel.add(createNotaButton, gbc);

        gbc.gridy = 5;
        mainPanel.add(backButton, gbc);
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // TODO
        if (!isNumeric(beratTextField.getText()) || Integer.parseInt(beratTextField.getText()) <= 0) {
            JOptionPane.showMessageDialog(mainPanel, "Berat Cucian harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            beratTextField.setText("");
        } else {
            if (Integer.parseInt(beratTextField.getText()) < 2) {
                beratTextField.setText("2");
                JOptionPane.showMessageDialog(mainPanel, "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
            Nota nota = new Nota(memberSystemGUI.getLoggedInMember(), Integer.parseInt(beratTextField.getText()), paketComboBox.getSelectedItem().toString(), this.fmt.format(this.cal.getTime()));
            if (setrikaCheckBox.isSelected()) {
                SetrikaService setrikaService = new SetrikaService();
                nota.addService(setrikaService);
            }
            if (antarCheckBox.isSelected()) {
                AntarService antarService = new AntarService();
                nota.addService(antarService);
            }
            JOptionPane.showMessageDialog(mainPanel, "Nota berhasil dibuat!", "Success", JOptionPane.INFORMATION_MESSAGE);
            NotaManager.addNota(nota);
            beratTextField.setText("");
            paketComboBox.setSelectedItem("Express");
            setrikaCheckBox.setSelected(false);
            antarCheckBox.setSelected(false);
        }
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
        MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
