package v1;

import java.util.concurrent.TimeUnit;

public class ExecuteParallelCommand implements ConsoleCommand {

    private View view;

    public ExecuteParallelCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'e';
    }

    @Override
    public String toString() {
        return "'e'xecute parallel";
    }

    @Override
    public void execute() {
        CommandQueue queue1 = new CommandQueue();
        CommandQueue queue2 = new CommandQueue();
        MaxOnesCommand max = new MaxOnesCommand((ViewResult) view);
        AvgAverageCommand avg = new AvgAverageCommand((ViewResult) view);
        MinMaxAverageCommand minMax = new MinMaxAverageCommand((ViewResult) view);
        System.out.println("Execute parallel threads...");
        queue1.put(minMax);
        queue2.put(max);
        queue2.put(avg);
        try {
            while (avg.running() || max.running() || minMax.running()) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            queue1.shutdown();
            queue2.shutdown();
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        System.out.println("All parallel done.");
    }

    @Override
    public void undo() {
    }
}