package bg.softuni.battleships.service;

import bg.softuni.battleships.model.DTO.ShipDTO;
import bg.softuni.battleships.model.entity.Category;
import bg.softuni.battleships.model.entity.CategoryEnum;
import bg.softuni.battleships.model.entity.Ship;
import bg.softuni.battleships.model.entity.User;
import bg.softuni.battleships.model.mapper.ShipMapper;
import bg.softuni.battleships.repository.CategoryRepository;
import bg.softuni.battleships.repository.ShipRepository;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.session.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {

    private final ShipMapper shipMapper;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;

    public ShipService(ShipMapper shipMapper, CurrentUser currentUser, UserRepository userRepository, ShipRepository shipRepository, CategoryRepository categoryRepository) {
        this.shipMapper = shipMapper;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
    }

    public boolean addShip(ShipDTO shipDTO) {
        Ship ship = this.shipMapper.mapFromShipDto(shipDTO);

        Optional<User> user = userRepository.findByUsername(currentUser.getUsername());
        if (user.isEmpty()) {
            return false;
        }

        CategoryEnum value = CategoryEnum.values()[shipDTO.getCategory()];
        Category category = this.categoryRepository.findByName(value).get();

        ship
                .setCategory(category)
                .setUser(user.get());

        this.shipRepository.save(ship);
        return true;
    }
}
