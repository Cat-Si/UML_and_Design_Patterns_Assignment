package useCases.skills;

import Exceptions.EntryAlreadyExistsException;
import domain.Category;
import domain.Skill;
import domain.validationStrategy.ValidationFactory;
import repositories.interfaces.BaseSkillRepository;
import useCases.BaseUseCase;
import useCases.UseCaseCommand;
import useCases.utility.UUIDGenerator;

public class AddNewSkill extends BaseUseCase implements UseCaseCommand {

    private final BaseSkillRepository SKILL_REPOSITORY;
    public AddNewSkill(BaseSkillRepository skillRepository){
        SKILL_REPOSITORY = skillRepository;
    }

    public void execute() throws IllegalArgumentException, EntryAlreadyExistsException {
        String skillName = (String) getNextRequestParameter();
        Category category = (Category) getNextRequestParameter();
        Skill skill = ValidationFactory.createSkill(category, UUIDGenerator.generate(), skillName);
        SKILL_REPOSITORY.add(skill);
    }
}

