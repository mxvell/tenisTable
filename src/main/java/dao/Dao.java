package dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    T create (T t);

    Optional<T> getById(int id);
    List<T> getAll();
}
