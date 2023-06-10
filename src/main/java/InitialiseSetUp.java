import domain.Category;
import domain.Manager;
import domain.StaffUser;
import domain.UserSkill;
import domain.enumerators.JobRole;
import domain.enumerators.SkillLevel;
import domain.enumerators.SystemRole;
import domain.iterators.SkillCollection;
import domain.validationStrategy.ValidationFactory;
import globals.InMemoryDatabase;
import globals.Ioc_Container;
import globals.interfaces.DataProvider;
import repositories.*;
import repositories.interfaces.*;
import useCases.utility.UUIDGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InitialiseSetUp {
    private static  SkillCollection skills = new SkillCollection();
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

        Ioc_Container iocContainer = new Ioc_Container.Ioc_ContainerBuilder( skillRepository,
                categoryRepository,
               userSkillRepository,
                managerRepository,
                 staffUserRepository).build();
    }

    public static List<Category> populateCategory(){
        catagories.add(new Category(UUIDGenerator.generate(), "Office365"));
        catagories.add(new Category(UUIDGenerator.generate(), "Programming"));
        catagories.add(new Category(UUIDGenerator.generate(), "Testing Tools"));
        catagories.add(new Category(UUIDGenerator.generate(), "Version Control"));
        return catagories;
    }

    public static SkillCollection populateSkills() {
        skills.add(ValidationFactory.createSkill(catagories.get(0), UUID.randomUUID(), "Word"));
        skills.add(ValidationFactory.createSkill(catagories.get(1), UUID.randomUUID(), "Java"));
        skills.add(ValidationFactory.createSkill(catagories.get(2), UUID.randomUUID(), "Junit"));
        skills.add(ValidationFactory.createSkill(catagories.get(3), UUID.randomUUID(), "Git"));
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

        return managers;
    }

    public static List<UserSkill> populateUserSkill() {
        LocalDate expiry = LocalDate.now();
        Optional.ofNullable(expiry);
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(0), skills.get(0), SkillLevel.ADVANCED, expiry, "Professional Qualification" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(0), skills.get(1), SkillLevel.ADVANCED, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(0), skills.get(2), SkillLevel.BASIC, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(0), skills.get(3), SkillLevel.BASIC, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(1), skills.get(0), SkillLevel.ADVANCED, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(1), skills.get(1), SkillLevel.BASIC, expiry, "Minimal Knowledge in Java" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(1), skills.get(2), SkillLevel.NONE, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(1), skills.get(3), SkillLevel.BASIC, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(2), skills.get(0), SkillLevel.EXPERT, expiry, "Completed MOS exams" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(2), skills.get(1), SkillLevel.EXPERT, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(2), skills.get(2), SkillLevel.EXPERT, expiry, "" ));
        userSkills.add(new UserSkill(UUIDGenerator.generate(), staffUsers.get(2), skills.get(3), SkillLevel.EXPERT, expiry, "" ));

        return userSkills;
    }
}
