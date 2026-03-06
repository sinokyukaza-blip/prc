package v1;

public class SearchConsoleCommand implements ConsoleCommand {

    private View view;

    public SearchConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'f';
    }

    @Override
    public String toString() {
        return "'f' search";
    }

    @Override
    public void execute() {
        System.out.println("Searching max ones count.");
        ResultData max = ((ViewResult) view).findMaxOnes();
        if (max != null) {
            System.out.println("Max: " + max);
        } else {
            System.out.println("Empty collection.");
        }
    }

    @Override
    public void undo() {
    }
}