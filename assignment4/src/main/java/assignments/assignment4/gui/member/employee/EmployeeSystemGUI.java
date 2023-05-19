package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // TODO
        return new JButton[]{
            new JButton("It\'s nyuci time", null),
            new JButton("Display List Nota", null),
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        // TODO
        if (NotaManager.notaList.length == 0) {
            JOptionPane.showMessageDialog(this, "Belum ada nota", "List nota", JOptionPane.ERROR_MESSAGE);
        } else {
            String output = "";
            for (int i = 0; i<NotaManager.notaList.length; i++) {
                output += "Nota "+i+" : ";
                output += NotaManager.notaList[i].getNotaStatus()+"\n";
            }
            JOptionPane.showMessageDialog(this, output, "List Nota", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        // TODO
        if (NotaManager.notaList.length == 0) {
            JOptionPane.showMessageDialog(this, "nothing to cuci here", "Nyuci Results", JOptionPane.ERROR_MESSAGE);
        } else {
            String output = "";
            for (int i = 0; i<NotaManager.notaList.length; i++) {
                output += "Nota "+i+": ";
                output += NotaManager.notaList[i].kerjakan()+"\n";
            }
            JOptionPane.showMessageDialog(this, "Stand back! "+loggedInMember.getNama()+" is beginning to nyuci", "Nyuci Time", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(this, output, "Nyuci Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
