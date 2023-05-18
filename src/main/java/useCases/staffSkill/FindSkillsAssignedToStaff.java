package useCases.staffSkill;

import domain.Skill;
import domain.StaffUser;
import domain.UserSkill;
import domain.enumerators.SkillLevel;
import repositories.interfaces.BaseUserSkillRepository;
import useCases.BaseUseCase;

import java.util.List;
import java.util.Optional;

public class FindSkillsAssignedToStaff extends BaseUseCase {
    private final BaseUserSkillRepository USER_SKILL_REPOSITORY;

    public FindSkillsAssignedToStaff(BaseUserSkillRepository userSkillRepository) {
        USER_SKILL_REPOSITORY = userSkillRepository;
    }

    public Optional<List<UserSkill>> execute() {
        StaffUser u = (StaffUser) getNextRequestParameter();
        return USER_SKILL_REPOSITORY.getSkillsForStaff(u);
    }
}
