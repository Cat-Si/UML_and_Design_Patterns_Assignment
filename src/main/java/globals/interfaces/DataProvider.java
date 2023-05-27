package globals.interfaces;

import domain.*;
import domain.iterators.SkillCollection;

import java.util.List;

public interface DataProvider {
    SkillCollection getSkill();

    List<Category> getCategory();

    List<StaffUser> getStaffUser();

    List<Manager> getManager();

    List<UserSkill> getUserSkill();

}
