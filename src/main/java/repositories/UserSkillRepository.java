package repositories;

import Exceptions.EntryAlreadyExistsException;
import domain.StaffUser;
import domain.UserSkill;
import globals.interfaces.DataProvider;
import repositories.interfaces.BaseUserSkillRepository;

import java.util.List;

public class UserSkillRepository implements BaseUserSkillRepository {

    private final DataProvider IN_MEMORY_DATABASE;
    public UserSkillRepository(DataProvider dataProvider) {
        IN_MEMORY_DATABASE = dataProvider;
    }

    public List<UserSkill> getAll() {
        return IN_MEMORY_DATABASE.getUserSkill();
    }

    public void add(UserSkill userSkill) throws EntryAlreadyExistsException {
        if (getAll().contains(userSkill)) {
            throw new EntryAlreadyExistsException("Staff User already exists");
        } else {
            getAll().add(userSkill);
        }
    }

    public void edit(UserSkill userSkill) {
        for (UserSkill us : getAll()) {
            if (us.getId().equals(userSkill.getId())) {
                us.setMySkill(userSkill.getMySkill());
            }
        }
    }
}
