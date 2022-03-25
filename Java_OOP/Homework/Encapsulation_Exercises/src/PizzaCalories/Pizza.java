package PizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int countOfToppings) {
        this.setName(name);
        this.setToppings(countOfToppings);
    }

    private void setName(String name) {
        if (name.trim().isEmpty() || name.length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public String getName() {
        return name;
    }

    private void setToppings(int count) {
        if (count < 0 || count > 10) throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        this.toppings = new ArrayList<>(count);
    }

    public void addTopping(Topping topping){
        if (this.toppings.size() < 10){
            this.toppings.add(topping);
        }
    }

    public double getOverallCalories(){
        return this.dough.calculateCalories() + this.toppings.stream().mapToDouble(Topping::calculateCalories).sum();
    }
}
