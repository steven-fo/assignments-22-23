package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady = false;

    //tambahan selain di soal
    private String tanggalSelesai;
    private int harga;
    private int bonus;

    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        // TODO: buat constructor untuk class ini
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.tanggalSelesai = NotaGenerator.hitungTanggal(this.tanggalMasuk, this.paket);
        this.harga = NotaGenerator.hitungHarga(this.paket);
        this.bonus = 1;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public boolean getIsReady(String tanggalSekarang) {
        if (tanggalSekarang.equals(this.tanggalSelesai)) {
            isReady = true;
        }
        return isReady;
    }

    public String toString() {
        String output = "";
        if (member.getBonusCounter() == 3) {
            this.bonus = 2;
            output = "ID    : "+this.member.getId()+"\n" + "Paket : "+this.paket+"\n" + "Harga :\n" + this.berat+" kg x "+this.harga+" = "+this.berat*this.harga +" = "+ this.berat*this.harga/this.bonus +"(Discount member 50%!!!)\n" + "Tanggal Terima  : "+this.tanggalMasuk+"\n" + "Tanggal Selesai : "+this.tanggalSelesai;
        }
        else {
            output = "ID    : "+this.member.getId()+"\n" + "Paket : "+this.paket+"\n" + "Harga :\n" + this.berat+" kg x "+this.harga+" = "+this.berat*this.harga/this.bonus+"\n" + "Tanggal Terima  : "+this.tanggalMasuk+"\n" + "Tanggal Selesai : "+this.tanggalSelesai;
        }
        return output;
    }
}
