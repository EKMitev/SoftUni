package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {

    private List<Component> components;
    private List<Peripheral> peripherals;

    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return peripherals;
    }

    @Override
    public void addComponent(Component component) {
        if (containsComponent(component, components)) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT
                    , component.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }
        components.add(component);
    }

    private boolean containsComponent(Component component, List<Component> components) {
        String name = component.getClass().getSimpleName();
        return components.stream().anyMatch(c -> c.getClass().getSimpleName().equals(name));
    }

    @Override
    public Component removeComponent(String componentType) {
        Component component = this.components.stream()
                .filter(c -> c.getClass().getSimpleName().equals(componentType))
                .findFirst().orElse(null);
        if (component == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT
                    , componentType, this.getClass().getSimpleName(), this.getId()));
        }
        this.components.remove(component);
        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (containsPeripheral(peripheral, peripherals)) {
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL
                    , peripheral.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }
        peripherals.add(peripheral);
    }

    private boolean containsPeripheral(Peripheral peripheral, List<Peripheral> peripherals) {
        String name = peripheral.getClass().getSimpleName();
        return peripherals.stream().anyMatch(p -> p.getClass().getSimpleName().equals(name));
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheral = this.peripherals.stream()
                .filter(p -> p.getClass().getSimpleName().equals(peripheralType))
                .findFirst().orElse(null);
        if (peripheral == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL
                    , peripheralType, this.getClass().getSimpleName(), this.getId()));
        }
        this.peripherals.remove(peripheral);
        return peripheral;
    }

    @Override
    public double getOverallPerformance() {
        return components.stream()
                .mapToDouble(Component::getOverallPerformance).average().orElse(0)
                + super.getOverallPerformance();
    }

    @Override
    public double getPrice() {
        return super.getPrice()
                + components.stream().mapToDouble(Component::getPrice).sum()
                + peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
    }

    @Override
    public String toString() {
        //Overall Performance: {overall performance}. Price: {price} - {product type}: {manufacturer} {model} (Id: {id})"
        //" Components ({components count}):"
        //"  {component one}"
        //"  {component two}"
        //"  {component n}"
        //" Peripherals ({peripherals count}); Average Overall Performance ({average overall performance peripherals}):"
        //"  {peripheral one}"
        //"  {peripheral two}"
        //"  {peripheral n}"

        double average = peripherals.stream()
                .mapToDouble(Peripheral::getOverallPerformance).average().orElse(0);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(PRODUCT_TO_STRING
                        , getOverallPerformance(), getPrice(), this.getClass().getSimpleName(), getManufacturer(), getModel(), getId()))
                .append(System.lineSeparator())
                .append(String.format(" " + COMPUTER_COMPONENTS_TO_STRING, components.size()))
                .append(System.lineSeparator());

        components.forEach(c -> sb.append("  ").append(c).append(System.lineSeparator()));

        sb.append(String.format(" " + COMPUTER_PERIPHERALS_TO_STRING, peripherals.size(), average))
                .append(System.lineSeparator());

        peripherals.forEach(p -> sb.append("  ").append(p).append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
