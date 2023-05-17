package useCases.skills;

import domain.Category;
import domain.Skill;
import domain.enumerators.SkillLevel;
import repositories.interfaces.BaseSkillRepository;
import useCases.BaseUseCase;
import java.util.UUID;

public class EditSkill extends BaseUseCase {

    private final BaseSkillRepository SKILL_REPOSITORY;
    public EditSkill(BaseSkillRepository skillRepository){
        SKILL_REPOSITORY = skillRepository;
    }

    public void execute() throws IllegalArgumentException{
        Category category = (Category) getNextRequestParameter();
        UUID id = (UUID) getNextRequestParameter();
        String skillName = (String) getNextRequestParameter();
        Skill editedSkill =new Skill(category, id, skillName);
        SKILL_REPOSITORY.edit(editedSkill);
    }
}
