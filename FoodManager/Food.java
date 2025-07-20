package foodmanager;

import java.util.List;
import java.io.*;
import storage.FoodStorageService;

public class  Food{
    // Track last id (Pulled from csv on class load)
    private static int lastId = getLastId();

    // Instance variables
    private int id;
    private String name;
    private String description;
    private double calories;

    // Class constructor for new food (auto-assigns ID)
    public Food(String name, String description, double calories){
        this.id = ++lastId;
        this.name = name;
        this.description = description;
        this.calories = calories;
    }

    // Class constructor when id is provided
    public Food(Integer id, String name, String description, double calories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.calories = calories;
    }

    // Getters
    public String getName(){return this.name.trim();}
    public String getDesc(){return this.description.trim();}
    public double getCal(){return this.calories;}
    public int getId(){return this.id;}

    // Setters
    public void setName(String name) {this.name = name;}
    public void setDesc(String description) {this.description = description;}
    public void setCal(double calories) {this.calories = calories;}


    @Override
    public String toString(){
        String foodString = String.format("%s, %s, %s, %.2f", id, name, (description == null ? "N/A" : description), calories);
        return foodString;
    }


    // Creates a Food object from a csv line
    public static Food fromCSV(String line){
        String[] fields = line.split(",");
        int id = Integer.parseInt(fields[0].trim());
        String name = fields[1].trim();
        String description = fields[2].trim();
        double calories = Double.parseDouble(fields[3].trim());
        return new Food(id, name, description, calories);
    }

    // Read last id from from CSV
    public static int getLastId() {
        String filePath = "data/food.csv"; 
        String lastLine = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Bucle to read to the end of the file
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }

        } catch (IOException e) {
            System.out.printf("An error has occurred: %s%n", e.getMessage());
        }
        
        // If file is empty returns 0
        if (lastLine == null || lastLine.trim().isEmpty()) {
            return 0;
        }
        
        // Separate csv line
        String[] parts = lastLine.split(",");

        try {
            // Return ID
            return Integer.parseInt(parts[0].trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format in last line.");
        }

        return 0;
    }
}