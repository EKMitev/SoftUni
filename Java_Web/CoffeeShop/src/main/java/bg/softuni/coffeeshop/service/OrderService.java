package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.dto.OrderAddDTO;
import bg.softuni.coffeeshop.model.dto.OrderDTO;
import bg.softuni.coffeeshop.model.entity.Category;
import bg.softuni.coffeeshop.model.entity.Order;
import bg.softuni.coffeeshop.model.entity.OrderType;
import bg.softuni.coffeeshop.model.entity.User;
import bg.softuni.coffeeshop.model.mapper.OrderMapper;
import bg.softuni.coffeeshop.repository.CategoryRepository;
import bg.softuni.coffeeshop.repository.OrderRepository;
import bg.softuni.coffeeshop.repository.UserRepository;
import bg.softuni.coffeeshop.session.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final OrderRepository orderRepository;

    public OrderService(OrderMapper orderMapper, CategoryRepository categoryRepository, UserRepository userRepository, CurrentUser currenUser, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.currentUser = currenUser;
        this.orderRepository = orderRepository;
    }

    public void add(OrderAddDTO orderAddDTO) {
        OrderType orderType = orderAddDTO.getCategory();
        Category category = this.categoryRepository.getByName(orderType);

        Optional<User> optionalUser = this.userRepository.findByUsername(currentUser.getUsername());

        Order order = this.orderMapper.mapFromOrderAddDto(orderAddDTO)
                .setCategory(category)
                .setEmployee(optionalUser.get()); //get optional is checked in the controller

        this.orderRepository.save(order);
    }

    public List<OrderDTO> getAllOrders() {
        return this.orderRepository.findAll()
                .stream()
                .sorted((f, s) -> s.getPrice().compareTo(f.getPrice()))
                .map(this.orderMapper::mapFromEntity)
                .toList();
    }

    public int getTime() {
        if (this.orderRepository.count() == 0) {
            return 0;
        }

        return this.orderRepository.findAll()
                .stream()
                .mapToInt(order -> order.getCategory().getNeededTime())
                .sum();
    }

    public void completeOrder(long orderId) {
        Optional<Order> order = this.orderRepository.findById(orderId);

        order.ifPresent(this.orderRepository::delete);
    }
}
