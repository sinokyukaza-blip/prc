package v1;

public class MacroConsoleCommand implements ConsoleCommand {

    private Command generate;

    private Command change;

    public MacroConsoleCommand(View view) {
        generate = new GenerateConsoleCommand(view);
        change = new ChangeConsoleCommand(view);
    }

    @Override
    public char getKey() {
        return 'm';
    }

    @Override
    public String toString() {
        return "'m'acro (generate + change)";
    }

    @Override
    public void execute() {
        System.out.println("Executing macro...");
        generate.execute();
        change.execute();
    }

    @Override
    public void undo() {
        change.undo();
        generate.undo();
    }
}