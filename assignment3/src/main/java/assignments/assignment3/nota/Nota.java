package assignments.assignment3.nota;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services = new LaundryService[0];
    private long baseHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int id = 0;
    private String tanggalMasuk;
    private boolean isDone;
    static public int totalNota;
    private int pointer = 0;                   
    private String hargaAkhirString = "";

    public Nota(Member member, int berat, String paket, String tanggal) {
        //TODO
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.sisaHariPengerjaan = hitungSisaHari();
    }

    public void addService(LaundryService service){
        //TODO
        LaundryService[] tempList = new LaundryService[services.length+1];
        for (int i=0; i<services.length; i++) {
            tempList[i] = services[i];
        }
        tempList[services.length] = service;
        services = tempList;
    }

    public String kerjakan(){
        // TODO
        if (pointer != services.length) {
            if (services.length > 1) {
                LaundryService service = services[pointer];
                pointer++;
                return service.doWork();
            }
            else {
                LaundryService service = services[pointer];
                return service.doWork();
            }
        }
        else {
            return "Sudah selesai.";
        }
    }

    public void toNextDay() {
        // TODO
        System.out.println("masuk");
        System.out.println(this.sisaHariPengerjaan);
        this.sisaHariPengerjaan -= 1;
    }

    public long calculateHarga(){
        // TODO
        baseHarga = NotaGenerator.hitungHarga(this.paket);
        return this.berat*baseHarga;
    }

    public String getNotaStatus(){
        // TODO
        if (pointer != services.length-1) {
            return "Belum selesai";
        }
        else {
            return "Sudah selesai";
        }
    }

    @Override
    public String toString(){
        // TODO
        String outputService = "";
        long hargaAkhir = calculateHarga();
        String output = NotaGenerator.generateNota(member.getId(), paket, berat, tanggalMasuk);
        String serviceList = "\n--- SERVICE LIST ---\n";
        for (int i=0; i<services.length; i++) {
            outputService = outputService+"-"+services[i].getServiceName()+" @ Rp."+services[i].getHarga(berat)+"\n";
            hargaAkhir += services[i].getHarga(berat);
        }
        if (this.sisaHariPengerjaan < 0) {
            hargaAkhir = hargaAkhir + this.sisaHariPengerjaan*2000;
            if (hargaAkhir <= 0) {
                hargaAkhir = 0;
            }
            hargaAkhirString = "Harga Akhir: "+hargaAkhir+" Ada kompensasi keterlambatan "+this.sisaHariPengerjaan*-1+" * 2000 hari\n";
        }
        else {
            hargaAkhirString = "Harga Akhir: "+hargaAkhir+"\n";
        }
        return output+serviceList+outputService+hargaAkhirString;
    }

    public int hitungSisaHari() {
        if (this.paket.equals("express")) {
            return 1;
        }
        else if (this.paket.equals("fast")) {
            return 2;
        }
        else if (this.paket.equals("reguler")) {
            return 3;
        }
        return 0;
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }
}
