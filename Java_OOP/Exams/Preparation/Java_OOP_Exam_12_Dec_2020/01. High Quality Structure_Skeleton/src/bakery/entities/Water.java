package bakery.entities;

public class Water extends BaseDrink{
    private static final double WATER_PRICE = 1.5;

    public Water(String name, int portion, String brand) {
        super(name, portion, WATER_PRICE, brand);
    }
}
