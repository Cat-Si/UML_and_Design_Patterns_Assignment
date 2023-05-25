package useCases;

import domain.*;
import domain.enumerators.JobRole;
import domain.enumerators.SkillLevel;
import domain.enumerators.SystemRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.UserSkillRepository;
import repositories.interfaces.BaseUserSkillRepository;
import useCases.staffSkill.AddSkillToStaff;
import useCases.staffSkill.EditUserSkill;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EditUserSkillTest {
    static BaseUserSkillRepository userSkillRepository;

    static EditUserSkill editUserSkill;

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
        editUserSkill = new EditUserSkill(userSkillRepository);
    }

    @Test
    @DisplayName("When use case is executed with a valid module details then it is edited")
    void test01() {

        UserSkill us = new UserSkill(DUMMY_ID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);

        editUserSkill.requestList.add(DUMMY_ID);
        editUserSkill.requestList.add(VALID_STAFF);
        editUserSkill.requestList.add(VALID_SKILL);
        editUserSkill.requestList.add(VALID_SKILL_LEVEL);
        editUserSkill.requestList.add(VALID_DATE);
        editUserSkill.requestList.add(VALID_NOTES);
        editUserSkill.execute();
        verify(userSkillRepository).edit(us); //check method was called with module det
    }

    @Test
    @DisplayName("When use case is executed with a invalid UserSkill then an IllegalArgumentException is thrown")
    void test02() throws IllegalArgumentException {
        final UUID existingUUID = UUID.fromString("0000-00-00-00-000000");
        final UUID differentUUID = UUID.fromString("0000-00-00-00-000001");

        final String NEW_NAME ="Valid name";
        UserSkill us = new UserSkill(existingUUID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);

        doThrow(new IllegalArgumentException("UserSkill already exists")).when(userSkillRepository).edit(us);

        assertThrows(IllegalArgumentException.class, () -> { //Module equals method compares on name only
            editUserSkill.requestList.add(differentUUID);
            editUserSkill.requestList.add(VALID_STAFF);
            editUserSkill.requestList.add(VALID_SKILL);
            editUserSkill.requestList.add(VALID_SKILL_LEVEL);
            editUserSkill.requestList.add(VALID_DATE);
            editUserSkill.requestList.add(VALID_NOTES);
            editUserSkill.execute();
        });
    }

}
