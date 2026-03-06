package v1;

import java.util.ArrayList;
import java.util.List;

public class SortConsoleCommand implements ConsoleCommand {

    private View view;

    private List<ResultData> prevOrder;

    public SortConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'o';  // o for sort
    }

    @Override
    public String toString() {
        return "'o' sort";
    }

    @Override
    public void execute() {
        prevOrder = new ArrayList<>(((ViewResult) view).getResults());
        System.out.println("Sorting by average.");
        ((ViewResult) view).sortByAverage();
        view.viewShow();
    }

    @Override
    public void undo() {
        ((ViewResult) view).getResults().clear();
        ((ViewResult) view).getResults().addAll(prevOrder);
        view.viewShow();
    }
}