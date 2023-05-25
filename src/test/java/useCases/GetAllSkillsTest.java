package useCases;

import domain.Category;
import domain.Skill;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repositories.SkillRepository;
import repositories.interfaces.BaseSkillRepository;
import useCases.skills.GetAllSkills;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllSkillsTest {

    static BaseSkillRepository skillRepository;

    static GetAllSkills getAllSkills;

    final UUID DUMMY_CATEGORY_ID = UUID.fromString("0000-00-00-00-000001");
    private Category VALID_CATEGORY = new Category(DUMMY_CATEGORY_ID, "category");

    @BeforeAll
    static void setup() {
        skillRepository = mock(SkillRepository.class);
        getAllSkills = new GetAllSkills(skillRepository);
    }

    @Test
    @DisplayName("When use case is executed one or more valid skills will be returned")
    void test01() {
        List<Skill> list = new ArrayList<>();
        final UUID DUMMY_UUID = UUID.fromString("0000-00-00-00-000000");
        Skill skill = new Skill(VALID_CATEGORY, DUMMY_UUID,"New skill");
        list.add(skill);

        when(skillRepository.getAll()).thenReturn(list);

        assertEquals(skillRepository.getAll().size(),1);
        assertEquals(skillRepository.getAll().get(0),skill);
    }
}
