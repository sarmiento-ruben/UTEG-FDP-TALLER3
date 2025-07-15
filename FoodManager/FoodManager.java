package FoodManager;

import utils.InputHelper;
import java.util.Scanner;

public class FoodManager{
    public static final String OPTIONS = """
    |==== Food Manager ====|
    1. Add
    2. Delete
    3. Update
    4. List
    5. Back""";
    public static void start(Scanner scanner){
        boolean isExecuting = true;
        while (isExecuting) {
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
                    FoodService.addFood(scanner);
                    break;
                case 2:
                    FoodService.updateFood(scanner);
                    break;
                case 3:
                    FoodService.deleteFood(scanner);
                    break;
                case 4:
                    FoodService.listFood();
                    break;
                case 5:
                    isExecuting = false;
                    break;
            };
        };
    };
};