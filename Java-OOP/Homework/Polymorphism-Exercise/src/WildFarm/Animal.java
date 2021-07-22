package WildFarm;

public abstract class Animal {

    protected String animalType;
    protected String animalName;
    protected Double animalWeight;
    protected Integer foodEaten;

    protected Animal(String animalType, String animalName, Double animalWeight) {
        this.animalType = animalType;
        this.animalName = animalName;
        this.animalWeight = animalWeight;
        this.foodEaten = 0;
    }

    public abstract void makeSound();
    public abstract void eat(Food food);


}
