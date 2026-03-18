import java.util.Stack;

// 1. The Memento (The Snapshot)
// Notice how the state is 'final' and there are no setter methods.
class EditorMemento {
    private final String content;

    public EditorMemento(String content) {
        this.content = content;
    }

    public String getSavedContent() {
        return content;
    }
}

// 2. The Originator (The Object we are tracking)
class TextEditor {
    private StringBuilder content;

    public TextEditor() {
        this.content = new StringBuilder();
    }

    public void typeWords(String words) {
        this.content.append(words);
    }

    public String getContent() {
        return this.content.toString();
    }

    // Creates a snapshot of its current state
    public EditorMemento save() {
        return new EditorMemento(this.content.toString());
    }

    // Restores state from a snapshot
    public void restore(EditorMemento memento) {
        // In a real app, you'd want null checks here!
        if (memento != null) {
            this.content = new StringBuilder(memento.getSavedContent());
        }
    }
}

// 3. The Caretaker (The History Manager)
class HistoryCaretaker {
    // A Stack is the perfect data structure for Undo functionality
    private final Stack<EditorMemento> history = new Stack<>();

    public void push(EditorMemento memento) {
        history.push(memento);
    }

    public EditorMemento undo() {
        if (history.isEmpty()) {
            return null;
        }
        return history.pop();
    }
}

// --- Putting it into action ---
public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        HistoryCaretaker history = new HistoryCaretaker();

        editor.typeWords("Hello, ");
        history.push(editor.save()); // Save state 1

        editor.typeWords("World!");
        history.push(editor.save()); // Save state 2

        editor.typeWords(" This is a mistake.");
        System.out.println("Current text: '" + editor.getContent() + "'");
        // Output: Current text: 'Hello, World! This is a mistake.'

        // Let's undo the mistake!
        editor.restore(history.undo());
        System.out.println("After undo 1: '" + editor.getContent() + "'");
        // Output: After undo 1: 'Hello, World!'

        editor.restore(history.undo());
        System.out.println("After undo 2: '" + editor.getContent() + "'");
        // Output: After undo 2: 'Hello, '
    }
}