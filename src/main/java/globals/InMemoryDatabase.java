package globals;

import domain.Skill;
import domain.StaffUser;
import domain.UserSkill;
import globals.interfaces.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase implements DataProvider {

    private static List<Skill> skill;
    private static List<StaffUser> staffUser;
    private static List<UserSkill> userSkill;

    private final ArrayList<Skill> currentSkills = new ArrayList<>();


    public InMemoryDatabase(List<Skill> skill, List<StaffUser> staffUser, List<UserSkill> userSkill) {
        this.skill = skill;
        this.staffUser = staffUser;
        this.userSkill = userSkill;
    }


    public List<Skill> getSkill() { return skill; }

    public List<StaffUser> getStaffUser() { return staffUser; }

    public  List<UserSkill> getUserSkill() { return userSkill; }




}
