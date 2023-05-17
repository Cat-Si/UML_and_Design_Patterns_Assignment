package useCases.skills;

import Exceptions.EntryAlreadyExistsException;
import domain.Category;
import domain.Skill;
import domain.enumerators.SkillLevel;
import useCases.utility.UUIDGenerator;
import repositories.interfaces.BaseSkillRepository;
import useCases.BaseUseCase;

public class AddNewSkill extends BaseUseCase {

    private final BaseSkillRepository SKILL_REPOSITORY;
    public AddNewSkill(BaseSkillRepository skillRepository){
        SKILL_REPOSITORY = skillRepository;
    }

    public void execute() throws IllegalArgumentException, EntryAlreadyExistsException {
        String skillName = (String) getNextRequestParameter();
        Category category = (Category) getNextRequestParameter();
        SkillLevel strengthOfSkills = (SkillLevel) getNextRequestParameter();
        Skill skill =new Skill(category, UUIDGenerator.generate(), skillName);
        SKILL_REPOSITORY.add(skill);
    }
}

