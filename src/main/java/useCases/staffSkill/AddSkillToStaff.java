package useCases.staffSkill;

import Exceptions.EntryAlreadyExistsException;
import domain.Skill;
import domain.StaffUser;
import domain.enumerators.SkillLevel;
import repositories.interfaces.BaseUserSkillRepository;
import useCases.BaseUseCase;
import useCases.UseCaseCommand;
import useCases.utility.UUIDGenerator;

import java.time.LocalDate;

public class AddSkillToStaff extends BaseUseCase implements UseCaseCommand {
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
        USER_SKILL_REPOSITORY.add(UUIDGenerator.generate(), staffUser, skill, skillStrength, expiry, notes);
    }
}
