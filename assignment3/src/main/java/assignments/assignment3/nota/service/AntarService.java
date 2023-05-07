package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    boolean kerja = false;
    @Override
    public String doWork() {
        // TODO
        isDone();
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return kerja;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        if (berat*500 < 2000) {
            return 2000;
        }
        else {
            return berat*500;
        }
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
