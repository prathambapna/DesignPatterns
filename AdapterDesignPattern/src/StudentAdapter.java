import java.time.LocalDate;
import java.time.Period;

public class StudentAdapter implements Student{
    BirthCertificateDetails birthCertificateDetails;
    public StudentAdapter(BirthCertificateDetails birthCertificateDetails){
        this.birthCertificateDetails=birthCertificateDetails;
    }
    @Override
    public String getFullname(){
        return birthCertificateDetails.getFirst_name()+" "+birthCertificateDetails.getLast_name();
    }

    @Override
    public int getAge(){
        LocalDate birthDate=birthCertificateDetails.getDate().toLocalDate();
        LocalDate currentDate=LocalDate.now();
        return(Period.between(birthDate,currentDate).getYears());
    }
}
