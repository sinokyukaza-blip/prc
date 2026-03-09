package v1;

import java.util.concurrent.TimeUnit;

public class MinMaxAverageCommand implements Command {

    private double minPositive = Double.MAX_VALUE;

    private double maxNegative = Double.MIN_VALUE;

    private int progress = 0;

    private ViewResult viewResult;

    public ViewResult getViewResult() {
        return viewResult;
    }

    public ViewResult setViewResult(ViewResult viewResult) {
        return this.viewResult = viewResult;
    }

    public MinMaxAverageCommand(ViewResult viewResult) {
        this.viewResult = viewResult;
    }

    public double getMinPositive() {
        return minPositive;
    }

    public double getMaxNegative() {
        return maxNegative;
    }

    public boolean running() {
        return progress < 100;
    }

    @Override
    public void execute() {
        progress = 0;
        System.out.println("MinMaxAverage executed...");
        viewResult.getResults().parallelStream().forEach(r -> {
            double avg = r.getAverage();
            if (avg > 0 && avg < minPositive) minPositive = avg;
            if (avg < 0 && avg > maxNegative) maxNegative = avg;
        });
        progress = 100;
        System.out.println("MinMaxAverage done. Min positive = " + minPositive + ", Max negative = " + maxNegative);
    }

    @Override
    public void undo() {
    }
}