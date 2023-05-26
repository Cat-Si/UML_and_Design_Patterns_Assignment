package useCases;

import domain.Manager;
import domain.StaffUser;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.StaffUserRepository;
import repositories.interfaces.BaseStaffUserRepository;
import useCases.staff.GetAllStaff;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllStaffTest {
    static BaseStaffUserRepository staffUserRepository;
    static GetAllStaff getAllStaff;

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
        getAllStaff = new GetAllStaff(staffUserRepository);
    }

    @Test
    @DisplayName("When use case is executed one or more valid Managers will be returned")
    void test01() {
        List<StaffUser> list = new ArrayList<>();
        final UUID DUMMY_UUID = UUID.fromString("0000-00-00-00-000000");
        StaffUser staff = new StaffUser(DUMMY_UUID, VALID_FIRSTNAME, VALID_SURNAME, VALID_USERNAME, VALID_PASSWORD, VALID_SYSTEMROLE, VALID_JOBROLE, VALID_MANAGER);
        list.add(staff);

        when(staffUserRepository.getAll()).thenReturn(list);

        assertEquals(staffUserRepository.getAll().size(),1);
        assertEquals(staffUserRepository.getAll().get(0),staff);
    }
}
