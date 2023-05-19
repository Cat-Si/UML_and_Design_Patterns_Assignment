package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.*;
import domain.enumerators.SkillLevel;
import globals.interfaces.DataProvider;
import repositories.interfaces.BaseUserSkillRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public void add(UUID uuid, StaffUser staff, Skill mySkill, SkillLevel strengthOfSkills, LocalDate expiry, String notes) throws EntryAlreadyExistsException {
        Optional<UserSkill> userSkill = doesUserExist(staff);

        if (userSkill.isPresent()) {
            if (userSkill.get().getCurrentSkills().contains(userSkill.get())) {
                throw new EntryAlreadyExistsException("skill already assigned to staff member");
            } else {
                userSkill.get().addSkill(userSkill.get());
            }
        } else {
            getAll().add(new UserSkill(uuid, staff, mySkill, strengthOfSkills, expiry, notes));
        }
    }

    public Optional<List<UserSkill>> getSkillsForStaff(StaffUser u) {
        List<UserSkill> userSkills = new ArrayList<>();

        for (UserSkill us : getAll()) {
            if ((us.getStaff().equals(u))) {
                userSkills.add(us);
            }
        }
        if(userSkills.size()==0) return Optional.empty();

        return Optional.ofNullable(userSkills);
    }

    public void edit(UserSkill userSkill) {
        for (UserSkill us : getAll()) {
            if (us.getId().equals(userSkill.getId())) {
                us.setSkill(userSkill.getSkill());
            }
        }
    }
    public void removeSkillFromStaff(StaffUser u, UserSkill s) throws IllegalArgumentException {
        Optional<UserSkill> userSkill = doesUserExist(u);

        if (userSkill.isEmpty()) {
            throw new IllegalArgumentException("No User Skill Exists");
        }

        if (userSkill.get().getCurrentSkills().contains(s)) {
            userSkill.get().removeSkill(userSkill.get());
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
                return Optional.ofNullable(us);
            }
        }
        return Optional.empty();
    }

}
