package foodmanager;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import utils.InputHelper;
import utils.MenuHandler;

public class FoodManager{
    private static final String OPTIONS = """
    |==== Food Manager ====|
    1. Add
    2. Delete
    3. Update
    4. List
    5. Back""";

    // Option loop
    public static void start(Scanner scanner){

    Map<Integer, Consumer<Scanner>> consumersMap = new HashMap<>();

        
    consumersMap.put(1, sc -> FoodService.addFood(sc)); // FoodManager (Add, Update and delete)
    consumersMap.put(2, sc -> FoodService.deleteFood(sc)); // Calculate calories
    consumersMap.put(3, sc -> FoodService.updateFood(sc)); // Exit the program
    consumersMap.put(4, sc -> FoodService.listFood()); // Exit the program
    consumersMap.put(5, _sc -> {}); // Exit the program

    // Start showing menu        
    MenuHandler.show(OPTIONS, 1, 5, consumersMap, scanner);
        
        
    }
}