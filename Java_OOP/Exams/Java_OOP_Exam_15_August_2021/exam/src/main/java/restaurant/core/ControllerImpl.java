package restaurant.core;

import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.interfaces.Fresh;
import restaurant.entities.drinks.interfaces.Smoothie;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.Salad;
import restaurant.entities.healthyFoods.interfaces.VeganBiscuits;
import restaurant.entities.tables.interfaces.InGarden;
import restaurant.entities.tables.interfaces.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {


    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double total;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {

        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood food = null;
        switch (type) {
            case "Salad":
                food = new Salad(name, price);
                break;
            case "VeganBiscuits":
                food = new VeganBiscuits(name, price);
                break;
        }
        if (healthFoodRepository.getAllEntities().stream().anyMatch(e -> e.getName().equals(name))) {
            throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
        }

        if (food != null) {
            this.healthFoodRepository.add(food);
        } else {
            return null;
        }

        return String.format(FOOD_ADDED, name); // !!!!!!
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages beverage = null;
        switch (type) {
            case "Fresh":
                beverage = new Fresh(name, counter, brand);
                break;
            case "Smoothie":
                beverage = new Smoothie(name, counter, brand);
                break;
        }
        if (beverageRepository.getAllEntities().stream().anyMatch(e -> e.getBrand().equals(brand))) {
            throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
        }

        if (beverage != null) {
            this.beverageRepository.add(beverage);
        } else {
            return null;
        }

        return String.format(BEVERAGE_ADDED, beverage.getClass().getSimpleName(), brand); // !!!!!!
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = null;
        switch (type) {
            case "Indoors":
                table = new Indoors(tableNumber, capacity);
                break;
            case "InGarden":
                table = new InGarden(tableNumber, capacity);
                break;
        }
        if (tableRepository.getAllEntities().stream().anyMatch(e -> e.getTableNumber() == tableNumber)) {
            throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
        }

        if (table != null) {
            this.tableRepository.add(table);
        } else {
            return null;
        }

        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table t = tableRepository.getAllEntities().stream()
                .filter(e -> !e.isReservedTable())
                .filter(e -> e.getSize() >= numberOfPeople)
                .findFirst().orElse(null);
        if (t == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        } else {
            tableRepository.getAllEntities().stream()
                    .filter(e -> !e.isReservedTable())
                    .filter(e -> e.getSize() >= numberOfPeople)
                    .findFirst().orElse(null).reserve(numberOfPeople);
        }

        return String.format(TABLE_RESERVED, t.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        if (tableRepository.byNumber(tableNumber) == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        if (healthFoodRepository.foodByName(healthyFoodName) == null) {
            return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }

        tableRepository.byNumber(tableNumber).orderHealthy(healthFoodRepository.foodByName(healthyFoodName));
        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        if (tableRepository.byNumber(tableNumber) == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        if (beverageRepository.beverageByName(name, brand) == null) {
            return String.format(NON_EXISTENT_DRINK, name, brand);
        }

        tableRepository.byNumber(tableNumber).orderBeverages(beverageRepository.beverageByName(name, brand));
        return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        double bill = tableRepository.byNumber(tableNumber).bill() + tableRepository.byNumber(tableNumber).allPeople();
        total += bill;
        tableRepository.byNumber(tableNumber).clear();
        return String.format(BILL, tableNumber, bill);
    }


    @Override
    public String totalMoney() {
       return String.format(TOTAL_MONEY, total);
    }
}
