package domain;

import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagerTests {

    //id, forename, surname, username, password, systemRole


    final UUID DUMMY_MANAGER_ID = UUID.fromString("0000-00-00-00-000000");
    final static String VALID_FIRSTNAME = "First1" ;
    final static String VALID_SURNAME = "Sur1";

    final static String VALID_USERNAME = "Username";
    final static String VALID_PASSWORD = "Password";

    final static SystemRole VALID_SYSTEMROLE = SystemRole.valueOf("Manager");



    final UUID DUMMY_STAFF_ID = UUID.fromString("0000-00-00-00-000000");
    final static SystemRole VALID_STAFF_SYSTEMROLE = SystemRole.valueOf("Staff");
    final  Manager VALID_STAFF_MANAGER = this.VALID_STAFF_MANAGER; //not getting the syntax
    final static JobRole VALID_JOBROLE = JobRole.valueOf("JobRole");
    static final List<StaffUser> dummyList = new ArrayList<>();



    @Test
    @DisplayName("When a Manager with valid details is assigned to the constructor and toString is called then the correct details are returned")
    void test01() {
        final String EXPECTED_STRING = String.format("%s %s (%s)",VALID_FIRSTNAME, VALID_SURNAME, VALID_SYSTEMROLE ); //Check the student class

        Manager manager = new Manager(DUMMY_MANAGER_ID, VALID_FIRSTNAME,VALID_SURNAME,VALID_USERNAME,VALID_PASSWORD,VALID_SYSTEMROLE);
        assertEquals(EXPECTED_STRING, manager.toString());
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
