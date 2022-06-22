package bg.softuni.coffeeshop.model.dto;

public class UserDTO {

    private String username;
    private int numberOfOrders;

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public UserDTO setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
        return this;
    }
}
