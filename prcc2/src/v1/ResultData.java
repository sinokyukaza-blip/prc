package v1;

import java.io.Serializable;

public class ResultData implements Serializable {

    private static final long serialVersionUID = 1L;

    private double[] angles;

    private double average;

    private int onesCount;

    private transient String binaryRepresentation;

    public ResultData() {
        angles = new double[4];
    }

    public double[] getAngles() {
        return angles;
    }

    public void setAngles(double[] angles) {
        this.angles = angles;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getOnesCount() {
        return onesCount;
    }

    public void setOnesCount(int onesCount) {
        this.onesCount = onesCount;
    }

    public String getBinaryRepresentation() {
        return binaryRepresentation;
    }

    public void setBinaryRepresentation(String binaryRepresentation) {
        this.binaryRepresentation = binaryRepresentation;
    }

    @Override
    public String toString() {
        return "Average = " + average +
                ", Ones count = " + onesCount +
                ", Binary = " + binaryRepresentation;
    }
}