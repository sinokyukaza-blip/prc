package v1;

import java.util.Stack;

public class Application {

    private static Application instance = new Application();

    private Application() {}

    public static Application getInstance() {
        return instance;
    }

    private View view = new ViewableTable().getView();

    private Menu menu = new Menu();

    private Stack<Command> history = new Stack<>();

    public void run() {
        menu.add(new ViewConsoleCommand(view));
        menu.add(new GenerateConsoleCommand(view));
        menu.add(new ChangeConsoleCommand(view));
        menu.add(new SaveConsoleCommand(view));
        menu.add(new RestoreConsoleCommand(view));
        menu.add(new SetWidthConsoleCommand(view));
        menu.add(new SortConsoleCommand(view));
        menu.add(new SearchConsoleCommand(view));
        menu.add(new MacroConsoleCommand(view));
        menu.add(new ExecuteParallelCommand(view));  // New parallel command
        menu.add(new UndoCommand(history));
        menu.execute();
    }

    public void addToHistory(Command cmd) {
        history.push(cmd);
    }
}