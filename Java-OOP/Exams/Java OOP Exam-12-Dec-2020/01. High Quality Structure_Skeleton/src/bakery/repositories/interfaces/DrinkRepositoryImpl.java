package bakery.repositories.interfaces;

import bakery.entities.drinks.interfaces.Drink;

import java.util.Collection;

public class DrinkRepositoryImpl <T extends Drink> extends RepositoryImpl<T> implements DrinkRepository<T>{

    @Override
    public T getByNameAndBrand(String drinkName, String drinkBrand) {
        return getAll().stream()
                .filter(d -> (d.getName().equals(drinkName) && d.getBrand().equals(drinkBrand)))
                .findFirst().orElse(null);
    }

}
