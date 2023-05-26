package useCases.category.categoryFactory;

import domain.Category;

import java.util.List;

public interface UseCaseQuery {
    List<Category> execute();
}
