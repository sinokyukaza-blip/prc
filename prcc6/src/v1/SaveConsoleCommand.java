package v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveConsoleCommand implements ConsoleCommand {

    private View view;

    private List<ResultData> prevState;

    public SaveConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 's';
    }

    @Override
    public String toString() {
        return "'s'ave";
    }

    @Override
    public void execute() {
        prevState = new ArrayList<>(((ViewResult) view).getResults());
        System.out.println("Save current.");
        try {
            view.viewSave();
        } catch (IOException e) {
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