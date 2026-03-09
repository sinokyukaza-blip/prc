package v1;

public class ViewableTable implements Viewable {
    @Override
    public View getView() {
        return new ViewTable();
    }
}