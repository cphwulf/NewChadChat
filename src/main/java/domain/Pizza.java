package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private int no;
    private static int counter = 0;
    private int pizzaID;
    private String name;
    private double price;
    private LocalDateTime time;
    private List<String> ingredients;

    public Pizza(int no, String name, double price, String... ingredients) {
        this.no = no;
        this.name = name;
        this.price = price;
        this.ingredients = new ArrayList<>();
        for(String ingredient : ingredients){
            this.ingredients.add(ingredient);
        }
    }
    public Pizza(Pizza pizza) {
        this.time = LocalDateTime.now();
        this.pizzaID = counter;
        this.no = pizza.getNo();
        this.name = pizza.getName();
        this.price = pizza.getPrice();
        this.ingredients = pizza.getIngredients();
        counter++;
    }

    public int getNo() { return no; }
    public int getCounter() { return  pizzaID;}
    public String getName() { return name; }
    public double getPrice() { return price; }
    public List<String> getIngredients() { return ingredients; }

    @Override
    public String toString() {
        String output = String.format("%02d %-15s kr. %5.2f", no, name, price);
        if(ingredients.isEmpty()) return output;

        output += "\n  " + ingredients.get(0);
        for (int i = 1; i < ingredients.size(); i++) {
            output += ", "+ingredients.get(i);

        }
        output += "\n";
        return output;
    }
}

