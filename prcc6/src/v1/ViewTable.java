package v1;

import java.util.Formatter;

public class ViewTable extends ViewResult {

    private static final int DEFAULT_WIDTH = 70;

    private int width;

    public ViewTable() {
        width = DEFAULT_WIDTH;
    }

    public int setWidth(int width) {
        return this.width = width;
    }

    public int getWidth() {
        return width;
    }

    private void outLine() {
        for (int i = width; i > 0; i--) {
            System.out.print('-');
        }
    }

    private void outLineLn() {
        outLine();
        System.out.println();
    }

    private void outHeader() {
        Formatter fmt = new Formatter();
        fmt.format("%-" + (width / 7) + "s|%-" + (width / 7) + "s|%-" + (width / 7) + "s|%-" + (width / 7) + "s|%-" + (width / 7) + "s|%-" + (width / 7) + "s|%-" + (width / 7) + "s\n",
                "Angle1", "Angle2", "Angle3", "Angle4", "Average", "Ones", "Binary");
        System.out.print(fmt);
    }

    private void outBody() {
        Formatter fmt = new Formatter();
        for (ResultData result : getResults()) {
            fmt.format("%-" + (width / 7) + ".2f|%-" + (width / 7) + ".2f|%-" + (width / 7) + ".2f|%-" + (width / 7) + ".2f|%-" + (width / 7) + ".4f|%-" + (width / 7) + "d|%-" + (width / 7) + "s\n",
                    result.getAngles()[0], result.getAngles()[1], result.getAngles()[2], result.getAngles()[3],
                    result.getAverage(), result.getOnesCount(), result.getBinaryRepresentation());
            System.out.print(fmt);
        }
    }

    public void init(int width) {
        setWidth(width);
        viewInit();
    }

    @Override
    public void viewInit() {
        System.out.print("Initialization... ");
        super.viewInit();
        System.out.println("done.");
    }

    @Override
    public void viewHeader() {
        outHeader();
        outLineLn();
    }

    @Override
    public void viewBody() {
        outBody();
    }

    @Override
    public void viewFooter() {
        outLineLn();
    }
}