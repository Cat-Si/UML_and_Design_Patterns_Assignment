package repositories.interfaces;

import Exceptions.EntryAlreadyExistsException;
import domain.Category;

import java.util.List;

public interface BaseCategoryRepository {
    List<Category> getAll();

    void add(Category category) throws EntryAlreadyExistsException;

    void edit(Category category);
}

