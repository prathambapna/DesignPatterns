public abstract class UniversityFactory {
    private static int cutoff=200;
    public static UniversityFactory getUniversityFactory(int marks){
        if(marks>=cutoff){
            return new IIT();
        }
        else{
            return new NIT();
        }
    }
    abstract public AdmitCard getAdmitCard(String course);
    abstract public FeeCalculator getFee(String course);
}
