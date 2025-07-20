package calculator;
import java.util.Scanner;
import utils.InputHelper;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import utils.MenuHandler;
import calculator.CalculatorService;

public class CalculatorHandler{

    private static final String OPTIONS = """
        =====Calculator=====
        1. Add food to list
        2. Delete from list
        3. Show list
        4. Back""";

    public static void start(Scanner scanner){

    Map<Integer, Consumer<Scanner>> consumersMap = new HashMap<>();

    consumersMap.put(1, sc -> CalculatorService.addToList(sc)); // Add food to calculator list
    consumersMap.put(2, sc -> CalculatorService.deleteFromList(sc)); // Delete food from calculator list
    consumersMap.put(3, sc -> CalculatorService.showList()); // Show the current list and calories
    consumersMap.put(4, _sc -> {}); // Exit the program

    // Start showing menu        
    MenuHandler.show(OPTIONS, 1, 4, consumersMap, scanner);

    }
}