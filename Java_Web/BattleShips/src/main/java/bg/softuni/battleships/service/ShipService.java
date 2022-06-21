package bg.softuni.battleships.service;

import bg.softuni.battleships.model.DTO.AddShipDTO;
import bg.softuni.battleships.model.DTO.FireModel;
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

import java.util.List;
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

    public boolean addShip(AddShipDTO addShipDTO) {
        Ship ship = this.shipMapper.mapFromAddShipDto(addShipDTO);

        Optional<User> user = userRepository.findByUsername(currentUser.getUsername());
        if (user.isEmpty()) {
            return false;
        }

        CategoryEnum value = CategoryEnum.values()[addShipDTO.getCategory()];
        Category category = this.categoryRepository.findByName(value).get();

        ship
                .setCategory(category)
                .setUser(user.get());

        this.shipRepository.save(ship);
        return true;
    }

    public List<ShipDTO> getUserShips() {
        User user = userRepository.findByUsername(this.currentUser.getUsername()).get();

        return user.getShips()
                .stream()
                .map(shipMapper::mapFromEntity)
                .toList();
    }

    public List<ShipDTO> getEnemyShips() {
        User user = userRepository.findByUsername(this.currentUser.getUsername()).get();

        return this.shipRepository.findAllByUserIdNot(user.getId())
                .stream()
                .map(shipMapper::mapFromEntity)
                .toList();
    }

    public List<ShipDTO> getAll() {
        return this.shipRepository.findAll()
                .stream()
                .map(shipMapper::mapFromEntity)
                .toList();
    }

    public void fire(FireModel fireModel) {
        Ship attacker = this.shipRepository.getById(fireModel.getAttackerId());
        Ship defender = this.shipRepository.getById(fireModel.getDefenderId());

        long damage = attacker.getPower();
        long health = defender.getHealth();

        defender.setHealth(health - damage);

        if (defender.getHealth() <= 0) {
            this.shipRepository.delete(defender);
        } else {
            this.shipRepository.save(defender);
        }
    }
}
