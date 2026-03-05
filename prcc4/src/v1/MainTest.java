package v1;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class MainTest {

    @Test
    public void testCalc() {
        ViewTable view = new ViewTable();
        view.setWidth(70);
        view.calculate(0, 90, 180, 270);
        List<ResultData> items = view.getResults();
        Assert.assertEquals(1, items.size());

        ResultData expected = new ResultData();
        expected.setAngles(new double[]{0, 90, 180, 270});
        expected.setAverage(0.0);
        expected.setBinaryRepresentation("0");
        expected.setOnesCount(0);

        ResultData actual = items.get(0);
        Assert.assertArrayEquals(expected.getAngles(), actual.getAngles(), 0.0);
        Assert.assertEquals(expected.getAverage(), actual.getAverage(), 1e-10);
        Assert.assertEquals(expected.getOnesCount(), actual.getOnesCount());
        Assert.assertEquals(expected.getBinaryRepresentation(), actual.getBinaryRepresentation());
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