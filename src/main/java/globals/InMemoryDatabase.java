package globals;

import domain.*;
import globals.interfaces.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase implements DataProvider {

    private static List<Skill> skill;
    private static List<Manager> manager;
    private static List<Category> category;
    private static List<StaffUser> staffUser;
    private static List<UserSkill> userSkill;




    public InMemoryDatabase(List<Skill> skill, List<Category> category, List<StaffUser> staffUser, List<Manager> manager, List<UserSkill> userSkill) {
        this.skill = skill;
        this.category = category;
        this.staffUser = staffUser;
        this.userSkill = userSkill;
        this.manager = manager;
    }


    public List<Manager> getManager() {
        return manager;
    }
    public List<Skill> getSkill() { return skill; }

    public List<Category> getCategory() { return category;}

    public List<StaffUser> getStaffUser() { return staffUser; }

    public  List<UserSkill> getUserSkill() { return userSkill; }




}
