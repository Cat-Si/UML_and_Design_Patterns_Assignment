package useCases.staffSkill;

import Exceptions.EntryAlreadyExistsException;
import domain.Skill;
import domain.SkillLevel;
import domain.StaffUser;
import domain.UserSkill;
import domain.utility.UUIDGenerator;
import repositories.interfaces.BaseUserSkillRepository;
import useCases.BaseUseCase;

import java.time.LocalDate;

public class AddSkillToStaff extends BaseUseCase {
    private final BaseUserSkillRepository USER_SKILL_REPOSITORY;

    public AddSkillToStaff(BaseUserSkillRepository userSkillRepository) {
        USER_SKILL_REPOSITORY = userSkillRepository;
    }

    public void execute() throws EntryAlreadyExistsException {
        StaffUser staffUser = (StaffUser) getNextRequestParameter();
        Skill skill = (Skill) getNextRequestParameter();
        SkillLevel skillStrength = (SkillLevel) getNextRequestParameter();
        LocalDate expiry = (LocalDate) getNextRequestParameter();
        String notes = (String) getNextRequestParameter();
        USER_SKILL_REPOSITORY.add(UUIDGenerator.generate(), skill, staffUser, skillStrength, expiry, notes);
    }
}
