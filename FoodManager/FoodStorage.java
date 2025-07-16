package FoodManager;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
class FoodStorage{
    private static final String file_path = "food_data.csv";

    public static void saveFoods(List<Food> foodList){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file_path, true))){
            for (Food food: foodList){
                writer.write(food.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving foods: " + e.getMessage());
        }
    }

    public static List<Food> getFoods(){

        List<Food> foodList = new ArrayList<>();
        File file = new File(file_path);

        if (!file.exists()) return foodList;

        try(BufferedReader reader = new BufferedReader(new FileReader(file_path))){
            String line;
            while((line = reader.readLine()) != null){
                Food food = Food.fromCSV(line);
                foodList.add(food);
            }
        } catch (IOException e){
            System.out.println("Error loading foods: " + e.getMessage());
        }
        return foodList;
    }
}