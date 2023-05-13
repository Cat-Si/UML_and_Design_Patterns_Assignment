package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FullNameTests {
    final static String VALID_FIRST_NAME = "Firstname";
    final static String VALID_SURNAME = "Surname";
    final static String BLANK_FIELD = "";

    @Test
    @DisplayName("When a valid FullName object is created and toString is called then full name is returned")
    void test01() {
        FullName fullName = new FullName(VALID_FIRST_NAME, VALID_SURNAME);

        final String EXPECTED_STRING = String.format("%s %s", VALID_FIRST_NAME,VALID_SURNAME); ///Check the FullName class

        assertEquals(fullName.toString(), EXPECTED_STRING);
    }

    @Test
    @DisplayName("When a blank first name is passed to the constructor then an IllegalArgumentException is thrown")
    void test02() {
        assertThrows(IllegalArgumentException.class, () -> {
            FullName fullName = new FullName(BLANK_FIELD, VALID_SURNAME);
        });
    }

    @Test
    @DisplayName("When a blank surname is passed to the constructor then an IllegalArgumentException is thrown")
    void test03() {
        assertThrows(IllegalArgumentException.class, () -> {
            FullName fullName = new FullName(VALID_FIRST_NAME, BLANK_FIELD);
        });
    }

    @Test
    @DisplayName("When a null first name is passed to the constructor then an exception is thrown")
    void test04() {
        assertThrows(IllegalArgumentException.class, () -> {
            FullName fullName = new FullName(null, VALID_SURNAME);
        });
    }

    @Test
    @DisplayName("When a null surname is passed to the constructor then an exception is thrown")
    void test05() {
        assertThrows(IllegalArgumentException.class, () -> {
            FullName fullName = new FullName(VALID_FIRST_NAME, null);
        });
    }
}