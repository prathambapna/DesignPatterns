public class main {
    public static void main(String[] args) {
        UniversityFactory universityFactory=UniversityFactory.getUniversityFactory(250);
        AdmitCard admitCard=universityFactory.getAdmitCard("CSE");
        FeeCalculator feeCalculator=universityFactory.getFee("CSE");
        System.out.println(admitCard.getAdmitCard());
        System.out.println(feeCalculator.calculateFee());

        UniversityFactory universityFactory2=UniversityFactory.getUniversityFactory(199);
        AdmitCard admitCard2=universityFactory2.getAdmitCard("ECE");
        FeeCalculator feeCalculator2=universityFactory2.getFee("ECE");
        System.out.println(admitCard2.getAdmitCard());
        System.out.println(feeCalculator2.calculateFee());
    }
}
