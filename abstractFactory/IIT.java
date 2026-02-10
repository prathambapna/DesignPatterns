public class IIT extends UniversityFactory{

    @Override
    public AdmitCard getAdmitCard(String course) {
        switch(course){
            case "CSE":
                return new IITDadmitcard();
            case "ECE":
                return new IITBadmitcard();
            default:
                return null;
        }
    }

    @Override
    public FeeCalculator getFee(String course) {
        switch(course){
            case "CSE":
                return new IITDFee();
            case "ECE":
                return new IITBFee();
            default:
                return null;
        }
    }
    
    
}
