package v1;

import java.util.concurrent.TimeUnit;

public class MaxOnesCommand implements Command {

    private int result = -1;

    private int progress = 0;

    private ViewResult viewResult;

    public ViewResult getViewResult() {
        return viewResult;
    }

    public ViewResult setViewResult(ViewResult viewResult) {
        return this.viewResult = viewResult;
    }

    public MaxOnesCommand(ViewResult viewResult) {
        this.viewResult = viewResult;
    }

    public int getResult() {
        return result;
    }

    public boolean running() {
        return progress < 100;
    }

    @Override
    public void execute() {
        progress = 0;
        System.out.println("MaxOnes executed...");
        int size = viewResult.getResults().size();
        result = viewResult.getResults().parallelStream()
                .max((r1, r2) -> Integer.compare(r1.getOnesCount(), r2.getOnesCount()))
                .map(viewResult.getResults()::indexOf)
                .orElse(-1);
        progress = 100;
        System.out.println("MaxOnes done. Result index = " + result);
    }

    @Override
    public void undo() {
    }
}