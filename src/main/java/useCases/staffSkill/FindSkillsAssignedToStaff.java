package useCases.staffSkill;


import domain.StaffUser;
import domain.UserSkill;
import repositories.interfaces.BaseUserSkillRepository;
import useCases.BaseUseCase;
import useCases.staffSkill.staffSkillFactory.UseCaseCommandFind;

import java.util.List;
import java.util.Optional;

public class FindSkillsAssignedToStaff extends BaseUseCase implements UseCaseCommandFind {
    private final BaseUserSkillRepository USER_SKILL_REPOSITORY;

    public FindSkillsAssignedToStaff(BaseUserSkillRepository userSkillRepository) {
        USER_SKILL_REPOSITORY = userSkillRepository;
    }


    public Optional<List<UserSkill>> execute() throws IllegalArgumentException{
        StaffUser u = (StaffUser) getNextRequestParameter();
        return USER_SKILL_REPOSITORY.getSkillsForStaff(u);
    }
}
