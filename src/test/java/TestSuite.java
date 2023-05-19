import domain.FullNameTests;

import domain.SkillTests;
import domain.StaffUserTests;

import globals.InMemoryDatabaseTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.*;


@SelectClasses({
        //MainTests.class, // UI TESTS
        FullNameTests.class,
        SkillTests.class,
        StaffUserTests.class,

        InMemoryDatabaseTests.class,

})

@Suite
@SelectPackages("com.services.configuration")
public class TestSuite {
}