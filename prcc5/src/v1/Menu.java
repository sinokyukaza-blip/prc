package v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Command {

    private List<ConsoleCommand> menu = new ArrayList<>();

    public ConsoleCommand add(ConsoleCommand command) {
        menu.add(command);
        return command;
    }

    @Override
    public String toString() {
        String s = "Enter command...\n";
        for (ConsoleCommand c : menu) {
            s += c + ", ";
        }
        return s.substring(0, s.length() - 2) + ": ";
    }

    @Override
    public void execute() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            do {
                System.out.println(this);
                try {
                    s = in.readLine();
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                    System.exit(0);
                }
            } while (s.length() != 1);
            char key = s.charAt(0);
            if (key == 'q') {
                System.out.println("Exit.");
                return;
            }
            boolean found = false;
            for (ConsoleCommand c : menu) {
                if (key == c.getKey()) {
                    c.execute();
                    if (!(c instanceof UndoCommand || c instanceof ViewConsoleCommand || c instanceof SearchConsoleCommand)) {
                        Application.getInstance().addToHistory(c);
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Wrong command.");
            }
        }
    }

    @Override
    public void undo() {
    }
}