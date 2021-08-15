package restaurant.repositories.interfaces;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;

public class HealthFoodRepositoryImpl<T extends HealthyFood> extends RepositoryImpl<T> implements HealthFoodRepository<T> {
    @Override
    public T foodByName(String name) {
        return this.getAllEntities().stream()
                .filter(e -> e.getName().equals(name))
                .findFirst().orElse(null);
    }
}
