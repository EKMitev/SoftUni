package AnimalFarm;

public class Chicken {

    private String name;
    private int age;

    public Chicken(String name, int age) throws IllegalAccessException {
        this.setName(name);
        this.setAge(age);
    }

    private void setName(String name) throws IllegalAccessException {
        if (name.trim().isEmpty()) throw new IllegalAccessException("Name cannot be empty.");
        this.name = name;
    }

    private void setAge(int age) throws IllegalAccessException {
        if (age > 15 || age < 0) throw new IllegalAccessException("Age should be between 0 and 15.");
        this.age = age;
    }

    private double calculateProductPerDay() {
        if (this.age < 6) {
            return 2;
        } else if (this.age < 12) {
            return 1;
        }
        return 0.75;
    }

    public double productPerDay(){
        return calculateProductPerDay();
    }

    @Override
    public String toString() {
        return String.format("Chicken %s (age %d) can produce %.2f eggs per day.", this.name, this.age, this.calculateProductPerDay());
    }
}
