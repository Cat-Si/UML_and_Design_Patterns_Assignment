package useCases.staffSkill;

import domain.Skill;
import domain.StaffUser;
import repositories.interfaces.BaseUserSkillRepository;
import useCases.BaseUseCase;

import java.util.List;
import java.util.Optional;

public class FindSkillsAssignedToStaff extends BaseUseCase {
    private final BaseUserSkillRepository USER_SKILL_REPOSITORY;

    public FindSkillsAssignedToStaff(BaseUserSkillRepository userSkillRepository) {
        USER_SKILL_REPOSITORY = userSkillRepository;
    }

    public Optional<List<Skill>> execute() {
        StaffUser su = (StaffUser) getNextRequestParameter();
        return USER_SKILL_REPOSITORY.getSkillsForStaff(su);
    }
}
