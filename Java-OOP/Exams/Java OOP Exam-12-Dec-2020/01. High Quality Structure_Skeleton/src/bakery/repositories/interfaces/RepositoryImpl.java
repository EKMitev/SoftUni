package bakery.repositories.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class RepositoryImpl<T> implements Repository<T>{

    private Collection<T> models = new ArrayList<>();

    @Override
    public void add(T t) {
        models.add(t);
    }

    @Override
    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(models);
    }
}
