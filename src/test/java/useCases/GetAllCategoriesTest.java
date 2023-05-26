package useCases;

import domain.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.CategoryRepository;
import repositories.interfaces.BaseCategoryRepository;
import useCases.category.GetAllCategories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllCategoriesTest {

    static BaseCategoryRepository categoryRepository;
    static GetAllCategories getAllCategories;

    @BeforeAll
    static void setup() {
        categoryRepository = mock(CategoryRepository.class);
        getAllCategories = new GetAllCategories(categoryRepository);
    }

    @Test
    @DisplayName("When use case is executed one or more valid modules will be returned")
    void test01() {
        List<Category> list = new ArrayList<>();
        final UUID DUMMY_UUID = UUID.fromString("0000-00-00-00-000000");
        Category category = new Category(DUMMY_UUID,"New Cat1");
        list.add(category);

        when(categoryRepository.getAll()).thenReturn(list);

        assertEquals(categoryRepository.getAll().size(),1);
        assertEquals(categoryRepository.getAll().get(0),category);
    }
}
