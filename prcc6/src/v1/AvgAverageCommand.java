package v1;

import java.util.concurrent.TimeUnit;

public class AvgAverageCommand implements Command {

    private double result = 0.0;

    private int progress = 0;

    private ViewResult viewResult;

    public ViewResult getViewResult() {
        return viewResult;
    }

    public ViewResult setViewResult(ViewResult viewResult) {
        return this.viewResult = viewResult;
    }

    public AvgAverageCommand(ViewResult viewResult) {
        this.viewResult = viewResult;
    }

    public double getResult() {
        return result;
    }

    public boolean running() {
        return progress < 100;
    }

    @Override
    public void execute() {
        progress = 0;
        System.out.println("AvgAverage executed...");
        int size = viewResult.getResults().size();
        result = viewResult.getResults().parallelStream()
                .mapToDouble(ResultData::getAverage)
                .average()
                .orElse(0.0);
        progress = 100;
        System.out.println("AvgAverage done. Result = " + String.format("%.2f", result));
    }

    @Override
    public void undo() {
    }
}