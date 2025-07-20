package calculator;
import java.util.*;
import foodmanager.Food;
import storage.FoodStorageService;
import utils.InputHelper;
import utils.LoopHelper;
import calculator.FoodEntry;

public class CalculatorService{
    
    // Singleton instance to access to stored food data
    public static FoodStorageService foodService = FoodStorageService.getInstance();
    // Map of all food items loaded from storage
    public static LinkedHashMap<Integer, Food> foodList = foodService.getFoodList();
    // Map to keep track of foods added by user with grams, to calculated total calories
    private static LinkedHashMap<Integer, FoodEntry> foodCounterList = new LinkedHashMap<>();

    // Constants 
    private final static String STRING_EXIT_CODE = "exit";
    private final static Double DOUBLE_EXIT_CODE = -1.00;
    private final static int INT_EXIT_CODE = -1;

    /**
     * Add food items with specified grams to user's list
     */
    public static void addToList(Scanner scanner){
        // Check if any food is stored yet!
        if (foodList.isEmpty()){
            System.out.println("No food saved yet!");
            return;
        }

        // Start loop
        LoopHelper.runLoop(() -> {
            int id = InputHelper.getInt(
                "Enter the food id (\"exit\" for go back): ",
                1,
                10000,
                "Id can't be empty",
                "Id has to be a number"
                );
            
            if (id == INT_EXIT_CODE){return false;}

            // Get food from food list
            Food food = foodList.get(id);

            // If the id was not found, print error
            if (food == null){
                System.out.printf("No food exists with %dID%n", id);
                return true;

            } else {

                String foodName = food.getName();
                Double foodKcal = food.getCal();
                
                Double grams = InputHelper.getDouble(
                String.format("Enter quantity of %s in grams (%.2f kcal per 100gr): ", foodName, foodKcal),
                1,
                10000,
                false, 
                "Grams can't be empty",
                "Please, enter a valid number"
                );

                // Calculate calories for the entered grams
                Double finalCalories = foodKcal * (grams/100);

                // Check if food is already added in foodCounterList
                FoodEntry foodIfExists = foodCounterList.get(id);

                // If exists, sum previous calories and grams, else start fresh
                Double foodCalories = finalCalories + (foodIfExists == null ? 0: foodIfExists.getCalories());
                Double foodGrams = grams + (foodIfExists == null ? 0: foodIfExists.getGrams());
                
                // Update existing or add new entry
                if (foodIfExists != null){
                    foodIfExists.setCalories(foodCalories);
                    foodIfExists.setGrams(foodGrams);
                } else {
                    foodIfExists = new FoodEntry(foodName, foodGrams, foodCalories);
                    foodCounterList.put(id, foodIfExists);
                }

                System.out.printf("Food in list: %d, %s%n", id, foodIfExists.toString());
                return false;
            }
        });
    }
    /**
     * Deleted from entries from the added list by ID
     */
    public static void deleteFromList(Scanner scanner){
        
        if (foodCounterList == null || foodCounterList.isEmpty()) {
            System.out.println("No food saved yet!");
            return;
            }

        LoopHelper.runLoop(() -> {
            Integer id = InputHelper.getInt(
                "Enter the food id (\"exit\" for go back): ",
                1,
                10000,
                "Id can't be empty",
                "Id has to be a number"
                );
            
            if (id == DOUBLE_EXIT_CODE){
                return false;
            }
            
            if (!foodCounterList.containsKey(id)){
                System.out.printf("The food with %dID has not been added to list%n", id);
                return true;

            } else {
                // Remove the food from list
                FoodEntry temporalFoodInfo = foodCounterList.get(id);
                foodCounterList.remove(id);

                System.out.printf("Removed: %s%n", temporalFoodInfo.toString());
                return false;
            }
            
        });
    }

    /**
     * Prints the current food list with total grams and calories
     */
    public static void showList(){

        if (foodCounterList.isEmpty()) {
            System.out.println("No food saved yet!");
        } else {
            System.out.println("====Added food====");
            System.out.println("Id,Name, Grams, Calories");
            
            // Sum total calories
            Double totalKcal = foodCounterList.values().stream().mapToDouble(values -> values.getCalories()).sum(); 

            // Print each foodEntry
            foodCounterList.forEach((id, values) -> {
                System.out.printf("%d, %s%n", id, values.toString());
            });

            // Print the total calories
            System.out.printf("Total calories: %.2fkcal%n", totalKcal);
        }
    }

}