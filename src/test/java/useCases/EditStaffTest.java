package useCases;

import Exceptions.EntryAlreadyExistsException;
import domain.Manager;
import domain.StaffUser;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.StaffUserRepository;
import repositories.interfaces.BaseStaffUserRepository;
import useCases.staff.EditStaff;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EditStaffTest {

    static BaseStaffUserRepository staffUserRepository;

    static EditStaff editStaff;

    final UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");
    final UUID DUMMY_MANAGER_ID = UUID.fromString("0000-00-00-00-000001");
    final static String VALID_FIRSTNAME = "First1";
    final static String VALID_SURNAME = "Sur1";

    final static String VALID_USERNAME = "Username";
    final static String VALID_PASSWORD = "Password";
    final static SystemRole VALID_SYSTEMROLE = SystemRole.STAFF_USER;
    final static JobRole VALID_JOBROLE = JobRole.MIDLEVEL_DEVELOPER;
    final Manager VALID_MANAGER = new Manager(DUMMY_MANAGER_ID, "First1", "Sur1", "UserName1", "Password1", VALID_SYSTEMROLE);

    //new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER)


    @BeforeAll
    static void setup() {
        staffUserRepository = mock(StaffUserRepository.class);
        editStaff = new EditStaff(staffUserRepository);
    }

    @Test
    @DisplayName("When use case is executed with a valid staff details then it is edited")
    void test01() {
        final UUID EXISTING_UUID = UUID.fromString("0000-00-00-00-000000");
        final String NEW_NAME = "Valid first";
        StaffUser staff = new StaffUser(EXISTING_UUID, NEW_NAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);


        editStaff.add(EXISTING_UUID);
        editStaff.add(NEW_NAME);
        editStaff.add(VALID_SURNAME);
        editStaff.add(VALID_USERNAME);
        editStaff.add(VALID_PASSWORD);
        editStaff.add(VALID_SYSTEMROLE);
        editStaff.add(VALID_JOBROLE);
        editStaff.add(VALID_MANAGER);
        editStaff.execute();
        verify(staffUserRepository).edit(staff); //check method was called with module det
    }

    @Test
    @DisplayName("When use case is executed with a invalid staff then an IllegalArgumentException is thrown")
    void test02() throws IllegalArgumentException {

        StaffUser staff = new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);


        doThrow(new IllegalArgumentException("skill already exists")).when(staffUserRepository).edit(staff);

        assertThrows(IllegalArgumentException.class, () -> { //Module equals method compares on name only
            final String NEW_NAME = "Valid first";
            editStaff.add(DUMMY_ID);
            editStaff.add(NEW_NAME);
            editStaff.add(VALID_SURNAME);
            editStaff.add(VALID_USERNAME);
            editStaff.add(VALID_PASSWORD);
            editStaff.add(VALID_SYSTEMROLE);
            editStaff.add(VALID_JOBROLE);
            editStaff.add(VALID_MANAGER);
            editStaff.execute();
        });
    }

}
