package useCases;

import Exceptions.EntryAlreadyExistsException;
import domain.*;
import domain.enumerators.JobRole;
import domain.enumerators.SkillLevel;
import domain.enumerators.SystemRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repositories.UserSkillRepository;
import repositories.interfaces.BaseUserSkillRepository;
import useCases.staffSkill.AddSkillToStaff;
import useCases.utility.UUIDGenerator;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AddSkillToStaffTest {

    static BaseUserSkillRepository userSkillRepository;

    static AddSkillToStaff addSkillToStaff;

    final UUID DUMMY_STAFF_ID = UUID.fromString("0000-00-00-00-000001");
    final UUID DUMMY_MANAGER_ID = UUID.fromString("0000-00-00-00-000002");
    final UUID DUMMY_CATEGORY_ID = UUID.fromString("0000-00-00-00-000003");
    final UUID DUMMY_SKILL_ID = UUID.fromString("0000-00-00-00-000004");
    final static UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");

    final SkillLevel VALID_SKILL_LEVEL = SkillLevel.ADVANCED;
    final String VALID_NOTES = "someNotes";
    final LocalDate VALID_DATE = LocalDate.now();

    final Category VALID_CATEGORY = new Category(DUMMY_CATEGORY_ID, "category");
    final Skill VALID_SKILL = new Skill(VALID_CATEGORY, DUMMY_SKILL_ID, "skillName");
    final Manager VALID_MANAGER = new Manager(DUMMY_MANAGER_ID, "man1", "ager1", "username2", "password2", SystemRole.MANAGER);
    final StaffUser VALID_STAFF = new StaffUser(DUMMY_STAFF_ID, "staff1", "user1", "username1", "password1", SystemRole.STAFF_USER, JobRole.MIDLEVEL_DEVELOPER, VALID_MANAGER);

    // UserSkill us = new UserSkill(DUMMY_ID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);

    @BeforeAll
    static void setup() {
        userSkillRepository = mock(UserSkillRepository.class);
        addSkillToStaff = new AddSkillToStaff(userSkillRepository);
    }

    @BeforeEach
    void resetList() {
        addSkillToStaff.requestList.clear();
    }

    private void executeUseCase() throws IllegalArgumentException, EntryAlreadyExistsException {
        try (MockedStatic<UUIDGenerator> ignored = Mockito.mockStatic(UUIDGenerator.class)) {
            when(UUIDGenerator.generate()).thenReturn(DUMMY_ID);//Called by execute method

            addSkillToStaff.execute();
        }
    }

    @Test
    @DisplayName("When use case is executed with valid skill and staff details then a new skill is added to the staff")
    void test01() {;
        UserSkill us = new UserSkill(DUMMY_ID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);

        addSkillToStaff.requestList.add(VALID_STAFF);
        addSkillToStaff.requestList.add(VALID_SKILL);
        addSkillToStaff.requestList.add(VALID_SKILL_LEVEL);
        addSkillToStaff.requestList.add(VALID_DATE);
        addSkillToStaff.requestList.add(VALID_NOTES);

        assertDoesNotThrow( () ->executeUseCase());
    }


    @Test
    @DisplayName("When use case is executed with an existing skill details then an EntryAlreadyExistsException is thrown")
    void test02() throws EntryAlreadyExistsException {
        final UUID EXISTING_ID = UUID.fromString("0000-00-00-00-000000");
        UserSkill us = new UserSkill(EXISTING_ID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);


        doThrow(new EntryAlreadyExistsException("User Skill already exists")).when(userSkillRepository).add(EXISTING_ID,VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);

        addSkillToStaff.requestList.add(VALID_STAFF);
        addSkillToStaff.requestList.add(VALID_SKILL);
        addSkillToStaff.requestList.add(VALID_SKILL_LEVEL);
        addSkillToStaff.requestList.add(VALID_DATE);
        addSkillToStaff.requestList.add(VALID_NOTES);

        assertThrows(EntryAlreadyExistsException.class, () -> {
            executeUseCase();
        });
    }
}

