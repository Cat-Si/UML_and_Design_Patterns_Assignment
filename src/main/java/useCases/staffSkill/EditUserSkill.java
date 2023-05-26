package useCases.staffSkill;

import domain.Category;
import domain.Skill;

import domain.StaffUser;
import domain.UserSkill;
import domain.enumerators.SkillLevel;
import repositories.interfaces.BaseUserSkillRepository;
import useCases.BaseUseCase;
import useCases.UseCaseCommand;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;



    public class EditUserSkill extends BaseUseCase implements UseCaseCommand {

        private final BaseUserSkillRepository USER_SKILL_REPOSITORY;
        public EditUserSkill(BaseUserSkillRepository userSkillRepository){
            USER_SKILL_REPOSITORY = userSkillRepository;
        }

        public void execute() throws IllegalArgumentException{
            UUID id = (UUID) getNextRequestParameter();
            StaffUser staff = (StaffUser) getNextRequestParameter();
            Skill skill = (Skill) getNextRequestParameter();
            SkillLevel strengthOfSkill = (SkillLevel) getNextRequestParameter();
            LocalDate expiry = (LocalDate) getNextRequestParameter();
            String notes = (String) getNextRequestParameter();
            UserSkill editedUserSkill =new UserSkill(id, staff, skill, strengthOfSkill, expiry, notes);
            USER_SKILL_REPOSITORY.edit(editedUserSkill);
        }
    }

