package useCases;

import domain.Category;
import domain.Skill;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.SkillRepository;
import repositories.interfaces.BaseSkillRepository;
import useCases.skills.EditSkill;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EditSkillTest {

    static BaseSkillRepository skillRepository;

    static EditSkill editSkill;

    @BeforeAll
    static void setup() {
        skillRepository = mock(SkillRepository.class);
        editSkill = new EditSkill(skillRepository);
    }

    final UUID DUMMY_CATEGORY_ID = UUID.fromString("0000-00-00-00-000001");
    private Category VALID_CATEGORY = new Category(DUMMY_CATEGORY_ID, "category");

    @Test
    @DisplayName("When use case is executed with a valid Skill details then it is edited")
    void test01() {
        final UUID existingUUID = UUID.fromString("0000-00-00-00-000000");
        final String NEW_NAME ="Valid name";
        Skill skill = new Skill(VALID_CATEGORY, existingUUID, NEW_NAME);

        editSkill.requestList.add(VALID_CATEGORY);
        editSkill.requestList.add(existingUUID);
        editSkill.requestList.add(NEW_NAME);
        editSkill.execute();
        verify(skillRepository).edit(skill); //check method was called with module det
    }


    @Test
    @DisplayName("When use case is executed with a invalid skill then an IllegalArgumentException is thrown")
    void test02() throws IllegalArgumentException {
        final UUID existingUUID = UUID.fromString("0000-00-00-00-000000");
        final UUID differentUUID = UUID.fromString("0000-00-00-00-000001");

        final String NEW_NAME ="Valid name";
        Skill skill = new Skill(VALID_CATEGORY, existingUUID, NEW_NAME);

        doThrow(new IllegalArgumentException("skill already exists")).when(skillRepository).edit(skill);

        assertThrows(IllegalArgumentException.class, () -> {
            editSkill.requestList.add( VALID_CATEGORY);
            editSkill.requestList.add(differentUUID);
            editSkill.requestList.add(NEW_NAME);
            editSkill.execute();
        });
    }



}
