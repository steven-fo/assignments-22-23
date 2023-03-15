// Nama: Steven Faustin Orginata
// NPM: 2206030855
// Kelas: F

package assignments.assignment1;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        // TODO: Implement interface menu utama
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.next();
            System.out.println("================================");
            switch (command){
                case "1" -> pilihanSatu();
                case "2" -> pilihanDua();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        // TODO: Implement generate ID sesuai soal.
        String id = "";
        id += (nama.split(" ")[0] + "-").toUpperCase();
        id += nomorHP;

        int checksum = 0;
        for (char c : id.toCharArray()) {
            if (Character.isDigit(c))
                checksum += c - '0';
            else if (Character.isLetter(c))
                checksum += (c - 'A') + 1;
            else
                checksum += 7;
        }
        id += String.format("-%02d", checksum % 100);
        return id;
    }

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // TODO: Implement generate nota sesuai soal.
        int harga = hitungHarga(paket);
        String tanggalSelesai = hitungTanggal(tanggalTerima, paket);
        return "ID    : "+id+"\n" + "Paket : "+paket+"\n" + "Harga :\n" + berat+" kg x "+harga+" = "+berat*harga+"\n" + "Tanggal Terima  : "+tanggalTerima+"\n" + "Tanggal Selesai : "+tanggalSelesai;
    }

    /**
     * Method ketika user input pilihan 1 (generate id)
     */
    public static void pilihanSatu() {
        System.out.println("Masukkan nama Anda:");
        String nama = input.next();
        System.out.println("Masukkan nomor handphone Anda:");
        String nomorHP = cekNomorHP();
        String idPelanggan = generateId(nama, nomorHP);             //call method generate id
        System.out.println("ID Anda : "+idPelanggan);
    }

    /**
     * Method jika user input pilihan 2 (generate nota)
     */
    public static void pilihanDua() {
        System.out.println("Masukkan nama Anda:");
        String nama = input.next();
        System.out.println("Masukkan nomor handphone Anda:");
        String nomorHP = cekNomorHP();
        System.out.println("Masukkan tanggal terima: ");
        String tanggalTerima = input.next();
        String paketLaundry = cekPaketLaundry();
        System.out.println("Masukkan berat cucian Anda [Kg]: ");
        int beratCucian = cekBerat();
        String id = generateId(nama, nomorHP);
        System.out.println("Nota Laundry");
        String nota = generateNota(id, paketLaundry, beratCucian, tanggalTerima);           //call method generate nota
        System.out.println(nota);
    }

    public static String cekNomorHP() {
        String nomorHP = input.next();
        while (!isNumeric(nomorHP)) {
            System.out.println("Nomor hp hanya menerima digit");
            nomorHP = input.next();
        }
        return nomorHP;
    }
    /**
     * Method untuk cek berat (harus bil positif, kurang dari 2 maka berat dianggap 2)
     */
    public static int cekBerat() {
        String berat = input.next();
        while (!isNumeric(berat) || Integer.parseInt(berat) < 1) {
            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
            berat = input.next();
        }
        int beratCucian = Integer.parseInt(berat);

        if (beratCucian < 2) {
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            beratCucian = 2;
        }
        return beratCucian;
    }

    /**
     * Method untuk hitung harga berdasarkan paket dipilih
     */
    public static int hitungHarga(String paket) {
        int harga = 0;
        String paketTemp = paket.toLowerCase();         //lower case untuk cek if else
        if (paketTemp.equals("express")) {
            harga = 12000;
        }
        else if (paketTemp.equals("fast")) {
            harga = 10000;
        }
        else if (paketTemp.equals("reguler")) {
            harga = 7000;
        }

        return harga;
    }

    /**
     * Method untuk hitung tanggal selesai berdasarkan paket dipilih
     */
    public static String hitungTanggal(String tanggalTerima, String paket) {
        LocalDate tanggal = LocalDate.parse(tanggalTerima, DateTimeFormatter.ofPattern("dd/MM/yyyy"));          //membuat date dengan input tanggal terima dan format dd/MM/yyyy
        String paketTemp = paket.toLowerCase();         //lower case untuk cek if else
        if (paketTemp.equals("express")) {
            tanggal = tanggal.plusDays(1);
        }
        else if (paketTemp.equals("fast")) {
            tanggal = tanggal.plusDays(2);
        }
        else if (paketTemp.equals("reguler")) {
            tanggal = tanggal.plusDays(3);
        }
        String tanggalSelesai = tanggal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));                  //return dari localdate diubah menjadi dd/mm/yyyy sebelumnya default yyyy-mm-dd
        return tanggalSelesai;
    }

    /**
     * Method untuk cek paket laundry (express, fast, reguler, selain itu show paket)
     */
    public static String cekPaketLaundry() {
        boolean status = true;
        String paketLaundry = "";
        while (status) {
            System.out.println("Masukkan paket laundry: ");
            String paket = input.next();
            String paketTemp = paket.toLowerCase();                 //to Lower Case untuk mengecek if else
            if (paketTemp.equals("?")) {
                showPaket();
            }
            else if (!paketTemp.equals("express") && !paketTemp.equals("fast") && !paketTemp.equals("reguler")) {
                System.out.println("Paket "+paket+" tidak diketahui");
                System.out.println("[ketik ? untuk mencari tahu jenis paket]");
            }
            else {
                status = false;
                paketLaundry = paket;
            }
        }
        return paketLaundry;
    }

    public static boolean isAlpha(char s) {
        if (!(s >= 'A' && s <= 'Z') && !(s >= 'a' && s <= 'z')) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
} 