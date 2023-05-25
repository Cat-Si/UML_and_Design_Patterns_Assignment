import domain.*;

import globals.InMemoryDatabaseTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import repositories.CategoryReposityTest;
import repositories.SkillRepositoryTest;
import repositories.UserSkillRepositoryTest;
import useCases.*;


@SelectClasses({


        //Domain Tests
        FullNameTests.class,
        SkillTests.class,
        StaffUserTests.class,
        ManagerTests.class,
        UserSkillTests.class,
        //GlobalsTests
        InMemoryDatabaseTests.class,
        //RepositoryTests
        CategoryReposityTest.class,
        ManagerTests.class,
        SkillRepositoryTest.class,
        StaffUserTests.class,
        UserSkillRepositoryTest.class,
        //UseCase Tests
        AddNewSkillTest.class,
        AddNewStaffTest.class,
        AddSkillToStaffTest.class,
        EditSkillTest.class,
        EditStaffTest.class,
        EditUserSkillTest.class,
        FindSkillsAssignedToStaffTests.class,
        GetAllCategoriesTest.class,
        GetAllManagersTest.class,
        GetAllSkillsTest.class,
        GetAllStaffTest.class,

})

@Suite
@SelectPackages("com.services.configuration")
public class TestSuite {
}