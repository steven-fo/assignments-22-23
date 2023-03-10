package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import java.util.ArrayList;

import assignments.assignment1.NotaGenerator;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();                        //ubah array menjadi array list
    private static ArrayList<Member> memberList = new ArrayList<Member>();                 //ubah array menjadi array list
    private static int idNota = 0;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        // TODO: handle generate user
        boolean isExist = false;
        System.out.println("Masukkan nama Anda:");
        String nama = input.nextLine();
        System.out.println("Masukkan nomor handphone Anda:");
        String nomorHP = cekNomorHP();
        String idPelanggan = generateId(nama, nomorHP);
        Member member = new Member(nama, nomorHP);
        if (memberList.size() > 0) {
            for (int i = 0; i<memberList.size(); i++) {
                if (member.equalsObj(memberList.get(i))) {
                    isExist = true;
                    break;
                }
            }
        }
        if (isExist) {
            System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", member.getNama(), member.getNoHp());
        }
        else {
            memberList.add(member);
            System.out.printf("Berhasil membuat member dengan ID %s!\n", idPelanggan);
        }
    }

    private static void handleGenerateNota() {
        // TODO: handle ambil cucian
        Member member = null;
        boolean notExist = false;
        System.out.println("Masukkan ID member:");
        String idMember = input.nextLine();
        if (memberList.size() == 0) {
            notExist = true;
        }
        else {
            for (int i = 0; i<memberList.size(); i++) {
                if (memberList.get(i).getId().equals(idMember)) {
                    member = memberList.get(i);
                    String paketLaundry = cekPaketLaundry();
                    System.out.println("Masukkan berat cucian Anda [Kg]:");
                    int beratCucian = cekBerat();
                    System.out.println("Berhasil menambahkan nota!");
                    String tanggalMasuk = fmt.format(cal.getTime());
                    System.out.printf("[ID Nota = %d]\n", idNota);
                    Nota nota = new Nota(member, paketLaundry, beratCucian, tanggalMasuk);
                    System.out.println(nota);
                    String status = cekStatus(nota);
                    System.out.printf("Status      	: %s\n", status);
                    notaList.add(nota);
                    idNota++;
                    notExist = false;
                    break;
                }
                else {
                    notExist = true;
                }
            }
        }
        if (notExist) {
            System.out.printf("Member dengan ID %s tidak ditemukan!\n", idMember);
        }
    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        int jumlahNota;
        if (memberList == null) {
            jumlahNota = 0;
            System.out.printf("Terdaftar %d nota dalam sistem.\n", jumlahNota);
        }
        else {
            System.out.printf("Terdaftar %d nota dalam sistem.\n", notaList.size());
            for (int i = 0; i<notaList.size(); i++) {
                System.out.printf("- [%d] Status      	: %s\n", i, cekStatus(notaList.get(i)));
            }
        }
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        int jumlahMember;
        if (memberList == null) {
            jumlahMember = 0;
            System.out.printf("Terdaftar %d member dalam sistem.\n", jumlahMember);
        }
        else {
            System.out.printf("Terdaftar %d member dalam sistem.\n", memberList.size());
            for (int i = 0; i<memberList.size(); i++) {
                System.out.printf("- %s : %s\n", memberList.get(i).getId(), memberList.get(i).getNama());
            }
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
        String idNotaAmbil;
        System.out.println("Masukkan ID nota yang akan diambil:");
        idNotaAmbil = cekIdNota();
        if (Integer.parseInt(idNotaAmbil) <= notaList.size()) {
            if (notaList.get(Integer.parseInt(idNotaAmbil)).getIsReady(fmt.format(cal.getTime()))) {
                notaList.set(Integer.parseInt(idNotaAmbil), null);
                System.out.printf("Nota dengan ID %s berhasil diambil!\n", idNotaAmbil);
            }
            else {
                System.out.printf("Nota dengan ID %s gagal diambil!\n", idNotaAmbil);
            }
        }
        else {
            System.out.printf("Nota dengan ID %s tidak ditemukan!\n", idNotaAmbil);
        }
    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
        System.out.println("Dek Depe tidur hari ini... zzz...");
        cal.add(Calendar.DAY_OF_MONTH, 1);
        for (int i = 0; i<notaList.size(); i++) {
            if (notaList.get(i).getIsReady(fmt.format(cal.getTime()))) {
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!\n", i);
            }
        }
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

    public static String cekStatus(Nota nota) {
        String status = "";
        if (!nota.getIsReady(fmt.format(cal.getTime()))) {
            status = "Belum bisa diambil :(";
        }
        else {
            status = "Sudah dapat diambil!";
        }
        return status;
    }

    public static String cekIdNota() {
        boolean status = true;
        boolean isNumeric = true;
        String idNota = "";
        while (status) {
            String id = input.next();
            for (int i = 0; i<id.length(); i++) {
                if (Character.isDigit(id.charAt(i))) {
                    isNumeric = true;
                }
                else {
                    isNumeric = false;
                    break;
                }
            }
            if (!isNumeric) {
                idNota = id;
                status = false;
            }
            else {
                System.out.println("ID nota berbentuk angka!");
            }
        }
        return idNota;
    }
}