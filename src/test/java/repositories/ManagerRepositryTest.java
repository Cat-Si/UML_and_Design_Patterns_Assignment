package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.Manager;
import domain.enumerators.SystemRole;
import globals.InMemoryDatabase;
import globals.interfaces.DataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.interfaces.BaseManagerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ManagerRepositryTest {
    final UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");
    final String VALID_NAME = "first name";
    static final List<Manager> dummyList = new ArrayList<>();
    static  BaseManagerRepository managerRepository;

    final Manager manager = new Manager(DUMMY_ID, VALID_NAME, "ager1", "username2", "password2", SystemRole.MANAGER);
    @BeforeAll
    static void setup() {
        DataProvider mockDataProvider = mock(InMemoryDatabase.class);
        when(mockDataProvider.getManager()).thenReturn(dummyList);
        managerRepository = new ManagerRepository(mockDataProvider);
    }

    @BeforeEach
        //Empty list between tests
    void resetList(){
        dummyList.clear();
    }

    @Test
    @DisplayName("When getAll is called then a valid list is returned")
    void test01() {
        assertEquals(managerRepository.getAll(), dummyList);
    }

    @Test
    @DisplayName("When a valid new manager is added then the repo will add it")
    void test02() {
        Manager m = manager;
        assertDoesNotThrow( () -> managerRepository.add(m));
    }

    @Test
    @DisplayName("When an existing manager is added then the repo will throw an EntryAlreadyExistsException")
    void test03() {
        Manager m = manager;;
        dummyList.add(m);

        assertThrows(EntryAlreadyExistsException.class, () -> {
            managerRepository.add(m);
        });
    }

    @Test
    @DisplayName("When an existing Manager is edited then the repo will update the name")
    void test04() {
        final String NEW_NAME = "new name";

        Manager m = manager;;
        dummyList.add(m);

        Manager newManager =  new Manager(DUMMY_ID, NEW_NAME, "ager1", "username2", "password2", SystemRole.MANAGER);

        managerRepository.edit(newManager);

        assertEquals(managerRepository.getAll().get(0).getFirstName(), NEW_NAME);
    }
}
