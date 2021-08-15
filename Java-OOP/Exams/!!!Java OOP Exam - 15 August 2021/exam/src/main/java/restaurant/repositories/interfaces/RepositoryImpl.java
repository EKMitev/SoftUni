package restaurant.repositories.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RepositoryImpl<T> implements Repository<T>{
    private Collection<T> entities;

    public RepositoryImpl() {
        entities = new ArrayList<>();
    }

    @Override
    public Collection<T> getAllEntities() {
        return Collections.unmodifiableCollection(entities);
    }

    @Override
    public void add(T entity) {
        entities.add(entity);
    }
}
