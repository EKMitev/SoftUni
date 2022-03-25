package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;

public class PlanetRepository<T extends Planet> implements Repository<T> {

    private Collection<T> planets;

    public PlanetRepository() {
        this.planets = new ArrayList<>();
    }

    @Override
    public Collection<T> getModels() {
        return planets;
    }

    @Override
    public void add(T model) {
        this.planets.add(model);
    }

    @Override
    public boolean remove(T model) {
        return this.planets.remove(model);
    }

    @Override
    public T findByName(String name) {
        return this.planets.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst().orElse(null);
    }
}
