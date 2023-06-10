package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.Category;
import domain.Skill;
import domain.iterators.SkillCollection;
import domain.validationStrategy.ValidationFactory;
import globals.InMemoryDatabase;
import globals.interfaces.DataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.interfaces.BaseSkillRepository;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SkillRepositoryTest {
    final UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");
    final String VALID_NAME = "skillname";
    static final SkillCollection dummyList = new SkillCollection();
    static BaseSkillRepository skillRepository;

    private static String CATEGORY_NAME = "category";
    final UUID DUMMY_CATEGORY_ID = UUID.fromString("0000-00-00-00-000001");
    private Category VALID_CATEGORY = new Category(DUMMY_CATEGORY_ID, CATEGORY_NAME);

    final Skill skill = ValidationFactory.createSkill(VALID_CATEGORY, DUMMY_ID, VALID_NAME);
    @BeforeAll
    static void setup() {
        DataProvider mockDataProvider = mock(InMemoryDatabase.class);
        when(mockDataProvider.getSkill()).thenReturn(dummyList);
        skillRepository = new SkillRepository(mockDataProvider);
    }

    @BeforeEach
        //Empty list between tests
    void resetList(){
        dummyList.clear();
    }

    @Test
    @DisplayName("When getAll is called then a valid list is returned")
    void test01() {
        assertEquals(skillRepository.getAll(), dummyList);
    }

    @Test
    @DisplayName("When a valid new Skill is added then the repo will add it")
    void test02() {
        Skill s = skill;
        assertDoesNotThrow( () -> skillRepository.add(s));
    }

    @Test
    @DisplayName("When an existing Skill is added then the repo will throw an EntryAlreadyExistsException")
    void test03() {
        Skill s = skill;;
        dummyList.add(s);

        assertThrows(EntryAlreadyExistsException.class, () -> {
            skillRepository.add(s);
        });
    }

    @Test
    @DisplayName("When an existing Skill is edited then the repo will update the name")
    void test04() {
        final String NEW_NAME = "new name";

        Skill s = skill;;
        dummyList.add(s);

        Skill newSkill =ValidationFactory.createSkill(VALID_CATEGORY, DUMMY_ID, NEW_NAME);

        skillRepository.edit(newSkill);

        assertEquals(skillRepository.getAll().get(0).getSkillName(), NEW_NAME);
    }
}
