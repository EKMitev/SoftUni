package restaurant.repositories.interfaces;

import restaurant.entities.drinks.interfaces.Beverages;

public class BeverageRepositoryImpl<T extends Beverages> extends RepositoryImpl<T> implements BeverageRepository<T> {
    @Override
    public T beverageByName(String drinkName, String drinkBrand) {
        return this.getAllEntities().stream()
                .filter(e -> e.getName().equals(drinkName))
                .filter(e -> e.getBrand().equals(drinkBrand))
                .findFirst().orElse(null);
    }
}
