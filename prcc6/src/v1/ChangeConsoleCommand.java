package v1;

public class ChangeConsoleCommand extends ChangeItemCommand implements ConsoleCommand {

    private View view;

    public ChangeConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'h';
    }

    @Override
    public String toString() {
        return "'h' change";
    }

    @Override
    public void execute() {
        double offset = Math.random() * 10.0;
        System.out.println("Change averages by factor: " + offset);
        for (ResultData item : ((ViewResult) view).getResults()) {
            setItem(item);
            setOffset(offset);
            super.execute();
        }
        view.viewShow();
    }

    @Override
    public void undo() {
        for (ResultData item : ((ViewResult) view).getResults()) {
            setItem(item);
            super.undo();
        }
        view.viewShow();
    }
}