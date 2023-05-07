package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    boolean kerja = false;
    @Override
    public String doWork() {
        // TODO
        kerja = true;
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return kerja;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
