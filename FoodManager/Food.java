package FoodManager;

import java.util.List;

public class  Food{
    private static int nextId = getLastId();
    private int id;
    private String name;
    private String description;
    private double calories;

    public Food(String name, String description, double calories){
        this.id = ++nextId;
        this.name = name;
        this.description = description;
        this.calories = calories;
    };

    public Food(int id, String name, String description, double calories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.calories = calories;
    }

    // Getters
    public String getName(){return this.name;}
    public String getDesc(){return this.description;}
    public double getCal(){return this.calories;}
    public int getId(){return this.id;}

    // Setters
    public void setName(String name) {this.name = name;}
    public void setDesc(String description) {this.description = description;}
    public void setCal(double calories) {this.calories = calories;}

    public String toCSV(){
        return id + "," + name + "," + description + "," + calories;
    }

    public static Food fromCSV(String line){
        String[] fields = line.split(",");
        int id = Integer.parseInt(fields[0].trim());
        String name = fields[1].trim();
        String description = fields[2].trim();
        double calories = Double.parseDouble(fields[3].trim());
        return new Food(id, name, description, calories);
    }

    public String toString(){
        String foodString = String.format("""
        Name: %s
        Description: %s
        Calories per 100gr: %.2f kcal""", name, (description.trim().isEmpty() ? "Not provided" : description), calories);

        return foodString;
    }

    public static int getLastId(){
        List<Food> foodList = FoodStorage.getFoods();
        if (foodList.isEmpty()){
            return 0;
        } else {
            Food lastRecord = foodList.get(foodList.size() - 1);
            return lastRecord.getId();
        }
    }
};