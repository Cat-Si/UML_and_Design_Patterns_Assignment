package useCases.skills;

import domain.Skill;
import repositories.interfaces.BaseSkillRepository;
import useCases.BaseUseCase;

import java.util.List;

public class GetAllSkills extends BaseUseCase {

    private final BaseSkillRepository SKILL_REPOSITORY;

    public GetAllSkills(BaseSkillRepository skillRepository) {
        SKILL_REPOSITORY = skillRepository;
    }
    public List<Skill> execute() {
        return SKILL_REPOSITORY.getAll();
    }
}
