package v1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testCalculation() {
        Calculator calc = new Calculator();
        calc.calculate(0, 90, 180, 270);

        assertNotNull(calc.getResult());
    }

    @Test
    public void testSerialization() throws Exception {
        Calculator calc = new Calculator();
        calc.calculate(10, 20, 30, 40);

        int onesBefore = calc.getResult().getOnesCount();

        calc.save();
        calc.restore();

        assertEquals(onesBefore,
                calc.getResult().getOnesCount());
    }
}