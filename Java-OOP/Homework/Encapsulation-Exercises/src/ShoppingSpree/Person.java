package ShoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money){
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        if (name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }

    private void setMoney(double money) {
        if (money <= 0) throw new IllegalArgumentException("Money cannot be negative");
        this.money = money;
    }

    private void buyProduct(Product product) {
        if (checkMoney(this.money ,product.getCost())){
            this.products.add(product);
        } else throw new IllegalArgumentException(String.format("%s can't afford %s", this.name, product.getName()));
    }

    private boolean checkMoney(double money, double cost) {
        return money - cost >= 0;
    }

}
