package v1;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MainTest {

    @Test
    public void testChangeItemCommand() {
        ResultData item = new ResultData();
        item.setAverage(10.0);

        ChangeItemCommand cmd = new ChangeItemCommand();
        cmd.setItem(item);
        cmd.setOffset(2.0);
        cmd.execute();
        Assert.assertEquals(20.0, item.getAverage(), 1e-10);

        cmd.undo();
        Assert.assertEquals(10.0, item.getAverage(), 1e-10);
    }

    @Test
    public void testMacro() {
        ViewTable view = new ViewTable();
        MacroConsoleCommand macro = new MacroConsoleCommand(view);
        macro.execute();
        Assert.assertEquals(1, view.getResults().size());  // Generated one
        // Average changed, but specific value random

        macro.undo();
        Assert.assertEquals(0, view.getResults().size());  // Undid change and generate
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