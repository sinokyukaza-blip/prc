package v1;

import java.util.List;

public class GenerateConsoleCommand implements ConsoleCommand {

    private View view;

    public GenerateConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'g';
    }

    @Override
    public String toString() {
        return "'g'enerate";
    }

    @Override
    public void execute() {
        System.out.println("Generate new result.");
        view.viewInit();
        view.viewShow();
    }

    @Override
    public void undo() {
        List<ResultData> results = ((ViewResult) view).getResults();
        if (!results.isEmpty()) {
            results.remove(results.size() - 1);
        }
        view.viewShow();
    }
}