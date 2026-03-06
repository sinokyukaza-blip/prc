package v1;

public class Application {

    private static Application instance = new Application();

    private Application() {}

    public static Application getInstance() {
        return instance;
    }

    private View view = new ViewableTable().getView();

    private Menu menu = new Menu();

    private java.util.Stack<Command> history = new java.util.Stack<>();

    public void run() {
        menu.add(new ViewConsoleCommand(view));
        menu.add(new GenerateConsoleCommand(view));
        menu.add(new ChangeConsoleCommand(view));
        menu.add(new SaveConsoleCommand(view));
        menu.add(new RestoreConsoleCommand(view));
        menu.add(new SetWidthConsoleCommand(view));
        menu.add(new SortConsoleCommand(view));
        menu.add(new SearchConsoleCommand(view));
        menu.add(new MacroConsoleCommand(view));  // Demonstration of macro
        menu.add(new UndoCommand(history));  // Undo last
        menu.execute();
    }

    public void addToHistory(Command cmd) {
        history.push(cmd);
    }
}