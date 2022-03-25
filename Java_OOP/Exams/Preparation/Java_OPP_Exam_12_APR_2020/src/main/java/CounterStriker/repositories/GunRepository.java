package CounterStriker.repositories;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;

public class GunRepository<T extends Gun> implements Repository<T> {

    Collection<T> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<T> getModels() {
        return this.models;
    }



    @Override
    public void add(T model) {
        if (model == null){
            throw new NullPointerException(ExceptionMessages.INVALID_GUN_REPOSITORY);
        }
        models.add(model);
    }

    @Override
    public boolean remove(T model) {
        return models.remove(model);
    }

    @Override
    public T findByName(String name) {
        return models.stream()
                .filter(g -> g.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
