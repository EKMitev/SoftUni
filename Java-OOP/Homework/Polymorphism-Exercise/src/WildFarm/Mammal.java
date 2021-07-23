package WildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal{

    protected String livingRegion;

    public Mammal(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight);
        this.livingRegion = livingRegion;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return String.format("%s[%s, %s, %s, %d]",
                this.animalType,
                this.animalName,
                decimalFormat.format(this.animalWeight),
                this.livingRegion,
                this.foodEaten).trim();
    }
}
