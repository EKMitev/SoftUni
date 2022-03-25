package bakery.core;

import bakery.common.ExceptionMessages;
import bakery.common.OutputMessages;
import bakery.core.interfaces.Controller;
import bakery.entities.*;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

import static bakery.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static bakery.common.ExceptionMessages.TABLE_EXIST;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;
    private double totalIncome;

    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.drinkRepository = new DrinkRepositoryImpl<>();
        this.foodRepository = new FoodRepositoryImpl<>();
        this.tableRepository = new TableRepositoryImpl<>();
    }


    @Override
    public String addFood(String type, String name, double price) {

        if (foodRepository.getAll().stream().filter(f -> f.getName().equals(name)).findFirst().orElse(null) != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        BakedFood food = null;
        switch (type) {
            case "Cake":
                food = new Cake(name, price);
                break;
            case "Bread":
                food = new Bread(name, price);
                break;
        }
        if (food != null) {
            foodRepository.add(food);
        }
        return String.format(FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        Drink drink = drinkRepository.getAll().stream().filter(f -> f.getName().equals(name)).findFirst().orElse(null);
        if (drink != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        switch (type) {
            case "Tea":
                drink = new Tea(name, portion, brand);
                break;
            case "Water":
                drink = new Water(name, portion, brand);
                break;
        }
        if (drink != null) {
            drinkRepository.add(drink);
        }
        return String.format(DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = tableRepository.getAll().stream().filter(t -> t.getTableNumber() == tableNumber).findFirst().orElse(null);
        if (table != null) {
            throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
        }
        switch (type) {
            case "InsideTable":
                table = new InsideTable(tableNumber, capacity);
                break;
            case "OutsideTable":
                table = new OutsideTable(tableNumber, capacity);
                break;
        }
        if (table != null) {
            tableRepository.add(table);
        }
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        Table table = this.tableRepository.getAll().stream().filter(t -> !t.isReserved()).
                filter(t -> t.getCapacity() >= numberOfPeople)
                .findFirst().orElse(null);

        String out;
        if (table == null) {
            out = String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        } else {
            this.tableRepository.getByNumber(table.getTableNumber()).reserve(numberOfPeople);
            out = String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
        }
        return out;
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
       if (tableRepository.getByNumber(tableNumber) == null){
           return String.format(WRONG_TABLE_NUMBER, tableNumber);
       }

       if (foodRepository.getByName(foodName) == null){
           return String.format(NONE_EXISTENT_FOOD, foodName);
       }

        tableRepository.getByNumber(tableNumber).orderFood(foodRepository.getByName(foodName));
       return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        if (tableRepository.getByNumber(tableNumber) == null){
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        if (drinkRepository.getByNameAndBrand(drinkName, drinkBrand) == null){
            return String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand);
        }

        tableRepository.getByNumber(tableNumber).orderDrink(drinkRepository.getByNameAndBrand(drinkName, drinkBrand));
        return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);
    }

    @Override
    public String leaveTable(int tableNumber) {
        double bill = tableRepository.getByNumber(tableNumber).getBill();
       totalIncome += bill;
        tableRepository.getByNumber(tableNumber).clear();
        //"Table: {tableNumber}"
        //"Bill: {table bill:f2}"
        return String.format(BILL, tableNumber, bill);
    }

    @Override
    public String getFreeTablesInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        tableRepository.getAll().stream()
                .filter(t -> !t.isReserved())
                .forEach(t -> {
                    stringBuilder.append(t.getFreeTableInfo())
                            .append(System.lineSeparator());
                });
        return stringBuilder.toString().trim();
    }

    @Override
    public String getTotalIncome() {
       return String.format(TOTAL_INCOME, totalIncome);
    }
}
