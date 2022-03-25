package bakery.repositories.interfaces;

import bakery.entities.tables.interfaces.Table;



public class TableRepositoryImpl<T extends Table> extends RepositoryImpl<T> implements TableRepository<T>{

    @Override
    public T getByNumber(int number) {
        return getAll().stream()
                .filter(t -> t.getTableNumber() == number)
                .findFirst().orElse(null);
    }
}
