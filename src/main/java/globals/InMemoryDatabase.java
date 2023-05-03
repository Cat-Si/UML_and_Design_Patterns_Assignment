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




    public InMemoryDatabase(List<Category> category, List<Skill> skill, List<Manager> manager, List<StaffUser> staffUser, List<UserSkill> userSkill) {
        this.category = category;
        this.skill = skill;
        this.manager = manager;
        this.staffUser = staffUser;
        this.userSkill = userSkill;
    }


    public List<Manager> getManager() {
        return manager;
    }
    public List<Skill> getSkill() { return skill; }

    public List<Category> getCategory() { return category;}

    public List<StaffUser> getStaffUser() { return staffUser; }

    public  List<UserSkill> getUserSkill() { return userSkill; }




}
