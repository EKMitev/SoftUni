package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.ArrayList;
import java.util.Collection;

public class AstronautRepository<T extends Astronaut> implements Repository<T> {

    private Collection<T> astronauts;

    public AstronautRepository() {
        this.astronauts = new ArrayList<>();
    }

    @Override
    public Collection<T> getModels() {
        return astronauts;
    }

    @Override
    public void add(T model) {
        this.astronauts.add(model);
    }

    @Override
    public boolean remove(T model) {
        return this.astronauts.remove(model);
    }

    @Override
    public T findByName(String name) {
        return this.astronauts.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst().orElse(null);
    }
}
