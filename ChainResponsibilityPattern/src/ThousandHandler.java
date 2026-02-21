public class ThousandHandler extends MoneyHandler{
    private int numNotes;
    public ThousandHandler(int numNotes){
        this.numNotes=numNotes;
    }

    public void addNotes(int notes){
        numNotes+=notes;
    }

    @Override
    public void dispense(int amount){
        int notesNeeded=amount/1000;
        if(notesNeeded>numNotes){
            notesNeeded=numNotes;
            numNotes=0;
        }
        else{
            numNotes-=notesNeeded;
        }
        if(notesNeeded>0){
            System.out.println("Dispensing :" + notesNeeded + " x 1000");
        }

        int remainingAmount=amount -( notesNeeded*1000);
        if(remainingAmount>0){
            if(this.nextHandler!=null){
                this.nextHandler.dispense(remainingAmount);
            }
            else{
                System.out.println("Sorry, no more money available");
            }
        }

    }

}
