package PizzaCalories;

public class Dough {

    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        if (flourType.equals("White") || flourType.equals("Wholegrain")) {
            this.flourType = flourType;
        } else throw new IllegalArgumentException("Invalid type of dough.");
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (bakingTechnique.equals("Crispy") || bakingTechnique.equals("Chewy") || bakingTechnique.equals("Homemade")) {
            this.bakingTechnique = bakingTechnique;
        } else throw new IllegalArgumentException("Invalid type of dough.");
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        double modifier = getModifier(flourType, bakingTechnique);
        return weight * 2 * modifier;
    }

    private double getModifier(String flourType, String bakingTechnique) {
        double type = 0;
        double technique = 0;
        switch (flourType) {
            case "White":
                type = 1.5;
                break;
            case "Wholegrain":
                type = 1.0;
                break;
        }

        switch (bakingTechnique) {
            case "Crispy":
                technique = 0.9;
                break;
            case "Chewy":
                technique = 1.1;
                break;
            case "Homemade":
                technique = 1;
                break;
        }
        return type * technique;
    }
}
