import java.util.ArrayList;
import java.util.List;

// ==========================================
// 1. The Elements (The stable data structure)
// ==========================================
interface Animal {
    // Every animal must be able to "accept" a visitor
    void accept(AnimalVisitor visitor);
}

class Lion implements Animal {
    public int getMeatConsumption() { return 10; } // Lion specific data

    @Override
    public void accept(AnimalVisitor visitor) {
        // Double Dispatch: The lion passes 'this' (itself) to the visitor.
        // The compiler knows 'this' is exactly a Lion, routing it to the right method.
        visitor.visit(this);
    }
}

class Penguin implements Animal {
    public int getFishConsumption() { return 5; } // Penguin specific data

    @Override
    public void accept(AnimalVisitor visitor) {
        visitor.visit(this);
    }
}

// ==========================================
// 2. The Visitor Interface
// ==========================================
interface AnimalVisitor {
    // A distinct method for EVERY concrete element type
    void visit(Lion lion);
    void visit(Penguin penguin);
}

// ==========================================
// 3. Concrete Visitors (The Behaviors)
// ==========================================

// Behavior 1: Feeding the animals
class FeedingVisitor implements AnimalVisitor {
    @Override
    public void visit(Lion lion) {
        System.out.println("Feeding the Lion " + lion.getMeatConsumption() + "kg of meat.");
    }

    @Override
    public void visit(Penguin penguin) {
        System.out.println("Feeding the Penguin " + penguin.getFishConsumption() + "kg of fish.");
    }
}

// Behavior 2: A totally different operation added WITHOUT changing the Animal classes!
class JSONExportVisitor implements AnimalVisitor {
    @Override
    public void visit(Lion lion) {
        System.out.println("{ \"type\": \"Lion\", \"meat_diet\": " + lion.getMeatConsumption() + " }");
    }

    @Override
    public void visit(Penguin penguin) {
        System.out.println("{ \"type\": \"Penguin\", \"fish_diet\": " + penguin.getFishConsumption() + " }");
    }
}

// ==========================================
// 4. Putting it into action
// ==========================================
public class Main {
    public static void main(String[] args) {
        List<Animal> zooAnimals = new ArrayList<>();
        zooAnimals.add(new Lion());
        zooAnimals.add(new Penguin());

        // We want to feed them
        AnimalVisitor feeder = new FeedingVisitor();
        System.out.println("--- Feeding Time ---");
        for (Animal animal : zooAnimals) {
            animal.accept(feeder);
        }

        // We want to export their data
        AnimalVisitor exporter = new JSONExportVisitor();
        System.out.println("\n--- Data Export ---");
        for (Animal animal : zooAnimals) {
            animal.accept(exporter);
        }
    }
}