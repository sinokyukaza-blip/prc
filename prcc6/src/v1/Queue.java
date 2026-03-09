package v1;

public interface Queue {
    void put(Command cmd);
    Command take();
}