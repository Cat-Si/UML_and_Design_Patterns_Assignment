package globals.interfaces;

import domain.Skill;
import domain.StaffUser;

import java.util.List;

public interface DataProvider {
    List<Skill> getSkills();

    List<StaffUser> getStaffUsers();

}
