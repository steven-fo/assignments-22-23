package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    boolean kerja = false;
    @Override
    public String doWork() {
        // TODO
        kerja = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return kerja;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        return 1000*berat;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
