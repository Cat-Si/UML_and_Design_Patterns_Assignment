package useCases.staffSkill;

import domain.Skill;
import domain.StaffUser;
import domain.UserSkill;
import repositories.interfaces.BaseUserSkillRepository;
import useCases.BaseUseCase;

public class RemoveSkillAssignedToStaff extends BaseUseCase {

    private final BaseUserSkillRepository USER_SKILL_REPOSITORY;

    public RemoveSkillAssignedToStaff(BaseUserSkillRepository userSkillRepository) {
        USER_SKILL_REPOSITORY = userSkillRepository;
    }

    public void execute() throws IllegalArgumentException {
        StaffUser u = (StaffUser) getNextRequestParameter();
        Skill s = (Skill) getNextRequestParameter();
       /* SkillLevel skillStrength = (SkillLevel) getNextRequestParameter();
        LocalDate expiry = (LocalDate) getNextRequestParameter();
        String notes = (String) getNextRequestParameter();*/
        USER_SKILL_REPOSITORY.removeSkillFromStaff(u,s);
    }
}
