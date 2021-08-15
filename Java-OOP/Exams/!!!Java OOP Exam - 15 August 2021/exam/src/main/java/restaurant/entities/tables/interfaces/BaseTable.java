package restaurant.entities.tables.interfaces;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.*;

public abstract class BaseTable implements Table {
    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.isReservedTable = false;
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
    }

    private void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        this.allPeople = numberOfPeople * pricePerPerson;
        return this.allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.isReservedTable = true;
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        return healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum()
                + beverages.stream().mapToDouble(Beverages::getPrice).sum();
    }

    @Override
    public void clear() {
        healthyFood.clear();
        beverages.clear();
        allPeople = 0;
        numberOfPeople = 0;
        isReservedTable = false;
    }

    @Override
    public String tableInformation() {
        //"Table - {table number}"
        //"Size - {table size}"
        //"Type - {table type}"
        //"All price - {price per person for the current table}"
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Table - %d", number))
                .append(System.lineSeparator())
                .append(String.format("Size - %d", size))
                .append(System.lineSeparator())
                .append(String.format("Type - %s", this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("All price - %.2f", pricePerPerson));

        return sb.toString().trim();
    }
}
