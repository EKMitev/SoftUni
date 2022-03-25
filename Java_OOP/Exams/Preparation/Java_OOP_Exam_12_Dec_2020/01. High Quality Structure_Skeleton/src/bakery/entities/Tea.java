package bakery.entities;

public class Tea extends BaseDrink{
    private static final double TEA_PRICE = 2.5;

    public Tea(String name, int portion, String brand) {
        super(name, portion, TEA_PRICE, brand);
    }
}
