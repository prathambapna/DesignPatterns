import java.time.LocalDateTime;

public class BirthCertificateDetails {
    String first_name,last_name;
    LocalDateTime date;
    public BirthCertificateDetails(String first_name,String last_name,LocalDateTime date){
        this.first_name=first_name;
        this.last_name=last_name;
        this.date=date;
    }
    String getFirst_name(){return first_name;}
    String getLast_name(){return last_name;}
    LocalDateTime getDate(){return date;}
}
