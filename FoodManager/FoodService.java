package foodmanager;


import utils.InputHelper;
import java.util.*;
import storage.FoodStorageService;
import foodmanager.Food;
import utils.LoopHelper;

public class FoodService{
    
    private final static FoodStorageService foodService = FoodStorageService.getInstance(); // Service instance for managing food storage
    private final static LinkedHashMap<Integer, Food> foodList = foodService.getFoodList(); // List of food

    private final static String STRING_EXIT_CODE = "exit";
    private final static Double DOUBLE_EXIT_CODE = -1.00;
    private final static int INT_EXIT_CODE = -1;

    /**
     * Prompts the user to add a new food and save it to the storage
     */
    public static void addFood(Scanner scanner){
        // Temporary food list 
        List<Food> temporalFood = new ArrayList<>();
        System.out.println("Enter \"exit\" to cancel at any moment");

        String name = InputHelper.getString(
            "Enter the name: ",
            false,
            "Name can't be empty"
        );
        if (name.equalsIgnoreCase(STRING_EXIT_CODE)) {return;}

        String description = InputHelper.getString(
            "Enter the description: ",
            true
        );
        if (String.valueOf(description).equalsIgnoreCase(STRING_EXIT_CODE)){return;}
        
        Double calories = InputHelper.getDouble(
            "Enter the calories per 100gr: ",
            0,
            1500,
            false, 
            "Calories can't be empty",
            "Please, enter a valid number"
        );
        if (calories == DOUBLE_EXIT_CODE) {return;}
        
        // New food instance
        Food food = new Food(name, description, calories);

        // Add the new instance to the list
        foodList.put(food.getId(), food);

        // Save the new instance to the storage
        foodService.saveFood(foodList, true);

        System.out.println("Food added!");
        System.out.println("Id, Name, Description, Calories");
        System.out.println(food.toString());
    }

    /**
     * Prompts the user to update an existing food, using its ID
     */
    public static void updateFood(Scanner scanner){

        // If list is empty prints an error
        if (foodList.isEmpty()){
            System.out.println("No food saved yet!");
            return;
        }

        // Start the loop
        LoopHelper.runLoop( () -> {

            int id = InputHelper.getInt(
                "Enter the food id (\"exit\" for go back): ",
                1,
                10000,
                "Id can't be empty",
                "Id has to be a number"
                );
            if (id == INT_EXIT_CODE){return false;}
            
            // Get the food with the provided id
            Food toUpdateFood = foodList.get(id);

            // If the food does not exists, prints an error
            if (toUpdateFood == null){
                System.out.printf("No food exists with %d ID%n", id);
                return false;
            }
            
            // Food previous state
            String foodPrevName = toUpdateFood.getName();
            String foodPrevDescription = toUpdateFood.getDesc();
            Double foodPrevCalories = toUpdateFood.getCal();

            // Format the getString with the prev status
            String nameGetString = String.format("Enter the name [%s]: ", foodPrevName);
            String descriptionGetString = String.format("Enter the description [%s]: ", foodPrevDescription);
            String caloriesGetDouble = String.format("Enter the calories [%.2f]: ", foodPrevCalories);

            String foodPrevStatus = toUpdateFood.toString();

            String newName = InputHelper.getString(
            nameGetString,
            true
            );
            if (newName.equalsIgnoreCase(STRING_EXIT_CODE)) {return false;}

            String newDescription = InputHelper.getString(
                descriptionGetString,
                true
            );
            if (STRING_EXIT_CODE.equalsIgnoreCase(newDescription)){return false;}

            Double newCalories = InputHelper.getDouble(
                caloriesGetDouble,
                0,
                1500,
                true,
                "Calories can't be empty",
                "Please, enter a valid number"
            );
            if (newCalories == DOUBLE_EXIT_CODE) {return false;}

            // Set the new values if they were provided; otherwise, keep the previous ones
            toUpdateFood.setName((newName == null ? foodPrevName : newName));
            toUpdateFood.setDesc((newDescription == null ? foodPrevDescription : newDescription));
            toUpdateFood.setCal((newCalories == null ? foodPrevCalories  : newCalories));

            System.out.printf("Previous status: %s%n", foodPrevStatus);
            System.out.printf("Current status: %s%n", toUpdateFood.toString());

            foodService.saveFood(foodList, false);
            return false;
        });
    }

    /**
     * Prompts the user to delete an existing food using its ID
     */
    public static void deleteFood(Scanner scanner){
        
        if (foodList.isEmpty()){
            System.out.println("No food saved yet!");
            return;
        }

        LoopHelper.runLoop(() -> {
            int id = InputHelper.getInt(
                "Enter the food id (\"exit\" for go back): ",
                1,
                10000,
                "Id can't be empty",
                "Id has to be a number");
                
            if (id == INT_EXIT_CODE){return false;}
            
            // Check if ID exists
            if (foodList.containsKey(id)){
                Food temporalFood = foodList.get(id);

                // Remove the food
                foodList.remove(id);
                System.out.printf("Item removed: %s%n", temporalFood.toString());
                return false;
            } else {
                System.out.printf("No food found with %d ID, please check the list%n", id);
                return true;
            }
        });
    }

    /**
     * List all the existing food items
     */
    public static void listFood(){
        if (foodList.isEmpty()){
            System.out.println("No food saved yet!");
            return;
        }
        
        System.out.println("Id, Name, Description, Calories");
        
        for(Food food : foodList.values()){
            System.out.println(food);
        }
    }
}
