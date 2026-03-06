package v1;

public class ChangeItemCommand implements Command {

    private ResultData item;

    private double offset;

    private double prevAverage;

    public ResultData setItem(ResultData item) {
        return this.item = item;
    }

    public double setOffset(double offset) {
        return this.offset = offset;
    }

    @Override
    public void execute() {
        prevAverage = item.getAverage();
        item.setAverage(item.getAverage() * offset);
    }

    @Override
    public void undo() {
        item.setAverage(prevAverage);
    }
}