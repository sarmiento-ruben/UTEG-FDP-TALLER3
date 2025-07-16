package Calculator;
import java.util.Scanner;
import utils.InputHelper;

public class CalculatorHandler{

    public static void start(Scanner scanner){
        boolean isExecuting = true;

        String OPTIONS = """
        =====Calculator=====
        1. Add food to list
        2. Delete from list
        3. Show list
        4. Final results
        5. Back""";

        while (isExecuting){
            System.out.println(OPTIONS);
            int option = InputHelper.getInt(
                "Enter an option: ",
                1,
                5,
                "Could not be empty",
                "Please, enter a valid number"
            );
            switch (option) {
                case 1:
                    System.out.println("Hey1");
                    break;
                case 2:
                    System.out.println("Hey2");
                    break;
                case 3:
                    System.out.println("Hey3");
                    break;
                case 4:
                    System.out.println("Hey4");
                    break;
                case 5:
                    isExecuting = false;
                    break;
            }
        }
    }
}