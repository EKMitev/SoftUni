package restaurant.entities.tables.interfaces;

public class Indoors extends BaseTable {
    public static final double pricePerPerson = 3.5;

    public Indoors(int number, int size) {
        super(number, size, pricePerPerson);
    }
}
