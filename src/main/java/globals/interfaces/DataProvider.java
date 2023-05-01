package globals.interfaces;

import domain.*;

import java.util.List;

public interface DataProvider {
    List<Skill> getSkill();

    List<Category> getCategory();

    List<StaffUser> getStaffUser();

    List<Manager> getManager();

    List<UserSkill> getUserSkill();

}
