package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.*;
import domain.enumerators.JobRole;
import domain.enumerators.SkillLevel;
import domain.enumerators.SystemRole;
import domain.validationStrategy.ValidationFactory;
import globals.InMemoryDatabase;
import globals.interfaces.DataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.interfaces.BaseUserSkillRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserSkillRepositoryTest {

    static BaseUserSkillRepository userSkillRepository;

    static DataProvider mockDataProvider;

    static final List<UserSkill> dummyList = new ArrayList<>();

    final UUID DUMMY_STAFF_ID = UUID.fromString("0000-00-00-00-000000");
    final String Valid_STAFF_NAME = "staff1";

    final UUID DUMMY_SKILL_ID = UUID.fromString("0000-00-00-00-000001");
    final UUID DUMMY_SKILL_ID2 = UUID.fromString("0000-00-00-00-000003");
    final UUID DUMMY_US_ID = UUID.fromString("0000-00-00-00-000002"); //Only needed if no entry for a specific staff is found
    final UUID DUMMY_MANAGER_ID = UUID.fromString("0000-00-00-00-000004");

    final LocalDate VALID_DATE = LocalDate.now();
    private static String CATEGORY_NAME = "category";

    static String SKILL_NAME = "skillname";
    final UUID DUMMY_CATEGORY_ID = UUID.fromString("0000-00-00-00-000001");
    private Category VALID_CATEGORY = new Category(DUMMY_CATEGORY_ID, CATEGORY_NAME);
    final Skill VALID_SKILL = ValidationFactory.createSkill(VALID_CATEGORY, DUMMY_SKILL_ID, SKILL_NAME);
    final Manager VALID_MANAGER = new Manager(DUMMY_MANAGER_ID, "man1", "ager1", "username2", "password2", SystemRole.MANAGER);
    final StaffUser VALID_STAFF = new StaffUser(DUMMY_STAFF_ID, "staff1", "user1", "username1", "password1", SystemRole.STAFF_USER, JobRole.MIDLEVEL_DEVELOPER, VALID_MANAGER);

    @BeforeAll
    static void setUp() {
        mockDataProvider = mock(InMemoryDatabase.class);
        userSkillRepository = new UserSkillRepository(mockDataProvider);
    }

    @BeforeEach
    void resetList() {
        dummyList.clear();
    }

    @Test
    @DisplayName("When getAll is called then a valid list is returned")
    void test01() {
        assertEquals(userSkillRepository.getAll(), dummyList);
    }

    @Test //this is passing the test but failing due to syntax between Expected and Actual
    @DisplayName("When a list of all Skills for a valid StaffUser is called then a list of all Skills for that user are returned")
    void test02() {
        StaffUser su = VALID_STAFF;
        Skill s = VALID_SKILL;
        UserSkill us = new UserSkill(DUMMY_US_ID, VALID_STAFF, VALID_SKILL, SkillLevel.NONE, VALID_DATE, "notes");
        dummyList.add(us);//Returns list of module enrolments

        when(mockDataProvider.getUserSkill()).thenReturn(dummyList);

        List<UserSkill> expectedListOfSkills = new ArrayList<>();
        expectedListOfSkills.add(us);//What our repo should return - to compare in our assert
        assertEquals(userSkillRepository.getSkillsForStaff(su).get(), expectedListOfSkills);
    }

    @Test
    @DisplayName("When a list of all skills for a staff user are called but no such skill is currently in the userskill repo then the list is empty")
    void test03() {
        StaffUser su = VALID_STAFF;

        when(mockDataProvider.getUserSkill()).thenReturn(dummyList);//Empty list

        assertTrue(userSkillRepository.getSkillsForStaff(su).isEmpty());
    }

    @Test
    @DisplayName("When a valid new UserSkill is added then the repo will add it if the user is not already present")
    void test04() {
        StaffUser su = VALID_STAFF;
        Skill s = VALID_SKILL;

        when(mockDataProvider.getUserSkill()).thenReturn(dummyList); //Add empty list (no such module already present)
        assertDoesNotThrow( () -> userSkillRepository.add(DUMMY_US_ID, VALID_STAFF, VALID_SKILL, SkillLevel.NONE, VALID_DATE, "notes"));
    }

    @Test
    @DisplayName("When all Skills are removed from a Staff User then the repo will remove the UserSkill entry from the list")
    void test05() {
        StaffUser su = VALID_STAFF;
        Skill s = VALID_SKILL;
        UserSkill us = new UserSkill(DUMMY_US_ID, VALID_STAFF, VALID_SKILL, SkillLevel.NONE, VALID_DATE, "notes");
        dummyList.add(us);//Returns list of module enrolments

        when(mockDataProvider.getUserSkill()).thenReturn(dummyList);

        userSkillRepository.removeSkillFromStaff(su,s);
        assertTrue(userSkillRepository.getSkillsForStaff(su).isEmpty());
    }


    @Test
    @DisplayName("When an additional skill is added to a staff user already with at least one skill then the repo will add the skill")
    void test06() {

        UserSkill us = new UserSkill(DUMMY_US_ID, VALID_STAFF, VALID_SKILL, SkillLevel.NONE, VALID_DATE, "notes");
        dummyList.add(us);//Returns list of module enrolments

        when(mockDataProvider.getUserSkill()).thenReturn(dummyList);

        Skill s2 = ValidationFactory.createSkill(VALID_CATEGORY, DUMMY_SKILL_ID2, "skill2");
        assertDoesNotThrow( () -> userSkillRepository.add(DUMMY_US_ID, VALID_STAFF, s2, SkillLevel.NONE, VALID_DATE, "notes"));
    }

    @Test
    @DisplayName("When we try to add an already added skill to a staff user then the repo will throw an EntryAlreadyExistsException")
    void test07() {
       UserSkill us = new UserSkill(DUMMY_US_ID, VALID_STAFF, VALID_SKILL, SkillLevel.NONE, VALID_DATE, "notes");
        dummyList.add(us);//Returns list of module enrolments

        when(mockDataProvider.getUserSkill()).thenReturn(dummyList);

        assertThrows(EntryAlreadyExistsException.class, () -> {
            userSkillRepository.add(DUMMY_US_ID, VALID_STAFF, VALID_SKILL, SkillLevel.NONE, VALID_DATE, "notes"); //Add same student to same module
        });
    }

    @Test
    @DisplayName("When we try to remove an skill from a user skill object for a present user then the repo will remove it")
    void test08() {
       UserSkill us = new UserSkill(DUMMY_US_ID, VALID_STAFF, VALID_SKILL, SkillLevel.NONE, VALID_DATE, "notes");
        dummyList.add(us);//Returns list of module enrolments

        when(mockDataProvider.getUserSkill()).thenReturn(dummyList);



        assertDoesNotThrow( () ->  userSkillRepository.removeSkillFromStaff(VALID_STAFF,VALID_SKILL));
    }

    @Test
    @DisplayName("When we try to remove a skill which isn't present from a user skill then the repo will throw an IllegalArgumentException")
    void test09() {
         UserSkill us = new UserSkill(DUMMY_US_ID, VALID_STAFF, VALID_SKILL, SkillLevel.NONE, VALID_DATE, "notes");
        dummyList.add(us);//Returns list of module enrolments

        when(mockDataProvider.getUserSkill()).thenReturn(dummyList);



        Skill s2 = ValidationFactory.createSkill(VALID_CATEGORY, DUMMY_SKILL_ID2, "skill2");

        assertThrows(IllegalArgumentException.class, () -> {
           userSkillRepository.removeSkillFromStaff(VALID_STAFF, s2);
        });
    }

    @Test //Issue potentially linked to a issue with AddSkillsToStaffController
    @DisplayName("When we try to remove a skill from a user skill for a staff user that doesn't have any skills then the repo will throw an IllegalArgumentException")
    void test10() {

        when(mockDataProvider.getUserSkill()).thenReturn(dummyList);

        assertThrows(IllegalArgumentException.class, () -> {
            userSkillRepository.removeSkillFromStaff(VALID_STAFF,VALID_SKILL);
        });
    }

}
