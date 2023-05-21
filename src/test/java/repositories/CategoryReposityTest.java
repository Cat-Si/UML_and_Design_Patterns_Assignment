package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.Category;
import globals.InMemoryDatabase;
import globals.interfaces.DataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.interfaces.BaseCategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryReposityTest {

    final UUID DUMMY_CATEGORY_ID = UUID.fromString("0000-00-00-00-000000");
    final String VALID_NAME = "module name";
    static final List<Category> dummyList = new ArrayList<>();
    static BaseCategoryRepository categoryRepository;

    @BeforeAll
    static void setup() {
        DataProvider mockDataProvider = mock(InMemoryDatabase.class);
        when(mockDataProvider.getCategory()).thenReturn(dummyList);
        categoryRepository = new CategoryRepository(mockDataProvider);
    }

    @BeforeEach
        //Empty list between tests
    void resetList(){
        dummyList.clear();
    }

    @Test
    @DisplayName("When getAll is called then a valid list is returned")
    void test01() {
        assertEquals(categoryRepository.getAll(), dummyList);
    }

    @Test
    @DisplayName("When a valid new category is added then the repo will add it")
    void test02() {
        Category c = new Category(DUMMY_CATEGORY_ID, VALID_NAME);
        assertDoesNotThrow( () -> categoryRepository.add(c));
    }

    @Test
    @DisplayName("When an existing category is added then the repo will throw an EntryAlreadyExistsException")
    void test03() {
        Category c = new Category(DUMMY_CATEGORY_ID, VALID_NAME);
        dummyList.add(c);

        assertThrows(EntryAlreadyExistsException.class, () -> {
            categoryRepository.add(c);
        });
    }

    @Test
    @DisplayName("When an existing category is edited then the repo will update the name")
    void test04() {
        final String NEW_NAME = "new name";

        Category c = new Category(DUMMY_CATEGORY_ID, VALID_NAME);
        dummyList.add(c);

        Category newCategory = new Category(DUMMY_CATEGORY_ID, NEW_NAME);

        categoryRepository.edit(newCategory);

        assertEquals(categoryRepository.getAll().get(0).getCategoryName(), NEW_NAME);
    }
}
