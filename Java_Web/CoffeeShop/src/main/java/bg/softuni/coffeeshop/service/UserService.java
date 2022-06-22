package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.dto.UserDTO;
import bg.softuni.coffeeshop.model.mapper.UserMapper;
import bg.softuni.coffeeshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> this.userMapper.mapFromEntity(user)
                         .setNumberOfOrders(user.getOrders().size()))
                .sorted((f, s) -> Integer.compare(s.getNumberOfOrders(), f.getNumberOfOrders()))
                .toList();
    }
}
