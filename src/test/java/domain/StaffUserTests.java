package domain;

import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class StaffUserTests {


        //UUID id, String forename, String surname, String username, String password, SystemRole systemRole + Validator

        final UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");
    final UUID DUMMY_MANAGER_ID = UUID.fromString("0000-00-00-00-000001");
        final static String VALID_FIRSTNAME = "First1" ;
        final static String VALID_SURNAME = "Sur1";

        final static String VALID_USERNAME = "Username";
        final static String VALID_PASSWORD = "Password";

        final static SystemRole VALID_SYSTEMROLE = SystemRole.STAFF_USER;

    final static JobRole VALID_JOBROLE = JobRole.MIDLEVEL_DEVELOPER;

        final  Manager VALID_MANAGER = new Manager(DUMMY_MANAGER_ID,"First1", "Sur1", "UserName1", "Password1", VALID_SYSTEMROLE);

    //new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER)


    //Validator Nulls
        @Test
        @DisplayName("When null id parameter is passed then IllegalArgumentException is thrown")
        void test01() {
            assertThrows(IllegalArgumentException.class, () -> {
                new StaffUser(null, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
            });
        }

        @Test
        @DisplayName("When null firstname parameter is passed then IllegalArgumentException is thrown")
        void test02() {
            assertThrows(IllegalArgumentException.class, () -> {
                new StaffUser(DUMMY_ID, null, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
            });
        }

        @Test
        @DisplayName("When null surname parameter is passed then IllegalArgumentException is thrown")
        void test03() {
            assertThrows(IllegalArgumentException.class, () -> {
                new StaffUser(DUMMY_ID, VALID_FIRSTNAME, null, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
            });
        }

        @Test
        @DisplayName("When null username parameter is passed then IllegalArgumentException is thrown")
        void test04() {
            assertThrows(IllegalArgumentException.class, () -> {
                new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, null, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
            });
        }

        @Test
        @DisplayName("When null password parameter is passed then IllegalArgumentException is thrown")
        void test05() {
            assertThrows(IllegalArgumentException.class, () -> {
                new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, null, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
            });
        }

        @Test
        @DisplayName("When null system role parameter is passed then IllegalArgumentException is thrown")
        void test06() {
            assertThrows(IllegalArgumentException.class, () -> {
                new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, null, VALID_JOBROLE, VALID_MANAGER);
            });
        }

        @Test
        @DisplayName("When null job role parameter is passed then IllegalArgumentException is thrown")
        void test07() {
            assertThrows(IllegalArgumentException.class, () -> {
                new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, null, VALID_MANAGER);
            });
        }

        @Test
        @DisplayName("When null manager parameter is passed then IllegalArgumentException is thrown")
        void test08() {
            assertThrows(IllegalArgumentException.class, () -> {
                new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, null);
            });
        }

    //validator blanks

        @Test
        @DisplayName("When blank first name value is passed then IllegalArgumentException is thrown")
        void test09() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new StaffUser(DUMMY_ID, " ", VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
            });
            assertEquals(exception.getMessage(), "firstname must not be blank");
        }

        @Test
        @DisplayName("When blank last name value is passed then IllegalArgumentException is thrown")
        void test10() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new StaffUser(DUMMY_ID, VALID_FIRSTNAME, " ", VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
            });
            assertEquals(exception.getMessage(), "surname must not be blank");
        }

        @Test
        @DisplayName("When blank username value is passed then IllegalArgumentException is thrown")
        void test11() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, " ", VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
            });
            assertEquals(exception.getMessage(), "username must not be blank");
        }

        @Test
        @DisplayName("When blank password value is passed then IllegalArgumentException is thrown")
        void test12() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, " ", VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
            });
            assertEquals(exception.getMessage(), "password must not be blank");
        }


    // non-null test

        @Test
        @DisplayName("When non-null value is passed then IllegalArgumentException is not thrown")
        void test13() {
            assertDoesNotThrow( () ->new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER));
        }



    //equals tests
        @Test
        @DisplayName("When two staff  objects with the same ids are compared the equals method works as expected")
        void test14() {
            StaffUser s1 = new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
            StaffUser s2 = new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);

            assertEquals(s1,s2);//Equals method works as expected
        }

        @Test
        @DisplayName("When two staff objects with the different ids are compared the equals method works as expected")
        void test15() {
            final UUID DUMMY_ID2 = UUID.fromString("0000-00-00-00-000001");

            StaffUser s1 = new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
            StaffUser s2 = new StaffUser(DUMMY_ID2, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);

            assertNotEquals(s1,s2);//Equals method works as expected
        }



    //toString

        @Test
        @DisplayName("When a Staff member with valid details is assigned to the constructor and toString is called then the correct details are returned")
        void test16() {
            final String EXPECTED_STRING = String.format("%s %s (%s)",VALID_FIRSTNAME, VALID_SURNAME, VALID_JOBROLE ); //Check the student class

            StaffUser staff = new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
            assertEquals(EXPECTED_STRING, staff.toString());
        }

        //Test the toString method
        //validate
        //toString
        //equals
    //}
}
