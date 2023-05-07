package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.*;
import globals.interfaces.DataProvider;
import repositories.interfaces.BaseUserSkillRepository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserSkillRepository implements BaseUserSkillRepository {

    private final DataProvider IN_MEMORY_DATABASE;
    public UserSkillRepository(DataProvider dataProvider) {
        IN_MEMORY_DATABASE = dataProvider;
    }

    public List<UserSkill> getAll() {
        return IN_MEMORY_DATABASE.getUserSkill();
    }

   public void add(UUID uuid, StaffUser staff, Skill mySkill, UserSkill.SkillLevel strengthOfSkills, LocalDate expiry, String notes) throws EntryAlreadyExistsException {
        Optional<UserSkill> userSkill = doesUserExist(staff);

       if (userSkill.isPresent()) {
           if (userSkill.get().getCurrentSkills().contains(mySkill)) {
               throw new EntryAlreadyExistsException("skill already assigned to staff member");
           } else {
               userSkill.get().addSkill(mySkill);
           }
       } else {
           getAll().add(new UserSkill(uuid, staff, mySkill, strengthOfSkills, expiry, notes));
       }
   }

    public Optional<List<Skill>> getSkillsForStaff(StaffUser s) {
        for (UserSkill us : getAll()) {
            if (us.getStaff().equals(s)) {
                return Optional.of(us.getCurrentSkills());
            }
        }
        return  Optional.empty();
    }

    public void removeSkillFromStaff(StaffUser u, Skill s) throws IllegalArgumentException {
        Optional<UserSkill> userSkill = doesUserExist(u);

        if (userSkill.isEmpty()) {
            throw new IllegalArgumentException("No Staff Member Exists");
        }

        if (userSkill.get().getCurrentSkills().contains(s)) {
            userSkill.get().removeSkill(s);
        } else {
            throw new IllegalArgumentException("Staff Member does not have skill to remove");
        }

        if (userSkill.get().getCurrentSkills().isEmpty()) {
            getAll().remove(userSkill.get());
        }
    }

    private Optional<UserSkill> doesUserExist(StaffUser u) {
        for (UserSkill us : getAll()) {
            if (us.getStaff().equals(u)) {
                return Optional.of(us);
            }
        }
        return Optional.empty();
    }

}
