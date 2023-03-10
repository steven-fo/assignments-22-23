package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    public Member(String nama, String noHp) {
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    public void generateId() {
        this.id = NotaGenerator.generateId(this.nama, this.noHp);
    }

    public String getNama() {
        return this.nama;
    }

    public String getNoHp() {
        return this.noHp;
    }

    public String getId() {
        generateId();
        return this.id;
    }

    public boolean equalsObj(Member anotherMember) {
        return this.getId().equals(anotherMember.getId());
    }
}
