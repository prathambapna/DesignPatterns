public class FiveHundredHandler extends MoneyHandler{
    private int numNotes;
    public FiveHundredHandler(int numNotes){
        this.numNotes=numNotes;
    }

    public void addNotes(int notes){
        numNotes+=notes;
    }

    @Override
    public void dispense(int amount){
        int notesNeeded=amount/500;
        if(notesNeeded>numNotes){
            notesNeeded=numNotes;
            numNotes=0;
        }
        else{
            numNotes-=notesNeeded;
        }
        if(notesNeeded>0){
            System.out.println("Dispensing :" + notesNeeded + " x 500");
        }

        int remainingAmount=amount -( notesNeeded*500);
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
