package globals;

import domain.Category;
import domain.Manager;
import domain.StaffUser;
import domain.UserSkill;
import domain.iterators.SkillCollection;
import globals.interfaces.DataProvider;

import java.util.List;

public class InMemoryDatabase implements DataProvider {

    private static SkillCollection skill;
    private static List<Manager> manager;
    private static List<Category> category;
    private static List<StaffUser> staffUser;
    private static List<UserSkill> userSkill;




    public InMemoryDatabase(List<Category> category,SkillCollection skill, List<Manager> manager, List<StaffUser> staffUser, List<UserSkill> userSkill) {
        this.category = category;
        this.skill = skill;
        this.manager = manager;
        this.staffUser = staffUser;
        this.userSkill = userSkill;
    }


    public List<Manager> getManager() {
        return manager;
    }
    public SkillCollection getSkill() { return skill; }

    public List<Category> getCategory() { return category;}

    public List<StaffUser> getStaffUser() { return staffUser; }

    public  List<UserSkill> getUserSkill() { return userSkill; }




}
