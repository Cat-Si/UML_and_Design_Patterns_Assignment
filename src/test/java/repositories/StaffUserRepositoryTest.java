package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.Manager;
import domain.StaffUser;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import globals.InMemoryDatabase;
import globals.interfaces.DataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.interfaces.BaseManagerRepository;
import repositories.interfaces.BaseStaffUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StaffUserRepositoryTest {



    final UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");
    final UUID DUMMY_MANAGER_ID = UUID.fromString("0000-00-00-00-000000");
    final String VALID_NAME = "first name";
    static final List<StaffUser> dummyList = new ArrayList<>();
    static BaseStaffUserRepository staffUserRepository;

    final Manager VALID_MANAGER = new Manager(DUMMY_MANAGER_ID, "man1", "ager1", "username2", "password2", SystemRole.MANAGER);
    final StaffUser VALID_STAFF = new StaffUser(DUMMY_ID, VALID_NAME, "user1", "username1", "password1", SystemRole.STAFF_USER, JobRole.MIDLEVEL_DEVELOPER, VALID_MANAGER);


    @BeforeAll
    static void setup() {
        DataProvider mockDataProvider = mock(InMemoryDatabase.class);
        when(mockDataProvider.getStaffUser()).thenReturn(dummyList);
        staffUserRepository = new StaffUserRepository(mockDataProvider);
    }

    @BeforeEach
        //Empty list between tests
    void resetList(){
        dummyList.clear();
    }

    @Test
    @DisplayName("When getAll is called then a valid list is returned")
    void test01() {
        assertEquals(staffUserRepository.getAll(), dummyList);
    }

    @Test
    @DisplayName("When a valid new staff user is added then the repo will add it")
    void test02() {
        StaffUser su = VALID_STAFF;
        assertDoesNotThrow( () -> staffUserRepository.add(su));
    }

    @Test
    @DisplayName("When an existing staff user is added then the repo will throw an EntryAlreadyExistsException")
    void test03() {
        StaffUser su = VALID_STAFF;
        dummyList.add(su);

        assertThrows(EntryAlreadyExistsException.class, () -> {
            staffUserRepository.add(su);
        });
    }

    @Test
    @DisplayName("When an existing staff user is edited then the repo will update the name")
    void test04() {
        final String NEW_NAME = "new name";

        StaffUser su = VALID_STAFF;
        dummyList.add(su);

        StaffUser newStaff =  new StaffUser(DUMMY_ID, NEW_NAME, "user1", "username1", "password1", SystemRole.STAFF_USER, JobRole.MIDLEVEL_DEVELOPER, VALID_MANAGER);

        staffUserRepository.edit(newStaff);

        assertEquals(staffUserRepository.getAll().get(0).getFirstName(), NEW_NAME);
    }
}
