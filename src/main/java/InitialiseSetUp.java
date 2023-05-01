import domain.*;
import domain.utility.UUIDGenerator;
import globals.InMemoryDatabase;
import globals.Ioc_Container;
import globals.interfaces.DataProvider;
import repositories.CategoryRepository;
import repositories.SkillRepository;
import repositories.UserSkillRepository;
import repositories.interfaces.BaseCategoryRepository;
import repositories.interfaces.BaseSkillRepository;
import repositories.interfaces.BaseUserSkillRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static domain.User.SystemRole;

public class InitialiseSetUp {
    public InitialiseSetUp {
        DataProvider dataProvider = new InMemoryDatabase(populateSkills(),
                                                           populateCategory(), populateStaffUser(), populateUserSkill(), populateManager() );

        BaseSkillRepository skillRepository = new SkillRepository(dataProvider);
        BaseCategoryRepository categoryRepository = new CategoryRepository(dataProvider);
        BaseUserSkillRepository userSkillRepository = new UserSkillRepository(dataProvider);

        new Ioc_Container(skillRepository, categoryRepository, userSkillRepository);
    }

    public static List<Skill> populateSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(Category, UUID.randomUUID(), "Word"));
        skills.add(new Skill(Category, UUID.randomUUID(), "Java"));
        skills.add(new Skill(Category, UUID.randomUUID(), "Junit"));
        skills.add(new Skill(Category, UUID.randomUUID(), "Git"));

    }

    public static List<Category> populateCategory(){
        List<Category> catagories = new ArrayList<>();
        catagories.add(new Category(UUIDGenerator.generate(), "Office365"));
        catagories.add(new Category(UUIDGenerator.generate(), "Programming"));
        catagories.add(new Category(UUIDGenerator.generate(), "Testing Tools"));
        catagories.add(new Category(UUIDGenerator.generate(), "Version Control"));
        return catagories;
    }

    public static List<StaffUser> populateStaffUser(){
        List<StaffUser> staffUsers = new ArrayList<>();
        staffUsers.add(new StaffUser(UUIDGenerator.generate(), "AW1","password",
                                                    "Andy", "Wyatt",
                                                             SystemRole.STAFF_USER,
                                                             StaffUser.JobRole.MIDLEVEL_DEVELOPER,
                                                             Manager));
        return staffUsers;
    }

    public static List<Manager> populateManager() {
        List<Manager> Managers = new ArrayList<>();
        Managers.add(new Manager(UUIDGenerator.generate(), "Manager1", "password", "Ben", "Smith", SystemRole.MANAGER, StaffUser));
        return Managers;
    }

    public static List<UserSkill> populateUserSkill() {
        List<UserSkill> userSkills = new ArrayList<>();
        return userSkills;
    }
}
