package useCases.skills;

import domain.Category;
import domain.Skill;
import domain.validationStrategy.ValidationFactory;
import repositories.interfaces.BaseSkillRepository;
import useCases.BaseUseCase;
import useCases.UseCaseCommand;

import java.util.UUID;

public class EditSkill extends BaseUseCase implements UseCaseCommand {

    private final BaseSkillRepository SKILL_REPOSITORY;
    public EditSkill(BaseSkillRepository skillRepository){
        SKILL_REPOSITORY = skillRepository;
    }

    public void execute() throws IllegalArgumentException{
        Category category = (Category) getNextRequestParameter();
        UUID id = (UUID) getNextRequestParameter();
        String skillName = (String) getNextRequestParameter();
        Skill editedSkill = ValidationFactory.createSkill(category, id, skillName);
        SKILL_REPOSITORY.edit(editedSkill);
    }
}
