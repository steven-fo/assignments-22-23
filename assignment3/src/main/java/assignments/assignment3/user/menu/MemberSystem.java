package assignments.assignment3.user.menu;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import java.util.Scanner;

public class MemberSystem extends SystemCLI {
    Scanner input = new Scanner(System.in);

    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO
        switch (choice) {
            case 1 -> buatNota();
            case 2 -> lihatNota();
            case 3 -> logout = true;
            default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        // TODO
        Member[] tempList = new Member[memberList.length+1];
        for (int i=0; i<memberList.length; i++) {
            tempList[i] = memberList[i];
        }
        tempList[memberList.length] = member;
        memberList = tempList;
    }

    public void buatNota() {
        showPaket();
        String paket = input.next();
        int berat = cekBerat();
        boolean setrika = pilihSetrika();
        boolean antar = pilihAntar();
        Nota nota = new Nota(loginMember, berat, paket, NotaManager.fmt.format(NotaManager.cal.getTime()));             //tanggal masuk
        CuciService cuci = new CuciService();
        nota.addService(cuci);
        if (setrika) {
            SetrikaService setrikaService = new SetrikaService();
            nota.addService(setrikaService);
        }
        if (antar) {
            AntarService antarService = new AntarService();
            nota.addService(antarService);
        }
        loginMember.addNota(nota);
        System.out.println("Nota berhasil dibuat!");
    }

    public void lihatNota() {
        for (int i=0; i<loginMember.getNotaList().length; i++) {
            System.out.println(loginMember.getNotaList()[i]);
        }
    }

    public void showPaket() {
        System.out.println("Masukkan paket laundry:");
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    public int cekBerat() {
        System.out.println("Masukkan berat cucian anda [Kg]");
        int berat = input.nextInt();
        if (berat < 2) {
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;
        }
        return berat;
    }

    public boolean pilihSetrika() {
        System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
        System.out.println("Hanya tambah 1000 / kg :0");
        System.out.print("[Ketik x untuk tidak mau]: ");
        String pilihanSetrika = input.next();
        if (pilihanSetrika.equals("x")) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean pilihAntar() {
        System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!");
        System.out.println("Cuma 2000 / 4kg, kemudian 500 / kg");
        System.out.print("[Ketik x untuk tidak mau]: ");
        String pilihanAntar = input.next();
        if (pilihanAntar.equals("x")) {
            return false;
        }
        else {
            return true;
        }
    }
}