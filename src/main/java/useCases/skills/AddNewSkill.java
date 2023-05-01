package useCases.skills;

import Exceptions.EntryAlreadyExistsException;
import domain.Category;
import domain.Skill;
import domain.utility.UUIDGenerator;
import repositories.interfaces.BaseSkillRepository;
import useCases.BaseUseCase;

public class AddNewSkill extends BaseUseCase {

    private final BaseSkillRepository SKILL_REPOSITORY;
    public AddNewSkill(BaseSkillRepository skillRepository){
        SKILL_REPOSITORY = skillRepository;
    }

    public void execute() throws IllegalArgumentException, EntryAlreadyExistsException {
        Category category = (Category) getNextRequestParameter();
        String skillName = (String) getNextRequestParameter();
        Skill skill =new Skill(category, UUIDGenerator.generate(), skillName);
        SKILL_REPOSITORY.add(skill);
    }
}

