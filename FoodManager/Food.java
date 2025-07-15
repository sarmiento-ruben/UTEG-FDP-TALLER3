public class  Food{

    private String name;
    private String description;
    private int calories;

    public Food(String name, String description, int calories){
        this.name = name;
        this.description = description;
        this.calories = calories;
    };

    // Getters
    public String getName(){return this.name}
    public String getDesc(){return this.description}
    public int getCal(){return this.calories}

    // Setters
    public void setName(String name) {this.name = name}
    public void setDesc(String description) {this.description = description}
    public void setCal(int calories) {this.calories = calories}
};