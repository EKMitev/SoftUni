package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission {

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        List<String> items = new ArrayList<>(planet.getItems());

        outerloop:
        for (Astronaut astronaut : astronauts) {
            for (int i = 0; i < items.size(); i++) {
                if (astronaut.canBreath()) {
                    astronaut.getBag().getItems().add(items.get(i));
                    planet.getItems().remove(items.get(i));
                    items.remove(items.get(i));
                    i--;
                    astronaut.breath();
                    if (items.isEmpty()){
                        break outerloop;
                    }
                }
            }
        }
    }
}
