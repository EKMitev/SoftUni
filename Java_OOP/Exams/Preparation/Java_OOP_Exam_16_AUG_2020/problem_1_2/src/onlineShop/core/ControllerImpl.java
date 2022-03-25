package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {

    private List<Computer> computers;
    private List<Component> components;
    private List<Peripheral> peripherals;

    public ControllerImpl() {
        this.computers = new ArrayList<>();
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computer;

        if (computerType.equals("DesktopComputer")) {
            computer = new DesktopComputer(id, manufacturer, model, price);
        } else if (computerType.equals("Laptop")) {
            computer = new Laptop(id, manufacturer, model, price);
        } else {
            throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }

        if (listContainsPC(computer, computers)) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }

        computers.add(computer);
        return String.format(ADDED_COMPUTER, computer.getId());
    }

    private boolean listContainsPC(Computer computer1, List<Computer> computers) {
        return computers.stream().anyMatch(c -> c.getId() == computer1.getId());
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        Computer computer = getComputerByID(computerId);

        Peripheral peripheral;
        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }
        boolean containsComponent = computer.getPeripherals().stream().anyMatch(p -> id == p.getId());
        if (containsComponent) {
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }

        computer.addPeripheral(peripheral);
        peripherals.add(peripheral);
        return String.format(ADDED_PERIPHERAL, peripheral.getClass().getSimpleName(), peripheral.getId(), computer.getId());
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        Computer computer = getComputerByID(computerId);
        Peripheral peripheral = computer.removePeripheral(peripheralType);
        peripherals.remove(peripheral);

        return String.format(REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        Computer computer = getComputerByID(computerId);

        Component component;
        switch (componentType) {
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }
        boolean containsComponent = computer.getComponents().stream().anyMatch(c -> id == c.getId());
        if (containsComponent) {
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }

        computer.addComponent(component);
        components.add(component);
        return String.format(ADDED_COMPONENT, component.getClass().getSimpleName(), component.getId(), computer.getId());
    }

    private Computer getComputerByID(int computerId) {
        Computer computer = computers.stream().filter(c -> computerId == c.getId()).findFirst().orElse(null);
        if (computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        return computer;
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        Computer computer = getComputerByID(computerId);
        Component component = computer.removeComponent(componentType);
        components.remove(component);

        return String.format(REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        Computer computer = getComputerByID(id);
        computers.remove(computer);
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        Computer computer = computers.stream()
                .filter(c -> c.getPrice() <= budget)
                .max(Comparator.comparingDouble(Product::getOverallPerformance)).orElse(null);
        if (computer == null){
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }
        computers.remove(computer);
        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
        Computer computer = getComputerByID(id);
        return computer.toString();
    }
}
