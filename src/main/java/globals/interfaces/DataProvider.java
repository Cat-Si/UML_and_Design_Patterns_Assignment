package globals.interfaces;

import domain.Skill;
import domain.StaffUser;
import domain.UserSkill;

import java.util.List;

public interface DataProvider {
    List<Skill> getSkill();

    List<StaffUser> getStaffUser();

    List<UserSkill> getUserSkill();

}
