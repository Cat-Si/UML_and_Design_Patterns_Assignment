package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SkillTests {

    private static String VALID_SKILL_NAME = "name";
    final UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");
    private static String CATEGORY_NAME = "category";
    final UUID DUMMY_CATEGORY_ID = UUID.fromString("0000-00-00-00-000001");
    private Category VALID_CATEGORY = new Category(DUMMY_CATEGORY_ID, CATEGORY_NAME);

    final Skill skill = new Skill(VALID_CATEGORY, DUMMY_ID, VALID_SKILL_NAME);



// null tests
    @Test
    @DisplayName("When null category parameter is passed then IllegalArgumentException is thrown")
    void test01() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Skill(null, DUMMY_ID, VALID_SKILL_NAME);
        });
    }

    @Test
    @DisplayName("When null id parameter is passed then IllegalArgumentException is thrown")
    void test02() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Skill(VALID_CATEGORY, null, VALID_SKILL_NAME);
        });
    }

    @Test
    @DisplayName("When null skill name parameter is passed then IllegalArgumentException is thrown")
    void test03() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Skill(VALID_CATEGORY, DUMMY_ID, null);
        });
    }


//blank tests

    @Test
    @DisplayName("When blank skill name value is passed then IllegalArgumentException is thrown")
    void test04() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Skill(VALID_CATEGORY, DUMMY_ID, " ");
        });
        assertEquals(exception.getMessage(), "name must not be blank");
    }

//toString

    @Test
    @DisplayName("When a skill with valid details is assigned to the constructor and toString is called then the correct details are returned")
    void test05() {
        final String EXPECTED_STRING = String.format("%s (%s)", VALID_SKILL_NAME, VALID_CATEGORY); //Check the student class

        Skill skill = new Skill(VALID_CATEGORY, DUMMY_ID, VALID_SKILL_NAME);
        assertEquals(EXPECTED_STRING, skill.toString());
    }


//equals tests
    @Test
    @DisplayName("When two skill objects with the same ids are compared the equals method works as expected")
    void test06() {
        Skill s1 = new Skill(VALID_CATEGORY, DUMMY_ID, VALID_SKILL_NAME);
        Skill s2 = new Skill(VALID_CATEGORY, DUMMY_ID, VALID_SKILL_NAME);

        assertEquals(s1,s2);//Equals method works as expected
    }

    @Test
    @DisplayName("When two skill objects with the different ids are compared the equals method works as expected")
    void test07() {
        final String ANOTHER_SKILL_NAME = "skill2";

        Skill s1 = new Skill(VALID_CATEGORY, DUMMY_ID, VALID_SKILL_NAME);
        Skill s2 = new Skill(VALID_CATEGORY, DUMMY_ID, ANOTHER_SKILL_NAME);

        assertNotEquals(s1,s2);//Equals method works as expected
    }
}
