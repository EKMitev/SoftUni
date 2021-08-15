package bakery.repositories.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;

import java.util.Collection;

public class FoodRepositoryImpl<T extends BakedFood> extends RepositoryImpl<T> implements FoodRepository<T> {
    @Override
    public T getByName(String name) {
        return getAll().stream()
                .filter(d -> d.getName().equals(name))
                .findFirst().orElse(null);
    }


}
