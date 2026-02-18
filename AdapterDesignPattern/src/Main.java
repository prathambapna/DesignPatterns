import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BirthCertificateDetails b1=new BirthCertificateDetails("Pratham", "bapna", LocalDateTime.of(2002, 10, 5, 8, 15));
        Student s1=new StudentAdapter(b1);
        System.out.println("Student's name: "+s1.getFullname());
        System.out.println("Student's age: "+s1.getAge());
    }
}