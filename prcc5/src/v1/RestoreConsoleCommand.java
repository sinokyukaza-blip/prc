package v1;

import java.util.ArrayList;
import java.util.List;

public class RestoreConsoleCommand implements ConsoleCommand {

    private View view;

    private List<ResultData> prevState;

    public RestoreConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'r';
    }

    @Override
    public String toString() {
        return "'r'estore";
    }

    @Override
    public void execute() {
        prevState = new ArrayList<>(((ViewResult) view).getResults());
        System.out.println("Restore last saved.");
        try {
            view.viewRestore();
        } catch (Exception e) {
            System.out.println("Serialization error: " + e);
        }
        view.viewShow();
    }

    @Override
    public void undo() {
        ((ViewResult) view).getResults().clear();
        ((ViewResult) view).getResults().addAll(prevState);
        view.viewShow();
    }
}