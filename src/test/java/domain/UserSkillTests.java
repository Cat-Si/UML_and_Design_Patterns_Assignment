package domain;

import domain.enumerators.JobRole;
import domain.enumerators.SkillLevel;
import domain.enumerators.SystemRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserSkillTests {

    final UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");

    final UUID DUMMY_STAFF_ID = UUID.fromString("0000-00-00-00-000001");

    final UUID DUMMY_MANAGER_ID = UUID.fromString("0000-00-00-00-000002");
    final UUID DUMMY_CATEGORY_ID = UUID.fromString("0000-00-00-00-000003");
    final UUID DUMMY_SKILL_ID = UUID.fromString("0000-00-00-00-000004");

//    final SystemRole VALID_STAFF_SYSTEM_ROLE = SystemRole.STAFF_USER;

//    final SystemRole VALID_MANAGER_SYSTEM_ROLE = SystemRole.MANAGER;

//    final JobRole VALID_JOB_ROLE = JobRole.MIDLEVEL_DEVELOPER;

    final SkillLevel VALID_SKILL_LEVEL = SkillLevel.ADVANCED;

    final String VALID_NOTES = "someNotes";

    final LocalDate VALID_DATE = LocalDate.now();



    final Category VALID_CATEGORY = new Category(DUMMY_CATEGORY_ID, "category");

    final Skill VALID_SKILL = new Skill(VALID_CATEGORY, DUMMY_SKILL_ID, "skillName");

    final Manager VALID_MANAGER = new Manager(DUMMY_MANAGER_ID, "man1", "ager1", "username2", "password2", SystemRole.MANAGER);

    final StaffUser VALID_STAFF = new StaffUser(DUMMY_STAFF_ID, "staff1", "user1", "username1", "password1", SystemRole.STAFF_USER, JobRole.MIDLEVEL_DEVELOPER, VALID_MANAGER);


    //new UserSkill(DUMMY_ID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES)
    @Test
    @DisplayName("When a UserSkill with valid details is assigned to the constructor and toString is called then the correct details are returned")
    void test01() {

        UserSkill us = new UserSkill(DUMMY_ID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);

        final String EXPECTED_STRING = String.format("%s - %s", VALID_SKILL, VALID_SKILL_LEVEL);

        assertEquals(us.toString(),EXPECTED_STRING);
        assertTrue(us.getCurrentSkills().size()==1);
    }


}
