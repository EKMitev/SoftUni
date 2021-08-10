package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;
import spaceStation.repositories.Repository;

import java.util.Collection;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Astronaut> astronauts;
    private Repository<Planet> planets;
    private int exploredPlanets;

    public ControllerImpl() {
        this.astronauts = new AstronautRepository<>();
        this.planets = new PlanetRepository<>();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;

        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        astronauts.add(astronaut);
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        for (String item : items) {
            planet.getItems().add(item);
        }

        planets.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = astronauts.findByName(astronautName);
        if (astronaut == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }

        astronauts.remove(astronaut);
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Collection<Astronaut> suitableAstronauts = this.astronauts.getModels()
                .stream()
                .filter(a -> a.getOxygen() > 60)
                .collect(Collectors.toList());

        if (suitableAstronauts.size() == 0) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        Mission mission = new MissionImpl();
        Planet planet = planets.findByName(planetName);
        mission.explore(planet, suitableAstronauts);
        exploredPlanets++;

        int deadAstronauts = 0;

        for (Astronaut astronaut : suitableAstronauts) {
            if (astronaut.getOxygen() == 0) {
                deadAstronauts++;
            }
        }
        return String.format(PLANET_EXPLORED, planetName, deadAstronauts);
    }

    @Override
    public String report() {
        //"{exploredPlanetsCount} planets were explored!"
        //"Astronauts info:"
        //"Name: {astronautName One}"
        //"Oxygen: {astronautOxygen One}"
        //"Bag items: {bagItem1, bagItem2, bagItem3, …, bagItemn \ "none"}"
        //…
        //"Name: {astronautName N}"
        //"Oxygen: {astronautOxygen N}"
        //"Bag items: {bagItem1, bagItem2, bagItem3, …, bagItemn \ "none"}"
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(REPORT_PLANET_EXPLORED, exploredPlanets))
                .append(System.lineSeparator())
                .append(REPORT_ASTRONAUT_INFO)
                .append(System.lineSeparator());

        astronauts.getModels()
                .forEach(a -> {
                    String bagItems = a.getBag().getItems().isEmpty()
                            ? "none"
                            : String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, a.getBag().getItems());

                    sb.append(String.format(REPORT_ASTRONAUT_NAME, a.getName()))
                            .append(System.lineSeparator())
                            .append(String.format(REPORT_ASTRONAUT_OXYGEN, a.getOxygen()))
                            .append(System.lineSeparator())
                            .append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, bagItems))
                            .append(System.lineSeparator());
                });
        return sb.toString().trim();
    }
}
