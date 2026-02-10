public class NIT  extends UniversityFactory{

    @Override
    public AdmitCard getAdmitCard(String course) {
        switch(course){
            case "CSE":
                return new NITKadmitcard();
            case "ECE":
                return new SVNITadmitcard();
            default:
                return null;
        }
    }

    @Override
    public FeeCalculator getFee(String course) {
        switch(course){
            case "CSE":
                return new NITKFee();
            case "ECE":
                return new SVNITFee();
            default:
                return null;
        }
    }
    
}
