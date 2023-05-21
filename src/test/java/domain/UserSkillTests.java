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
    final UUID DUMMY_SKILL_ID2 = UUID.fromString("0000-00-00-00-000014");


    final SkillLevel VALID_SKILL_LEVEL = SkillLevel.ADVANCED;
    final String VALID_NOTES = "someNotes";
    final LocalDate VALID_DATE = LocalDate.now();

    final Category VALID_CATEGORY = new Category(DUMMY_CATEGORY_ID, "category");
    final Skill VALID_SKILL = new Skill(VALID_CATEGORY, DUMMY_SKILL_ID, "skillName");
    final Manager VALID_MANAGER = new Manager(DUMMY_MANAGER_ID, "man1", "ager1", "username2", "password2", SystemRole.MANAGER);
    final StaffUser VALID_STAFF = new StaffUser(DUMMY_STAFF_ID, "staff1", "user1", "username1", "password1", SystemRole.STAFF_USER, JobRole.MIDLEVEL_DEVELOPER, VALID_MANAGER);



    @Test
    @DisplayName("When a UserSkill with valid details is assigned to the constructor and toString is called then the correct details are returned")
    void test01() {

        UserSkill us = new UserSkill(DUMMY_ID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);

        final String EXPECTED_STRING = String.format("%s - %s", VALID_SKILL, VALID_SKILL_LEVEL);
        assertEquals(us.toString(),EXPECTED_STRING);
        assertTrue(us.getCurrentSkills().size()==1);
    }

    @Test
    @DisplayName("When a valid new Skill is added to an existing UserSkill object then the number of Skill in that UserSkill object will increase by 1")
    void test02() {
        UserSkill us = new UserSkill(DUMMY_ID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);

        Skill s2 = new Skill(VALID_CATEGORY, DUMMY_SKILL_ID2, "skill");
        us.addSkill(s2);
        assertTrue(us.getCurrentSkills().size()==2);
    }

    @Test
    @DisplayName("When a Skill is already present in an UserSkills object then attempting to add it will not be successful")
    void test03() {
        UserSkill us = new UserSkill(DUMMY_ID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);
        Skill s = new Skill(VALID_CATEGORY, DUMMY_SKILL_ID, "skillName");
        us.addSkill(s);
        assertTrue(us.getCurrentSkills().size()==1);
    }

    @Test
    @DisplayName("When a valid Skill is removed from an UserSkill object then the number of Skill in that UserSkill object will decrease by 1")
    void test04() {
        UserSkill us = new UserSkill(DUMMY_ID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);

        assertTrue(us.getCurrentSkills().size()==1);

        us.removeSkill(VALID_SKILL);
        assertTrue(us.getCurrentSkills().size()==0);
    }

    @Test
    @DisplayName("When a Skill is not present in an UserSkill object then calling remove with not decrease the number of Skills for that User")
    void test05() {

        UserSkill us = new UserSkill(DUMMY_ID, VALID_STAFF, VALID_SKILL, VALID_SKILL_LEVEL, VALID_DATE, VALID_NOTES);

        assertTrue(us.getCurrentSkills().size()==1);

        Skill s = new Skill(VALID_CATEGORY, DUMMY_SKILL_ID2, "skill");

        us.removeSkill(s);
        assertTrue(us.getCurrentSkills().size()==1);
    }
}
