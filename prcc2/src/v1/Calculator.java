package v1;

import java.io.*;

public class Calculator {

    private static final String FILE_NAME = "result.bin";

    private ResultData result;

    public Calculator() {
        result = new ResultData();
    }

    public ResultData getResult() {
        return result;
    }

    public void calculate(double a1, double a2, double a3, double a4) {

        double[] angles = {a1, a2, a3, a4};
        result.setAngles(angles);

        double sum = 0;
        for (double a : angles) {
            sum += 1000 * Math.sin(Math.toRadians(a));
        }

        double avg = sum / 4.0;
        result.setAverage(avg);

        int intPart = (int) avg;

        String binary = Integer.toBinaryString(Math.abs(intPart));
        result.setBinaryRepresentation(binary);

        int ones = 0;
        for (char c : binary.toCharArray()) {
            if (c == '1') ones++;
        }

        result.setOnesCount(ones);
    }

    public void save() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME));
        out.writeObject(result);
        out.close();
    }

    public void restore() throws Exception {
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(FILE_NAME));
        result = (ResultData) in.readObject();
        in.close();
    }
}