package domain;

import domain.enumerators.SystemRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StaffUserTests {
    public class UserTests {

        //UUID id, String forename, String surname, String username, String password, SystemRole systemRole + Validator

        final UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");
        final static String VALID_FIRSTNAME = "First1" ;
        final static String VALID_SURNAME = "Sur1";

        final static String VALID_USERNAME = "Username";
        final static String VALID_PASSWORD = "Password";

        final static SystemRole VALID_SYSTEMROLE = SystemRole.valueOf("SystemRole");



        @Test
        @DisplayName("When null id parameter is passed then IllegalArgumentException is thrown")
        void test01() {
            assertThrows(IllegalArgumentException.class, () -> {
                new User(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE);
            });
        }

        @Test
        @DisplayName("When null name parameter is passed then IllegalArgumentException is thrown")
        void test02() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Module(DUMMY_ID,null);
            });
        }

        @Test
        @DisplayName("When blank name value is passed then IllegalArgumentException is thrown")
        void test03() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Module(DUMMY_ID,"");
            });
            assertEquals(exception.getMessage(), "name must not be blank");
        }

        @Test
        @DisplayName("When non-null value is passed then IllegalArgumentException is not thrown")
        void test04() {
            assertDoesNotThrow( () ->new Module(DUMMY_ID,VALID_NAME));
        }

        @Test
        @DisplayName("When two Module objects with the same name are compared the equals method works as expected")
        void test05() {
            Module m1 = new Module(DUMMY_ID,VALID_NAME);
            Module m2 = new Module(DUMMY_ID,VALID_NAME);

            assertEquals(m1,m2);//Equals method works as expected
        }

        @Test
        @DisplayName("When two Module objects with the different names are compared the equals method works as expected")
        void test06() {
            final String ANOTHER_VALID_NAME = "Something";
            Module m1 = new Module(DUMMY_ID,VALID_NAME);
            Module m2 = new Module(DUMMY_ID,ANOTHER_VALID_NAME);

            assertNotEquals(m1,m2);//Equals method works as expected
        }

        //Test the toString method
        //validate
        //toString
        //equals
    }
}
