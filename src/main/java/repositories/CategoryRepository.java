package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.Category;
import globals.interfaces.DataProvider;
import repositories.interfaces.BaseCategoryRepository;

import java.util.List;

public class CategoryRepository implements BaseCategoryRepository {

    private final DataProvider IN_MEMORY_DATABASE;

    public CategoryRepository(DataProvider dataProvider){
        IN_MEMORY_DATABASE = dataProvider;
    }

    public List<Category> getAll(){
        return IN_MEMORY_DATABASE.getCategory();
    }

    public void add(Category category) throws EntryAlreadyExistsException {
        if(getAll().contains(category)){
            throw new EntryAlreadyExistsException("Category already exists");
        }
        else{
            getAll().add(category);
        }
    }

    public void edit(Category category){
        for (Category c: getAll()) {
            if (c.getId().equals(category.getId())) {
                c.setCategoryName(category.getCategoryName());
            }
        }
    }
}

