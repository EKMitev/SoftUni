package restaurant.entities.drinks.interfaces;

public class Fresh extends BaseBeverage{
    private static final double freshPrice = 3.5;

    public Fresh(String name, int counter, String brand) {
        super(name, counter, freshPrice, brand);
    }
}
