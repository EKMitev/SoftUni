package restaurant.entities.tables.interfaces;

public class InGarden extends BaseTable {
    public static final double pricePerPerson = 4.5;

    public InGarden(int number, int size) {
        super(number, size, pricePerPerson);
    }
}
