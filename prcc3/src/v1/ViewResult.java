package v1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ViewResult implements View {

    private static final String FILE_NAME = "results.bin";

    private List<ResultData> results = new ArrayList<>();

    public List<ResultData> getResults() {
        return results;
    }

    public void calculate(double a1, double a2, double a3, double a4) {
        ResultData result = new ResultData();
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
        results.add(result);
    }

    @Override
    public void viewInit() {
        double a1 = Math.random() * 360;
        double a2 = Math.random() * 360;
        double a3 = Math.random() * 360;
        double a4 = Math.random() * 360;
        calculate(a1, a2, a3, a4);
    }

    @Override
    public void viewSave() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        out.writeObject(results);
        out.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
        results = (List<ResultData>) in.readObject();
        in.close();
    }

    @Override
    public void viewHeader() {
        System.out.println("Computation Results:");
    }

    @Override
    public void viewBody() {
        for (ResultData result : results) {
            System.out.println(result);
        }
    }

    @Override
    public void viewFooter() {
        System.out.println("End of Results.");
    }

    @Override
    public void viewShow() {
        viewHeader();
        viewBody();
        viewFooter();
    }
}