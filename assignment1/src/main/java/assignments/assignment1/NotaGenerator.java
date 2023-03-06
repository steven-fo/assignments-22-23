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
        while (true) {
            printMenu();
            cekPilihan();
        }
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
        nama = nama.toUpperCase();
        int checkSum = 0;
        String id = nama+'-'+nomorHP;
        for (int i = 0; i<id.length(); i++) {
            if (isAlpha(id.charAt(i))) {
                checkSum += nama.charAt(i) - '@';           //dikurang @ agar nilai dec A = 1
            }
            else {
                checkSum += 7;
            }
        }
        for (int i = 0; i<nomorHP.length(); i++) {
            checkSum += nomorHP.charAt(i) - '0';        //dikurang 0 agar nilai 0 = 0
        }
        if (checkSum > 100) {
            checkSum = checkSum % 100;
        } 
        else if (checkSum < 10) {
            return nama+'-'+nomorHP+"-0"+checkSum;
        }
        return nama+'-'+nomorHP+'-'+checkSum;           //return id pelanggan
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
        String notaLaundry = "";
        int harga = hitungHarga(paket);
        String tanggalSelesai = hitungTanggal(tanggalTerima, paket);
        notaLaundry += "ID    : "+id+"\n";
        notaLaundry += "Paket : "+paket+"\n";
        notaLaundry += "Harga :\n";
        notaLaundry += berat+" kg x "+harga+" = "+berat*harga+"\n";
        notaLaundry += "Tanggal Terima  : "+tanggalTerima+"\n";
        notaLaundry += "Tanggal Selesai : "+tanggalSelesai+"\n";
        return notaLaundry;
    }

    /**
     * Method untuk cek pilihan (0, 1, 2, pilihan selain 3 tsb)
     */
    public static void cekPilihan() {
        System.out.print("Pilihan : ");
        try {
            int pilihan = input.nextInt();
            System.out.println("================================");
            if (pilihan == 0) {
                input.close();
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                System.exit(0);
            }
            else if (pilihan == 1) {
                pilihanSatu();
            }
            else if (pilihan == 2) {
                pilihanDua();
            }
            else {
                throw new Exception();
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
        }
    }

    /**
     * Method ketika user input pilihan 1 (generate id)
     */
    public static void pilihanSatu() {
        System.out.println("Masukkan nama Anda:");
        String nama = input.next();
        input.nextLine();
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
        input.nextLine();
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
        boolean status = true;
        boolean isNumeric = true;
        String nomorHP = "";
        while (status) {
            String nomor = input.next();
            for (int i = 0; i<nomor.length(); i++) {
                if (Character.isDigit(nomor.charAt(i))) {
                    isNumeric = true;
                    nomorHP += nomor.charAt(i);
                }
                else {
                    isNumeric = false;
                    break;
                }
            }
            if (isNumeric) {
                status = false;
            }
            else {
                System.out.println("Nomor hp hanya menerima digit");
            }
        }
        return nomorHP;
    }
    /**
     * Method untuk cek berat (harus bil positif, kurang dari 2 maka berat dianggap 2)
     */
    public static int cekBerat() {
        int beratCucian = 0;
        boolean status = true;
        while (status) {
            int berat = input.nextInt();
            if (berat < 2 && berat > 0) {
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                beratCucian = 2;
                status = false;
            }
            else if (berat >= 2) {
                beratCucian = berat;
                status = false;
            }
            else {
                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
            }
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
            System.out.println("express");
        }
        else if (paketTemp.equals("fast")) {
            tanggal = tanggal.plusDays(2);
            System.out.println("fast");
        }
        else if (paketTemp.equals("reguler")) {
            tanggal = tanggal.plusDays(3);
            System.out.println("reguler");
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
} 