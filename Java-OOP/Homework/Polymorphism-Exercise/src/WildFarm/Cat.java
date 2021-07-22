package WildFarm;

import java.text.DecimalFormat;

public class Cat extends Felime {

    private String breed;

    protected Cat(String animalType, String animalName, Double animalWeight, String livingRegion, String breed) {
        super(animalType, animalName, animalWeight, livingRegion);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eat(Food food) {
        this.foodEaten += food.quantity;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("##.#");
        return String.format("%s[%s, %s, %s, %s, %d]", this.animalType, this.animalName, this.breed, decimalFormat.format(this.animalWeight), this.livingRegion, this.foodEaten).trim();
    }
}
