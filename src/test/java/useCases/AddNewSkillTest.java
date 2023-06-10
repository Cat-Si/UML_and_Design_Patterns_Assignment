package useCases;

import Exceptions.EntryAlreadyExistsException;
import domain.Category;
import domain.Skill;
import domain.validationStrategy.ValidationFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repositories.SkillRepository;
import repositories.interfaces.BaseSkillRepository;
import useCases.skills.AddNewSkill;
import useCases.utility.UUIDGenerator;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class AddNewSkillTest {

    static BaseSkillRepository skillRepository;

    static AddNewSkill addNewSkill;

    final static UUID DUMMY_ID = UUID.fromString("0000-00-00-00-000000");

    final UUID DUMMY_CATEGORY_ID = UUID.fromString("0000-00-00-00-000001");
    private Category VALID_CATEGORY = new Category(DUMMY_CATEGORY_ID, "category");
    final String NAME = "skillname";
    final Skill VALID_SKILL = ValidationFactory.createSkill(VALID_CATEGORY, DUMMY_ID, NAME);
    @BeforeAll
    static void setup() {
        skillRepository = mock(SkillRepository.class);
        addNewSkill = new AddNewSkill(skillRepository);
    }

    @BeforeEach
    void resetList() {
        addNewSkill.clear();
    }

    private void executeUseCase() throws IllegalArgumentException, EntryAlreadyExistsException{
        try (MockedStatic<UUIDGenerator> ignored = Mockito.mockStatic(UUIDGenerator.class)) {
            when(UUIDGenerator.generate()).thenReturn(DUMMY_ID);

            addNewSkill.execute();
        }
    }

    @Test
    @DisplayName("When use case is executed with valid skill details then a new skill is added")
    void test01() {
        final String NAME = "skill name";
        Skill VALID_SKILL = ValidationFactory.createSkill(VALID_CATEGORY, DUMMY_ID, NAME);

        addNewSkill.add(NAME);
        addNewSkill.add(VALID_CATEGORY);

        assertDoesNotThrow( () ->executeUseCase());
    }

    @Test
    @DisplayName("When use case is executed with invalid skill details then an IllegalArgumentException is thrown")
    void test02(){
        final String EMPTY_NAME = "";
        addNewSkill.add(EMPTY_NAME);
        addNewSkill.add(VALID_CATEGORY);

        assertThrows(IllegalArgumentException.class, () -> {
            executeUseCase();
        });
    }

    @Test
    @DisplayName("When use case is executed with an existing skill details then an EntryAlreadyExistsException is thrown")
    void test03() throws EntryAlreadyExistsException {
        final String EXISTING_SKILL_NAME = "existing skill name";
       Skill s = ValidationFactory.createSkill(VALID_CATEGORY, DUMMY_ID, EXISTING_SKILL_NAME);

        doThrow(new EntryAlreadyExistsException("Skill already exists")).when(skillRepository).add(s);

        addNewSkill.add(EXISTING_SKILL_NAME);
        addNewSkill.add(VALID_CATEGORY);

        assertThrows(EntryAlreadyExistsException.class, () -> {
            executeUseCase();
        });
    }


}
