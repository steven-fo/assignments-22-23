package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO:
        switch (choice) {
            case 1 -> kerjakan();
            case 2 -> displayNota();
            case 3 -> logout = true;
            default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }

    protected void kerjakan() {
        System.out.printf("Stand back! %s beginning to nyuci!\n", loginMember.getNama());
        for (int i=0; i<notaList.length; i++) {
            System.out.printf("Nota %d : %s \n", i, notaList[i].kerjakan());
        }
    }

    protected void displayNota() {
        for (int i=0; i<notaList.length; i++) {
            System.out.printf("Nota %d : %s \n", i, notaList[i].getNotaStatus());
        }
    }

    public void addEmployee(Employee[] employees) {
        Member[] result = new Member[employees.length + memberList.length];
     
     
        System.arraycopy(memberList, 0, result, 0, memberList.length);
        System.arraycopy(employees, 0, result, memberList.length, employees.length);
     
        memberList = result;
     }
     
}