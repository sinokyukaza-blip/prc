package v1;

public class SetWidthConsoleCommand implements ConsoleCommand {

    private View view;

    private int prevWidth;

    public SetWidthConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'w';
    }

    @Override
    public String toString() {
        return "'w'idth";
    }

    @Override
    public void execute() {
        prevWidth = ((ViewTable) view).getWidth();
        System.out.println("Set new width (e.g., 70):");
        try {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            int width = scanner.nextInt();
            ((ViewTable) view).setWidth(width);
        } catch (Exception e) {
            System.out.println("Input error: " + e);
        }
    }

    @Override
    public void undo() {
        ((ViewTable) view).setWidth(prevWidth);
    }
}