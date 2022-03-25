package restaurant.repositories.interfaces;

import restaurant.entities.tables.interfaces.Table;

public class TableRepositoryImpl<T extends Table> extends RepositoryImpl<T> implements TableRepository<T> {
    @Override
    public T byNumber(int number) {
       return this.getAllEntities().stream()
                .filter(e -> e.getTableNumber() == number)
                .findFirst().orElse(null);
    }
}
