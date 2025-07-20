package calculator;

public class FoodEntry {
    private String name;
    private Double grams;
    private Double calories;

    // Constructor
    public FoodEntry(String name, Double grams, Double calories){
        this.name = name;
        this.grams = grams;
        this.calories = calories;
    }

    // Getters
    public String getName(){return name;}
    public Double getGrams(){return grams;}
    public Double getCalories(){return calories;}

    // Setters
    public void setName(String name){this.name = name;}
    public void setGrams(Double grams){this.grams = grams;}
    public void setCalories(Double calories){this.calories = calories;}

    @Override
    public String toString(){return String.format("%s, %.2fgr, %.2fkcal", name, grams, calories);}
}
