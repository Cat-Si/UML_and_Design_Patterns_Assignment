package globals;

import domain.*;
import domain.enumerators.JobRole;
import domain.enumerators.SkillLevel;
import domain.enumerators.SystemRole;
import domain.iterators.SkillCollection;
import domain.validationStrategy.ValidationFactory;
import globals.interfaces.DataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class InMemoryDatabaseTests {

    static DataProvider dataProvider;


    static UUID DUMMY_CATEGORY_ID = UUID.fromString("0000-00-00-00-000003");
    static UUID DUMMY_SKILL_ID = UUID.fromString("0000-00-00-00-000004");
    static UUID DUMMY_MANAGER_ID = UUID.fromString("0000-00-00-00-000002");
    static UUID DUMMY_STAFF_ID = UUID.fromString("0000-00-00-00-000001");
    static UUID DUMMY_US_ID = UUID.fromString("0000-00-00-00-000005");

    static String DUMMY_STAFF_NAME = "Dummy Staff";
    static String DUMMY_Manager_NAME = "Dummy Manager";
    static String DUMMY_SKILL_NAME = "Dummy Skill";
    static String DUMMY_CATEGORY_NAME = "Dummy Category";
    static LocalDate VALID_DATE = LocalDate.now();

    static List<Category> categoryList = new ArrayList<>();
    static SkillCollection skillList  = new SkillCollection();
    static List<Manager> managerList = new ArrayList<>();
    static List<StaffUser> staffUserList = new ArrayList<>();
    static List<UserSkill> userSkillList = new ArrayList<>();

    static Category VALID_CATEGORY = new Category(DUMMY_CATEGORY_ID, DUMMY_CATEGORY_NAME);
    static Skill VALID_SKILL = ValidationFactory.createSkill(VALID_CATEGORY, DUMMY_SKILL_ID, DUMMY_SKILL_NAME);
    static Manager VALID_MANAGER = new Manager(DUMMY_MANAGER_ID, DUMMY_Manager_NAME, "ager1", "username2", "password2", SystemRole.MANAGER);
    static StaffUser VALID_STAFF = new StaffUser(DUMMY_STAFF_ID, DUMMY_STAFF_NAME, "user1", "username1", "password1", SystemRole.STAFF_USER, JobRole.MIDLEVEL_DEVELOPER, VALID_MANAGER);
    static UserSkill VALID_USER_SKILL = new UserSkill(DUMMY_US_ID, VALID_STAFF, VALID_SKILL, SkillLevel.EXPERT, VALID_DATE, "VALID_NOTES");

    @BeforeAll
    static void setUp() {
        categoryList.add(VALID_CATEGORY);
        skillList.add(VALID_SKILL);
        managerList.add(VALID_MANAGER);
        staffUserList.add(VALID_STAFF);
        userSkillList.add(VALID_USER_SKILL);
        dataProvider = new InMemoryDatabase(categoryList, skillList, managerList, staffUserList, userSkillList);
    }


    //Category
    @Test
    @DisplayName("When getCategories is called then it returns 1 category")
    void test01() {
        assertEquals(dataProvider.getCategory().size(),1);
    }
    @Test
    @DisplayName("When the first Category is retrieved it has the correct name")
    void test02() {
        List<Category> categories = dataProvider.getCategory();
        assertEquals(categories.get(0).getCategoryName(), DUMMY_CATEGORY_NAME);
    }

    //Skill
    @Test
    @DisplayName("When getSkill is called then it returns 1 skill")
    void test03() {
        assertEquals(dataProvider.getSkill().size(),1);
    }
    @Test
    @DisplayName("When the first Skill is retrieved it has the correct name")
    void test04() {
        SkillCollection skills = dataProvider.getSkill();
        assertEquals(skills.get(0).getSkillName(), DUMMY_SKILL_NAME);
    }

    //Manager
    @Test
    @DisplayName("When getManagers is called then it returns 1 Manager")
    void test05() {
        assertEquals(dataProvider.getManager().size(),1);
    }
    @Test
    @DisplayName("When the first Manager is retrieved it has the correct name")
    void test06() {
        List<Manager> manager = dataProvider.getManager();
        assertEquals(manager.get(0).getFirstName(), DUMMY_Manager_NAME );
    }

    //StaffUser
    @Test
    @DisplayName("When getStaffUser is called then it returns 1 StaffUser")
    void test07() {
        assertEquals(dataProvider.getStaffUser().size(),1);
    }
    @Test
    @DisplayName("When the first StaffUser is retrieved it has the correct name")
    void test08() {
        List<StaffUser> staffUser = dataProvider.getStaffUser();
        assertEquals(staffUser.get(0).getFirstName(), DUMMY_STAFF_NAME );
    }

    //UserSkills
    @Test
    @DisplayName("When UserSkill is called then it returns 1 UserSkill")
    void test09() {
        assertEquals(dataProvider.getUserSkill().size(),1);
    }
    @Test
    @DisplayName("When the first UserSkill is retrieved it has the correct Skill")
    void test10() {
        List<UserSkill> userSkills = dataProvider.getUserSkill();
        assertEquals(userSkills.get(0).getSkill(), VALID_SKILL);
    }

    @Test
    @DisplayName("When the first UserSkill is retrieved it has the correct Staff")
    void test11() {
        List<UserSkill> userSkills = dataProvider.getUserSkill();
        assertEquals(userSkills.get(0).getStaff(), VALID_STAFF);
    }


}
