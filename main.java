import java.util.Scanner;
import FoodManager.FoodManager;
import utils.InputHelper;

public class Main { 
    public static final String OPTIONS = """
    |==== Calories intake calculator ====|
    1. Manage food
    2. Calories calculator
    3. Exit""";


    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args){
        boolean isExecuting = true;
        while (isExecuting) {
                System.out.println(OPTIONS);
                int option = InputHelper.getInt(
                    "Enter an option: ",
                    1,
                    3,
                    "Could not be empty",
                    "Please, enter a valid number"
                );
                switch (option) {
                    case 1:
                        FoodManager.start(scanner);
                        break;
                    case 2:
                        System.out.println("Calculate calories");
                        break;
                    case 3:
                        isExecuting = false;
                        break;
                    };
                }
    }
}