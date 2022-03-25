package CounterStriker.models.field;

import CounterStriker.common.OutputMessages;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import java.util.Collection;

public class FieldImpl implements Field {

    @Override
    public String start(Collection<Player> players) {

        int counter = 1;
        while (allTAreAlive(players)) {
            try {
                takeDmg(players, counter);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            counter++;
        }

        return players.stream()
                .filter(p -> p instanceof CounterTerrorist)
                .noneMatch(Player::isAlive)
                ? OutputMessages.TERRORIST_WINS
                : OutputMessages.COUNTER_TERRORIST_WINS;
    }

    private boolean allTAreAlive(Collection<Player> players) {
        return players.stream()
                .filter(p -> p instanceof Terrorist)
                .anyMatch(Player::isAlive)
                && players.stream()
                .filter(p -> p instanceof CounterTerrorist)
                .anyMatch(Player::isAlive);
    }



    private void takeDmg(Collection<Player> players, int counter) throws ClassNotFoundException {

        String className = counter % 2 == 0
                ? "CounterTerrorist"
                : "Terrorist";

        final Class<?> clazz = Class.forName("CounterStriker.models.players." + className);


        players
                .forEach(p -> {
                    if (p.getClass().getSimpleName().equals(clazz.getSimpleName()) && p.isAlive()) {
                        for (Player player : players) {
                            if (!player.getClass().getSimpleName().equals(clazz.getSimpleName()) && player.isAlive()){
                                player.takeDamage(p.getGun().fire());
                            }
                        }
                    }
                });
    }
}