package v1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {

        Calculator calc = new Calculator();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("\n1 - Calculate");
            System.out.println("2 - Save");
            System.out.println("3 - Restore");
            System.out.println("4 - Show");
            System.out.println("0 - Exit");

            String choice = reader.readLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter 4 angles:");
                    double a1 = Double.parseDouble(reader.readLine());
                    double a2 = Double.parseDouble(reader.readLine());
                    double a3 = Double.parseDouble(reader.readLine());
                    double a4 = Double.parseDouble(reader.readLine());
                    calc.calculate(a1, a2, a3, a4);
                    break;

                case "2":
                    calc.save();
                    System.out.println("Saved.");
                    break;

                case "3":
                    calc.restore();
                    System.out.println("Restored.");
                    break;

                case "4":
                    System.out.println(calc.getResult());
                    break;

                case "0":
                    return;
            }
        }
    }
}