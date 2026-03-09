package v1;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MainTest {

    @Test
    public void testMaxOnesCommand() {
        ViewResult view = new ViewResult();
        view.calculate(0, 90, 180, 270);
        view.calculate(45, 135, 225, 315);
        MaxOnesCommand cmd = new MaxOnesCommand(view);
        cmd.execute();
        Assert.assertTrue(cmd.getResult() >= 0);
    }

    @Test
    public void testAvgAverageCommand() {
        ViewResult view = new ViewResult();
        view.calculate(0, 90, 180, 270);
        view.calculate(45, 135, 225, 315);
        AvgAverageCommand cmd = new AvgAverageCommand(view);
        cmd.execute();
        Assert.assertEquals(0.0, cmd.getResult(), 1e-10);  // Змінено на assertEquals для перевірки близькості до 0
    }

    @Test
    public void testMinMaxAverageCommand() {
        ViewResult view = new ViewResult();
        view.calculate(0, 90, 180, 270);
        view.calculate(45, 135, 225, 315);
        MinMaxAverageCommand cmd = new MinMaxAverageCommand(view);
        cmd.execute();
        Assert.assertNotEquals(Double.MAX_VALUE, cmd.getMinPositive());
    }

    @Test
    public void testCommandQueue() {
        ViewResult view = new ViewResult();
        CommandQueue queue = new CommandQueue();
        MaxOnesCommand max = new MaxOnesCommand(view);
        queue.put(max);
        try {
            Thread.sleep(1000);  // Wait for execution
        } catch (InterruptedException e) {
            Assert.fail(e.getMessage());
        }
        queue.shutdown();
        Assert.assertFalse(max.running());
    }

    @Test
    public void testRestore() {
        ViewTable view1 = new ViewTable();
        ViewTable view2 = new ViewTable();

        view1.calculate(0, 90, 180, 270);
        view1.calculate(45, 135, 225, 315);

        try {
            view1.viewSave();
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }

        try {
            view2.viewRestore();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        Assert.assertEquals(view1.getResults().size(), view2.getResults().size());
        Assert.assertTrue("containsAll()", view1.getResults().containsAll(view2.getResults()));
    }
}