package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTests {

    final static String VALID_CATEGORY_NAME = "Category";

    final UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");


    @Test
    @DisplayName("When null id parameter is passed then IllegalArgumentException is thrown")
    void test01() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Category(null,VALID_CATEGORY_NAME);
        });
    }

    @Test
    @DisplayName("When null name parameter is passed then IllegalArgumentException is thrown")
    void test02() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Category(DUMMY_ID,null);
        });
    }

    @Test
    @DisplayName("When blank name value is passed then IllegalArgumentException is thrown")
    void test03() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(DUMMY_ID,"");
        });
        assertEquals(exception.getMessage(), "Category must not be blank");
    }

    @Test
    @DisplayName("When non-null value is passed then IllegalArgumentException is not thrown")
    void test04() {
        assertDoesNotThrow( () ->new Category(DUMMY_ID,VALID_CATEGORY_NAME));
    }

    @Test
    @DisplayName("When two Category objects with the same name are compared the equals method works as expected")
    void test05() {
        Category c1 = new Category(DUMMY_ID,VALID_CATEGORY_NAME);
        Category c2 = new Category(DUMMY_ID,VALID_CATEGORY_NAME);

        assertEquals(c1,c2);//Equals method works as expected
    }

    @Test
    @DisplayName("When two Category objects with the different names are compared the equals method works as expected")
    void test06() {
        final String ANOTHER_VALID_NAME = "Something";
        Category c1 = new Category(DUMMY_ID,VALID_CATEGORY_NAME);
        Category c2 = new Category(DUMMY_ID,ANOTHER_VALID_NAME);

        assertNotEquals(c1,c2);//Equals method works as expected
    }

    //Test the toString method
}
