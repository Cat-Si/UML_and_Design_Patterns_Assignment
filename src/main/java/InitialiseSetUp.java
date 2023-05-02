import domain.*;
import domain.StaffUser;
import useCases.utility.UUIDGenerator;
import globals.InMemoryDatabase;
import globals.Ioc_Container;
import globals.interfaces.DataProvider;
import repositories.*;
import repositories.interfaces.*;

import java.time.LocalDate;
import java.util.*;

import static domain.User.SystemRole;

public class InitialiseSetUp {
    private static  List<Skill> skills = new ArrayList<>();
    private static List<Category> catagories = new ArrayList<>();
    private static List<StaffUser> staffUsers = new ArrayList<>();
    private static List<Manager> managers = new ArrayList<>();
    private static List<UserSkill> userSkills = new ArrayList<>();

    public InitialiseSetUp() {
        DataProvider dataProvider = new InMemoryDatabase(populateSkills(),
                                                           populateCategory(), populateStaffUser(), populateManager(), populateUserSkill() );

        BaseSkillRepository skillRepository = new SkillRepository(dataProvider);
        BaseCategoryRepository categoryRepository = new CategoryRepository(dataProvider);
        BaseUserSkillRepository userSkillRepository = new UserSkillRepository(dataProvider);
        BaseManagerRepository managerRepository = new ManagerRepository(dataProvider);
        BaseStaffUserRepository staffUserRepository = new StaffUserRepository(dataProvider);

        new Ioc_Container(skillRepository, categoryRepository, userSkillRepository, managerRepository, staffUserRepository);
    }

    public static List<Skill> populateSkills() {
        skills.add(new Skill(catagories.get(0), UUID.randomUUID(), "Word"));
        skills.add(new Skill(catagories.get(1), UUID.randomUUID(), "Java"));
        skills.add(new Skill(catagories.get(2), UUID.randomUUID(), "Junit"));
        skills.add(new Skill(catagories.get(3), UUID.randomUUID(), "Git"));
        return skills;
    }

    public static List<Category> populateCategory(){
        catagories.add(new Category(UUIDGenerator.generate(), "Office365"));
        catagories.add(new Category(UUIDGenerator.generate(), "Programming"));
        catagories.add(new Category(UUIDGenerator.generate(), "Testing Tools"));
        catagories.add(new Category(UUIDGenerator.generate(), "Version Control"));
        return catagories;
    }

    public static List<StaffUser> populateStaffUser(){
        staffUsers.add(new StaffUser(UUIDGenerator.generate(), "AW1","password",
                                                    "Andy", "Wyatt",
                                                             SystemRole.STAFF_USER,
                                                             StaffUser.JobRole.MIDLEVEL_DEVELOPER,
                                                             managers.get(0 ) ));
        staffUsers.add(new StaffUser(UUIDGenerator.generate(), "BR1","password",
                                                            "Bill", "Richards",
                                                                     SystemRole.STAFF_USER,
                                                                      StaffUser.JobRole.JUNIOR_DEVELOPER,
                                                                        managers.get(0) ));
        staffUsers.add(new StaffUser(UUIDGenerator.generate(), "AW1","password",
                                                                     "Jenny", "Finney",
                                                                               SystemRole.STAFF_USER,
                                                                                StaffUser.JobRole.SENIOR_DEVELOPER,
                                                                            managers.get(0) ));
        return staffUsers;
    }

    public static List<Manager> populateManager() {
        Manager st = new Manager(UUIDGenerator.generate(), "Manager1", "password", "Ben", "Smith", SystemRole.MANAGER, staffUsers.get(0));
        managers.add(st); st.addStaff(staffUsers.get(1)); st.addStaff(staffUsers.get(2));


/*        Map<Manager, List<StaffUser>> staffByManager = staffUsers.stream()
                .collect(Collectors.groupingBy(StaffUser::getCurrentManager));

        // Add staffUsers to their respective managers
        staffByManager.forEach((manager, staffUsers) -> {
            manager.addStaff((StaffUser) staffUsers);
            managers.add(manager);
        });*/

        //managers.add(new Manager(UUIDGenerator.generate(), "Manager1", "password", "Ben", "Smith", SystemRole.MANAGER, staffUsers.get(0)));
        return managers;
    }

    public static List<UserSkill> populateUserSkill() {

        LocalDate expiry = LocalDate.now();
        Optional.ofNullable(expiry);
        userSkills.add(new UserSkill(UUIDGenerator.generate(), skills.get(0), staffUsers.get(0), SkillLevel.ADVANCED, expiry, "" ));
        return userSkills;
    }
}
