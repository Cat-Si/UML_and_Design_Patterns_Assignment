package useCases.category.categoryFactory;

import globals.Ioc_Container;
import useCases.category.GetAllCategories;


public class CategoryFactory {
    public enum CommandType{view}

    public static UseCaseQuery createQuery (CommandType commandType) {
        switch (commandType) {
            case view:
                return new GetAllCategories(Ioc_Container.getCategoryRepository());
            default:
                throw new IllegalArgumentException();
        }
    }
}
