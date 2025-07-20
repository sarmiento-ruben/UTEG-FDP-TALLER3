import utils.MenuHandler;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import foodmanager.FoodManager;
import utils.InputHelper;
import calculator.CalculatorHandler;

public class Main { 
    
    // Scanner instance used throughout the program (Not in InputHelps)
    private static final Scanner scanner = new Scanner(System.in);

    // Options to be displayed
    private static final String OPTIONS = """
        |==== Calories intake calculator ====|
        1. Manage food
        2. Calories calculator
        3. Exit""";
    public static void main(String[] args){

        // Consumers map
        Map<Integer, Consumer<Scanner>> consumersMap = new HashMap<>();
        
        consumersMap.put(1, scanner -> FoodManager.start(scanner)); // FoodManager (Add, Update and delete)
        consumersMap.put(2, scanner -> CalculatorHandler.start(scanner)); // Calculate calories
        consumersMap.put(3, _scanner -> System.out.println("Bye!")); // Exit the program

        // Start showing menu        
        MenuHandler.show(OPTIONS, 1, 3, consumersMap, scanner);
        
    }
}