package FoodManager;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import utils.InputHelper;

public class FoodService{
    public static void addFood(Scanner scanner){
        List<Food> foodList = new ArrayList<>();

        String name = InputHelper.getString(
            "Enter the name: ",
            false,
            "Name can't be empty"
        );
        String description = InputHelper.getString(
            "Enter the description: ",
            true
        );
        double calories = InputHelper.getDouble(
            "Enter the calories per 100gr: ",
            0,
            1500,
            false, 
            "Calories can't be empty",
            "Please, enter a valid number"
        );
        
        Food food = new Food(name, description, calories);
        foodList.add(food);
        new FoodStorage().saveFoods(foodList, true);

        System.out.println("Food added!");
        System.out.println("Id, Name, Description, Calories");
        System.out.println(food.toString());
    };

    public static void updateFood(Scanner scanner){
        boolean isExecuting = true;
        while (isExecuting) {
            int id = InputHelper.getInt(
                "Enter the food id (\"exit\" for go back): ",
                1,
                10000,
                "Id can't be empty",
                "Id has to be a number");
                
            if (id == -1){
                    isExecuting = false;
                    break;
                }
            List<Food> foodList = FoodStorage.getFoods();
            
            Food toUpdateFood = null;
            for (Food food : foodList) {
                if (food.getId() == id){
                    toUpdateFood = food;
                    break;
                };
            };
            
            if (toUpdateFood == null){
                System.out.println(String.format("No food found with %d ID, please check the list", id));
                continue;
            }

            String nameString = String.format("Enter the name [%s]: ", toUpdateFood.getName());
            String descriptionString = String.format("Enter the description [%s]: ", toUpdateFood.getDesc());
            String caloriesDouble = String.format("Enter the calories [%.2f]: ", toUpdateFood.getCal());
            
            String foodPrev = toUpdateFood.toString();

            String name = InputHelper.getString(
            nameString,
            true
            );
            String description = InputHelper.getString(
                descriptionString,
                true
            );
            Double calories = InputHelper.getDouble(
                caloriesDouble,
                0,
                1500,
                true,
                "Calories can't be empty",
                "Please, enter a valid number"
            );

            toUpdateFood.setName((name.isEmpty() ? toUpdateFood.getName() : name));
            toUpdateFood.setDesc((description.isEmpty() ? toUpdateFood.getDesc() : description));
            toUpdateFood.setCal((calories == null ? toUpdateFood.getCal() : calories));

            System.out.println(String.format("Estado anterior: %s", foodPrev));
            System.out.println(String.format("Estado actual: %s", toUpdateFood.toString()));

            FoodStorage.saveFoods(foodList, false);
            return;
        };
    };

    public static void deleteFood(Scanner scanner){
        boolean isExecuting = true;
        while (isExecuting) {
            int id = InputHelper.getInt(
                "Enter the food id (\"exit\" for go back):",
                1,
                10000,
                "Id can't be empty",
                "Id has to be a number");
                
            if (id == -1){
                    isExecuting = false;
                    break;
                }
            List<Food> foodList = FoodStorage.getFoods();
            

            for (Food food : foodList) {
                if (food.getId() == id){
                    foodList.remove(food);
                    System.out.println(String.format("The  next item was removed: %s", food.toString()));
                    FoodStorage.saveFoods(foodList, false);
                    isExecuting = false;
                    return;
                };
                
            };

            System.out.println(String.format("No food found with %d ID, please check the list", id));
            continue;
            
        };
    };
    public static void listFood(){
        List<Food> foodList = FoodStorage.getFoods();
        if (foodList.isEmpty()){
            System.out.println("No food saved yet!");
            return;
        }
        System.out.println("Id, Name, Description, Calories");
        for(Food food : foodList){
        System.out.println(food);
        };
    };

    
}