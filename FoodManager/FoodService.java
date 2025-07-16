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
            "Calories can't be empty",
            "Please, enter a valid number"
        );
        
        Food food = new Food(name, description, calories);
        foodList.add(food);
        new FoodStorage().saveFoods(foodList);

        System.out.println("Food added!");
        System.out.println(food.toString());
    };
    public static void updateFood(Scanner scanner){

        System.out.println("Update");
    };
    public static void deleteFood(Scanner scanner){
        System.out.println("Delete");
    };
    public static void listFood(){
        System.out.println("List");
    };
}