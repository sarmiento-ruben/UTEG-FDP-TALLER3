package storage;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import foodmanager.Food;

public class FoodStorageService{

    // Singleton instance
    private static FoodStorageService instance;

    // In-memory storage of food items
    private LinkedHashMap<Integer, Food> foodList = new LinkedHashMap<>();

    // Path to csv file where data is saved
    private static final String FILE_PATH = "data/food.csv";
    private File file = new File(FILE_PATH);

    // Loads data when instance is created
    private FoodStorageService(){
        loadData();
    }

    // Get the singleton instance
    public static FoodStorageService getInstance(){
        if (instance == null){
            instance = new FoodStorageService();
        }
        return instance;
    }

    /**
     * Loads the data from the csv into the in-memory foodList map
     * Creates data folder and file if they don't exist
     */
    public void loadData(){
        // Make sure parent folder exists
        File file_parent = file.getParentFile();
        if (file_parent != null && !file_parent.exists()){
            file_parent.mkdirs();
        }

        //  Create file if it doesn't exist yet
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.printf("An error has occurred: %s%n", e.getMessage());
            }
        }

        // Clear existing food in-memory before loading
        foodList.clear();

        // Read the csv line by line
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String[] lineParts;
            String line;
                while ((line = reader.readLine()) != null){
                    try{

                        // Split each line by commas and parse each field    
                        lineParts = line.split(",");
                        Integer id = Integer.parseInt(lineParts[0]);
                        String name = lineParts[1];
                        String description = lineParts[2];
                        Double calories = Double.parseDouble(lineParts[3]);

                        // Create food object and put it in the map
                        foodList.put(id, new Food(id, name, description, calories));
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
                        // If line is corrupted or something, just skip it
                        System.out.printf("Invalid data format in line: '%s'. Skipping it. Error: %s%n", line, e.getMessage());
                    }
                }   
        } catch (IOException e){
            System.out.printf("An error has occurred: %s%n", e.getMessage());
        }
    }

    // Return the current in-memory foodList
    public LinkedHashMap<Integer, Food> getFoodList(){
        return foodList;
    }

    /**
     * Save the current foodList to the csv
     * if `appendMode` is true, adds to the file, else overwrites it
     * After saving, reloads the data to keep everything synced.
     */
    public void saveFood(LinkedHashMap<Integer, Food> foodList, boolean appendMode){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, appendMode))){
            for (Food food : foodList.values()) {
                // Writes each Food item as line using its `toString()` method
                writer.write(food.toString());
                writer.newLine();
            }       
        } catch (IOException e){
            System.out.printf("An error has occurred: %s", e.getMessage());
        }
    
    // Reload data after saving
    loadData();
    }

}