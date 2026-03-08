// --- State Interface ---
interface State {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
}

// --- Concrete States ---

class NoQuarterState implements State {
    GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You inserted a quarter.");
        // Transition from NoQuarter to HasQuarter
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("You haven't inserted a quarter.");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned, but there's no quarter.");
    }

    @Override
    public void dispense() {
        System.out.println("You need to pay first.");
    }

    @Override
    public String toString() {
        return "waiting for quarter";
    }
}

class HasQuarterState implements State {
    GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You can't insert another quarter.");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Quarter returned.");
        // Transition from HasQuarter to NoQuarter
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned...");
        // Transition from HasQuarter to Sold
        gumballMachine.setState(gumballMachine.getSoldState());
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed.");
    }

    @Override
    public String toString() {
        return "waiting for crank turn";
    }
}

class SoldState implements State {
    GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Please wait, we're already giving you a gumball.");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Sorry, you already turned the crank.");
    }

    @Override
    public void turnCrank() {
        System.out.println("Turning twice doesn't get you another gumball!");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0) {
            // Transition from Sold back to NoQuarter
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            System.out.println("Oops, out of gumballs!");
            // Transition from Sold to SoldOut
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }

    @Override
    public String toString() {
        return "dispensing a gumball";
    }
}

class SoldOutState implements State {
    GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You can't insert a quarter, the machine is sold out.");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("You can't eject, you haven't inserted a quarter yet.");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned, but there are no gumballs.");
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed.");
    }

    @Override
    public String toString() {
        return "sold out";
    }
}

// --- Context Class ---

class GumballMachine {
    private State soldOutState;
    private State noQuarterState;
    private State hasQuarterState;
    private State soldState;

    private State currentState;
    private int count = 0;

    public GumballMachine(int numberGumballs) {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);

        this.count = numberGumballs;
        if (numberGumballs > 0) {
            currentState = noQuarterState;
        } else {
            currentState = soldOutState;
        }
    }

    // Context methods that delegate to the current state
    public void insertQuarter() {
        currentState.insertQuarter();
    }

    public void ejectQuarter() {
        currentState.ejectQuarter();
    }

    public void turnCrank() {
        currentState.turnCrank();
        // Dispense must be called as part of the complete turnCrank operation
        currentState.dispense();
    }

    // Method for states to change the current state
    public void setState(State state) {
        this.currentState = state;
    }

    // Logic to release a ball
    public void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count--;
        }
    }

    // Getters for individual states
    public State getNoQuarterState() { return noQuarterState; }
    public State getHasQuarterState() { return hasQuarterState; }
    public State getSoldState() { return soldState; }
    public State getSoldOutState() { return soldOutState; }
    public int getCount() { return count; }

    @Override
    public String toString() {
        return "\nMighty Gumball, Inc.\nInventory: " + count + " gumball" + (count != 1 ? "s" : "") + "\nMachine is " + currentState + "\n";
    }
}

// --- Main Class for Testing ---

public class Main {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(3);
        System.out.println(gumballMachine);

        // Standard happy path
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);

        // Try some "illegal" actions in the current state
        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();
        gumballMachine.turnCrank(); // Should say "turned, but no quarter"
        System.out.println(gumballMachine);

        // Try two standard purchases to empty the machine
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);

        // The machine should now be sold out
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);
    }
}