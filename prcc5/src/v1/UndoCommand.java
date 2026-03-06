package v1;

import java.util.Stack;

public class UndoCommand implements ConsoleCommand {

    private Stack<Command> history;

    public UndoCommand(Stack<Command> history) {
        this.history = history;
    }

    @Override
    public char getKey() {
        return 'u';
    }

    @Override
    public String toString() {
        return "'u'ndo";
    }

    @Override
    public void execute() {
        if (!history.isEmpty()) {
            Command last = history.pop();
            System.out.println("Undoing: " + last.getClass().getSimpleName());
            last.undo();
        } else {
            System.out.println("No commands to undo.");
        }
    }

    @Override
    public void undo() {
    }
}