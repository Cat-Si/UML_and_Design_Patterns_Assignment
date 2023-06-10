package domain;

import domain.enumerators.SystemRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ManagerTests {

    //id, forename, surname, username, password, systemRole


    final UUID DUMMY_MANAGER_ID = UUID.fromString("0000-00-00-00-000000");
    final static String VALID_FIRSTNAME = "First1" ;
    final static String VALID_SURNAME = "Sur1";

    final static String VALID_USERNAME = "Username";
    final static String VALID_PASSWORD = "Password";

    final static SystemRole VALID_SYSTEMROLE = SystemRole.MANAGER;



/*    final UUID DUMMY_STAFF_ID = UUID.fromString("0000-00-00-00-000001");
    final static SystemRole VALID_STAFF_SYSTEMROLE = SystemRole.STAFF_USER;
    final  Manager VALID_STAFF_MANAGER = this.VALID_STAFF_MANAGER; //not getting the syntax
    final static JobRole VALID_JOBROLE = JobRole.MIDLEVEL_DEVELOPER;
    static final List<StaffUser> dummyList = new ArrayList<>();*/


    @Test
    @DisplayName("When null id parameter is passed then IllegalArgumentException is thrown")
    void test01() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Manager(null, VALID_FIRSTNAME,VALID_SURNAME,VALID_USERNAME,VALID_PASSWORD,VALID_SYSTEMROLE);
        });
    }

    @Test
    @DisplayName("When null firstname parameter is passed then IllegalArgumentException is thrown")
    void test02() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Manager(DUMMY_MANAGER_ID, null ,VALID_SURNAME,VALID_USERNAME,VALID_PASSWORD,VALID_SYSTEMROLE);
        });
    }

    @Test
    @DisplayName("When null surname parameter is passed then IllegalArgumentException is thrown")
    void test03() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME,null,VALID_USERNAME,VALID_PASSWORD,VALID_SYSTEMROLE);
        });
    }

    @Test
    @DisplayName("When null username parameter is passed then IllegalArgumentException is thrown")
    void test04() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME,VALID_SURNAME,null ,VALID_PASSWORD,VALID_SYSTEMROLE);
        });
    }

    @Test
    @DisplayName("When null password parameter is passed then IllegalArgumentException is thrown")
    void test05() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME,VALID_SURNAME,VALID_USERNAME,null,VALID_SYSTEMROLE);
        });
    }

    @Test
    @DisplayName("When null system role parameter is passed then IllegalArgumentException is thrown")
    void test06() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME,VALID_SURNAME,VALID_USERNAME,VALID_PASSWORD,null);
        });
    }



    //validator blanks

    @Test
    @DisplayName("When blank first name value is passed then IllegalArgumentException is thrown")
    void test07() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager(DUMMY_MANAGER_ID, " ",VALID_SURNAME,VALID_USERNAME,VALID_PASSWORD,VALID_SYSTEMROLE);
        });
        assertEquals(exception.getMessage(), "firstname must not be blank");
    }

    @Test
    @DisplayName("When blank last name value is passed then IllegalArgumentException is thrown")
    void test08() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME," ",VALID_USERNAME,VALID_PASSWORD,VALID_SYSTEMROLE);
        });
        assertEquals(exception.getMessage(), "surname must not be blank");
    }

    @Test
    @DisplayName("When blank username value is passed then IllegalArgumentException is thrown")
    void test09() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME,VALID_SURNAME, " ",VALID_PASSWORD,VALID_SYSTEMROLE);
        });
        assertEquals(exception.getMessage(), "username must not be empty");
    }

    @Test
    @DisplayName("When blank password value is passed then IllegalArgumentException is thrown")
    void test10() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME,VALID_SURNAME,VALID_USERNAME," ",VALID_SYSTEMROLE);
        });
        assertEquals(exception.getMessage(), "password must not be empty");
    }


    // non-null test

    @Test
    @DisplayName("When non-null value is passed then IllegalArgumentException is not thrown")
    void test11() {
        assertDoesNotThrow( () ->new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME,VALID_SURNAME,VALID_USERNAME,VALID_PASSWORD,VALID_SYSTEMROLE));
    }


    @Test
    @DisplayName("When a Manager with valid details is assigned to the constructor and toString is called then the correct details are returned")
    void test12() {
        final String EXPECTED_STRING = String.format("%s %s (%s)",VALID_FIRSTNAME, VALID_SURNAME, VALID_SYSTEMROLE ); //Check the student class

        Manager manager = new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME,VALID_SURNAME,VALID_USERNAME,VALID_PASSWORD,VALID_SYSTEMROLE);
        assertEquals(EXPECTED_STRING, manager.toString());
    }


    //equals tests
    @Test
    @DisplayName("When two staff  objects with the same ids are compared the equals method works as expected")
    void test14() {
        Manager m1 = new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME,VALID_SURNAME,VALID_USERNAME,VALID_PASSWORD,VALID_SYSTEMROLE);
        Manager m2 = new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME,VALID_SURNAME,VALID_USERNAME,VALID_PASSWORD,VALID_SYSTEMROLE);

        assertEquals(m1,m2);//Equals method works as expected
    }

    @Test
    @DisplayName("When two staff objects with the different ids are compared the equals method works as expected")
    void test15() {
        final UUID DUMMY_MANAGER_ID2 = UUID.fromString("0000-00-00-00-000001");

        Manager m1 = new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME,VALID_SURNAME,VALID_USERNAME,VALID_PASSWORD,VALID_SYSTEMROLE);
        Manager m2 = new Manager(DUMMY_MANAGER_ID2, VALID_FIRSTNAME,VALID_SURNAME,VALID_USERNAME,VALID_PASSWORD,VALID_SYSTEMROLE);

        assertNotEquals(m1,m2);//Equals method works as expected
    }

/*    @Test //Identify the logic
    @DisplayName("When a valid new StaffUser is added to an existing Manager object then the number of Staff assigned to the Manager will increase by 1")
    void test02() {
        Manager m = new Manager(DUMMY_MANAGER_ID,"First1", "Sur1", "UserName1", "Password1", VALID_SYSTEMROLE);
        StaffUser s1 = new StaffUser(DUMMY_STAFF_ID,"first2","surname2", "UserName2", "Password2", VALID_STAFF_SYSTEMROLE, VALID_JOBROLE, VALID_STAFF_MANAGER);
        final List<StaffUser> dummyList = new ArrayList<>();
        dummyList.add(s1);
        assertTrue(dummyList.get().size()==1);
    }*/

}
