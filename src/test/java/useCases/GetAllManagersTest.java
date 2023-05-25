package useCases;

import domain.Category;
import domain.Manager;
import domain.enumerators.SystemRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.ManagerRepository;
import repositories.interfaces.BaseManagerRepository;
import useCases.staff.GetAllManagers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllManagersTest {

    static BaseManagerRepository managerRepository;
    static GetAllManagers getAllManagers;

    @BeforeAll
    static void setup() {
        managerRepository = mock(ManagerRepository.class);
        getAllManagers = new GetAllManagers(managerRepository);
    }

    @Test
    @DisplayName("When use case is executed one or more valid Managers will be returned")
    void test01() {
        List<Manager> list = new ArrayList<>();
        final UUID DUMMY_UUID = UUID.fromString("0000-00-00-00-000000");
        Manager manager = new Manager(DUMMY_UUID, "man1", "ager1", "username2", "password2", SystemRole.MANAGER);
        list.add(manager);

        when(managerRepository.getAll()).thenReturn(list);

        assertEquals(managerRepository.getAll().size(),1);
        assertEquals(managerRepository.getAll().get(0),manager);
    }
}

