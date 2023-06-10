package useCases;

import Exceptions.EntryAlreadyExistsException;
import domain.Manager;
import domain.StaffUser;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repositories.StaffUserRepository;
import repositories.interfaces.BaseStaffUserRepository;
import useCases.staff.AddNewStaff;
import useCases.utility.UUIDGenerator;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AddNewStaffTest {

    static BaseStaffUserRepository staffUserRepository;
    static AddNewStaff addNewStaff;

    final static UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");
    final UUID DUMMY_MANAGER_ID = UUID.fromString("0000-00-00-00-000001");
    final Manager VALID_MANAGER = new Manager(DUMMY_MANAGER_ID, "First1", "Sur1", "UserName1", "Password1", SystemRole.MANAGER);
    final static String VALID_FIRSTNAME = "First1" ;
    final static String VALID_SURNAME = "Sur1";
    final static String VALID_USERNAME = "Username";
    final static String VALID_PASSWORD = "Password";
    final static SystemRole VALID_SYSTEMROLE = SystemRole.STAFF_USER;
    final static JobRole VALID_JOBROLE = JobRole.MIDLEVEL_DEVELOPER;


    @BeforeAll
    static void setup() {
        staffUserRepository = mock(StaffUserRepository.class);
        addNewStaff = new AddNewStaff(staffUserRepository);
    }

    @BeforeEach
    void resetList() {
        addNewStaff.clear();
    }

    private void executeUseCase() throws IllegalArgumentException, EntryAlreadyExistsException {
        try (MockedStatic<UUIDGenerator> ignored = Mockito.mockStatic(UUIDGenerator.class)) {
            when(UUIDGenerator.generate()).thenReturn(DUMMY_ID);

            addNewStaff.execute();
        }
    }

    @Test
    @DisplayName("When use case is executed with valid staff details then a new staff is added")
    void test01() {
        StaffUser su = new StaffUser(DUMMY_ID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
        addNewStaff.add(VALID_FIRSTNAME);
        addNewStaff.add(VALID_SURNAME);
        addNewStaff.add(VALID_USERNAME);
        addNewStaff.add(VALID_PASSWORD);
        addNewStaff.add(VALID_SYSTEMROLE);
        addNewStaff.add(VALID_JOBROLE);
        addNewStaff.add(VALID_MANAGER);

        assertDoesNotThrow( () ->executeUseCase());
    }

    @Test
    @DisplayName("When use case is executed with invalid staff details then an IllegalArgumentException is thrown")
    void test02(){
        final String EMPTY_NAME = "";
        addNewStaff.add(EMPTY_NAME);
        addNewStaff.add(VALID_SURNAME);
        addNewStaff.add(VALID_USERNAME);
        addNewStaff.add(VALID_PASSWORD);
        addNewStaff.add(VALID_SYSTEMROLE);
        addNewStaff.add(VALID_JOBROLE);
        addNewStaff.add(VALID_MANAGER);

        assertThrows(IllegalArgumentException.class, () -> {
            executeUseCase();
        });
    }

    @Test
    @DisplayName("When use case is executed with an existing staff details then an EntryAlreadyExistsException is thrown")
    void test03() throws EntryAlreadyExistsException {
        final String EXISTING_USERNAME = "existingUserName";
        final UUID EXISTING_ID = UUID.fromString("0000-00-00-00-000000");
        StaffUser su = new StaffUser(EXISTING_ID, VALID_FIRSTNAME, VALID_SURNAME, EXISTING_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);

        doThrow(new EntryAlreadyExistsException("Staff already exists")).when(staffUserRepository).add(su);

        addNewStaff.add(VALID_FIRSTNAME);
        addNewStaff.add(VALID_SURNAME);
        addNewStaff.add(EXISTING_USERNAME);
        addNewStaff.add(VALID_PASSWORD);
        addNewStaff.add(VALID_SYSTEMROLE);
        addNewStaff.add(VALID_JOBROLE);
        addNewStaff.add(VALID_MANAGER);

        assertThrows(EntryAlreadyExistsException.class, () -> {
            executeUseCase();
        });
    }

}
