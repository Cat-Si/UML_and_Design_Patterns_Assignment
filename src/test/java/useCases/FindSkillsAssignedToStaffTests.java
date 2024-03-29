package useCases;

import Exceptions.EntryAlreadyExistsException;
import domain.*;
import domain.enumerators.JobRole;
import domain.enumerators.SkillLevel;
import domain.enumerators.SystemRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.UserSkillRepository;
import repositories.interfaces.BaseUserSkillRepository;
import useCases.staffSkill.EditUserSkill;
import useCases.staffSkill.FindSkillsAssignedToStaff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class FindSkillsAssignedToStaffTests {

    static BaseUserSkillRepository userSkillRepository;

    static FindSkillsAssignedToStaff findSkillsAssignedToStaff;

    final SkillLevel VALID_SKILL_LEVEL = SkillLevel.ADVANCED;
    final String VALID_NOTES = "someNotes";
    final LocalDate VALID_DATE = LocalDate.now();

    final UUID DUMMY_STAFF_ID = UUID.fromString("0000-00-00-00-000001");
    final UUID DUMMY_MANAGER_ID = UUID.fromString("0000-00-00-00-000002");
    final UUID DUMMY_CATEGORY_ID = UUID.fromString("0000-00-00-00-000003");
    final UUID DUMMY_SKILL_ID = UUID.fromString("0000-00-00-00-000004");
    final static UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");

    final Category VALID_CATEGORY = new Category(DUMMY_CATEGORY_ID, "category");
    final Skill VALID_SKILL = new Skill(VALID_CATEGORY, DUMMY_SKILL_ID, "skillName");
    final Manager VALID_MANAGER = new Manager(DUMMY_MANAGER_ID, "man1", "ager1", "username2", "password2", SystemRole.MANAGER);
    final StaffUser VALID_STAFF = new StaffUser(DUMMY_STAFF_ID, "staff1", "user1", "username1", "password1", SystemRole.STAFF_USER, JobRole.MIDLEVEL_DEVELOPER, VALID_MANAGER);

    @BeforeAll
    static void setup() {
        userSkillRepository = mock(UserSkillRepository.class);
        findSkillsAssignedToStaff = new FindSkillsAssignedToStaff(userSkillRepository);
    }

    @Test
    @DisplayName("When use case is executed with a invalid Staff then an IllegalArgumentException is thrown")
    void test01() throws IllegalArgumentException, EntryAlreadyExistsException {

        final UUID existingUUID = UUID.fromString("0000-00-00-00-000000");

        StaffUser NEW_STAFF = new StaffUser(DUMMY_STAFF_ID, "staff1", "user1", "username1", "password1", SystemRole.STAFF_USER, JobRole.MIDLEVEL_DEVELOPER, VALID_MANAGER);

        UserSkill us = new UserSkill(DUMMY_ID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);

        doThrow(new IllegalArgumentException("Staff already exists")).when(userSkillRepository).getSkillsForStaff(VALID_STAFF);

        assertThrows(IllegalArgumentException.class, () -> {
            findSkillsAssignedToStaff.requestList.add(NEW_STAFF);
            findSkillsAssignedToStaff.execute();

        });

    }
}
