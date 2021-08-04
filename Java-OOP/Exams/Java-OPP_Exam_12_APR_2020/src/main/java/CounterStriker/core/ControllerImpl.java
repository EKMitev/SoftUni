package CounterStriker.core;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.common.OutputMessages;
import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private GunRepository<Gun> guns;
    private PlayerRepository<Player> players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository<>();
        this.players = new PlayerRepository<>();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;

        if (type.equals("Pistol")) {
            gun = new Pistol(name, bulletsCount);
        } else if (type.equals("Rifle")) {
            gun = new Rifle(name, bulletsCount);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_TYPE);
        }

        this.guns.add(gun);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {

        Gun gun = this.guns.findByName(gunName);
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.GUN_CANNOT_BE_FOUND);
        }

        Player player;


        if (type.equals("Terrorist")) {
            player = new Terrorist(username, health, armor, gun);

        } else if (type.equals("CounterTerrorist")) {
            player = new CounterTerrorist(username, health, armor, gun);

        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

        this.players.add(player);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        Collection<Player> collection = players.getModels().stream().filter(Player::isAlive).collect(Collectors.toList());
        return this.field.start(collection);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        this.players
                .getModels()
                .stream()
                .sorted((f, s) -> {
                    int result = f.getClass().getSimpleName().compareTo(s.getClass().getSimpleName());
                    if (result == 0) {
                        result = Integer.compare(s.getHealth(), f.getHealth());
                    }
                    if (result == 0) {
                        result = f.getUsername().compareTo(s.getUsername());
                    }
                    return result;
                })
                .forEach(p -> sb
                        .append(p)
                        .append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
