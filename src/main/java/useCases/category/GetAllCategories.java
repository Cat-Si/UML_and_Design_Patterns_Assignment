package useCases.category;

import domain.Category;
import repositories.interfaces.BaseCategoryRepository;
import useCases.BaseUseCase;
import useCases.category.categoryFactory.UseCaseQuery;

import java.util.List;

public class GetAllCategories extends BaseUseCase implements UseCaseQuery {
    private final BaseCategoryRepository CATEGORY_REPOSITORY;

    public GetAllCategories(BaseCategoryRepository categoryRepository) {
        CATEGORY_REPOSITORY = categoryRepository;
    }

    public List<Category> execute() {
        return CATEGORY_REPOSITORY.getAll();
    }
}
