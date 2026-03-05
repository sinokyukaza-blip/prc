package v1;

public class ViewableResult implements Viewable {
    @Override
    public View getView() {
        return new ViewResult();
    }
}