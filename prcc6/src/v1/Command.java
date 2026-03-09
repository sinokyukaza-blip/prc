package v1;

public interface Command {
    void execute();
    void undo();
}