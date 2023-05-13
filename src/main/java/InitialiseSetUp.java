
import domain.*;
import domain.StaffUser;
import domain.enumerators.JobRole;
import domain.enumerators.SkillLevel;
import useCases.utility.UUIDGenerator;
import globals.InMemoryDatabase;
import globals.Ioc_Container;
import globals.interfaces.DataProvider;
import repositories.*;
import repositories.interfaces.*;

import java.time.LocalDate;
import java.util.*;

import domain.enumerators.SystemRole;

public class InitialiseSetUp {
    private static  List<Skill> skills = new ArrayList<>();
    private static List<Category> catagories = new ArrayList<>();
    private static List<StaffUser> staffUsers = new ArrayList<>();
    private static List<Manager> managers = new ArrayList<>();
    private static List<UserSkill> userSkills = new ArrayList<>();

    public InitialiseSetUp() {
        DataProvider dataProvider = new InMemoryDatabase(populateCategory(), populateSkills(),
                populateManager(), populateStaffUser(), populateUserSkill() );

        BaseCategoryRepository categoryRepository = new CategoryRepository(dataProvider);
        BaseSkillRepository skillRepository = new SkillRepository(dataProvider);
        BaseManagerRepository managerRepository = new ManagerRepository(dataProvider);
        BaseUserSkillRepository userSkillRepository = new UserSkillRepository(dataProvider);
        BaseStaffUserRepository staffUserRepository = new StaffUserRepository(dataProvider);

        new Ioc_Container(skillRepository, categoryRepository, userSkillRepository, managerRepository, staffUserRepository);
    }

    public static List<Category> populateCategory(){
        catagories.add(new Category(UUIDGenerator.generate(), "Office365"));
        catagories.add(new Category(UUIDGenerator.generate(), "Programming"));
        catagories.add(new Category(UUIDGenerator.generate(), "Testing Tools"));
        catagories.add(new Category(UUIDGenerator.generate(), "Version Control"));
        return catagories;
    }

    public static List<Skill> populateSkills() {
        skills.add(new Skill(catagories.get(0), UUID.randomUUID(), "Word"));
        skills.add(new Skill(catagories.get(1), UUID.randomUUID(), "Java"));
        skills.add(new Skill(catagories.get(2), UUID.randomUUID(), "Junit"));
        skills.add(new Skill(catagories.get(3), UUID.randomUUID(), "Git"));
        return skills;
    }

    public static List<StaffUser> populateStaffUser(){
        staffUsers.add(new StaffUser(UUIDGenerator.generate(), "Andy","Wyatt",
                                                    "AW1", "password",
                                                             SystemRole.STAFF_USER,
                                                             JobRole.MIDLEVEL_DEVELOPER,
                                                             managers.get(0 ) ));
        staffUsers.add(new StaffUser(UUIDGenerator.generate(), "Bill","Richards",
                                                            "BR1", "password",
                                                                     SystemRole.STAFF_USER,
                                                                      JobRole.JUNIOR_DEVELOPER,
                                                                        managers.get(0) ));
        staffUsers.add(new StaffUser(UUIDGenerator.generate(), "Jenny","Finney",
                                                                     "JF1", "password",
                                                                               SystemRole.STAFF_USER,
                                                                                JobRole.SENIOR_DEVELOPER,
                                                                            managers.get(0) ));
        managers.get(0).addStaff(staffUsers.get(0));
        managers.get(0).addStaff(staffUsers.get(1));
        managers.get(0).addStaff(staffUsers.get(2));
        return staffUsers;
    }

    public static List<Manager> populateManager() {
        Manager st = new Manager(UUIDGenerator.generate(), "Ben", "Smith", "BS1m", "password", SystemRole.MANAGER);
        managers.add(st);


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
   // List<UserSkill> userSkills = new ArrayList<>();
        LocalDate expiry = LocalDate.now();
        Optional.ofNullable(expiry);
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(0), skills.get(0), SkillLevel.ADVANCED, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(0), skills.get(1), SkillLevel.ADVANCED, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(0), skills.get(2), SkillLevel.BASIC, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(0), skills.get(3), SkillLevel.BASIC, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(1), skills.get(0), SkillLevel.ADVANCED, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(1), skills.get(1), SkillLevel.BASIC, expiry, "Minimal Knowledge in Java" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(1), skills.get(2), SkillLevel.NONE, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(1), skills.get(3), SkillLevel.BASIC, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(2), skills.get(0), SkillLevel.EXPERT, expiry, "Completed MOS exams" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(1), skills.get(1), SkillLevel.EXPERT, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(1), skills.get(2), SkillLevel.EXPERT, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(1), skills.get(3), SkillLevel.EXPERT, expiry, "" ));



        return userSkills;
    }
}
