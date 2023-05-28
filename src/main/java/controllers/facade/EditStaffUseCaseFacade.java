package controllers.facade;

import domain.Manager;
import domain.StaffUser;
import domain.UserSkill;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import globals.Ioc_Container;
import useCases.manager.GetAllManagers;
import useCases.staff.EditStaff;
import useCases.staff.GetAllStaff;
import useCases.staffSkill.FindSkillsAssignedToStaff;

import java.util.List;
import java.util.Optional;

public class EditStaffUseCaseFacade {

    private final GetAllStaff getAllStaff = new GetAllStaff(Ioc_Container.getInstance().getStaffUserRepository());
    private final GetAllManagers getAllManagers = new GetAllManagers(Ioc_Container.getInstance().getManagerRepository());
    private final EditStaff editStaff = new EditStaff(Ioc_Container.getInstance().getStaffUserRepository());
    private final FindSkillsAssignedToStaff findSkillsAssignedToStaff = new FindSkillsAssignedToStaff(Ioc_Container.getInstance().getUserSkillRepository());

    public List<StaffUser> getAllStaff() {
        return getAllStaff.execute();
    }

    public List<Manager> getAllManagers() {
        return getAllManagers.execute();
    }

    public void  editStaff(StaffUser selectedUser, String forname, String surname, String user, String pass, SystemRole selectedSystemRole, JobRole selectedJobRole, Manager selectedManager) {
            editStaff.add(selectedUser.getId());
            editStaff.add(forname);
            editStaff.add(surname);
            editStaff.add(user);
            editStaff.add(pass);
            editStaff.add(selectedSystemRole);
            editStaff.add(selectedJobRole);
            editStaff.add(selectedManager);
            editStaff.execute();
    }

    public  Optional<List<UserSkill>> findSkillsAssignedToStaff(StaffUser staff) {
        findSkillsAssignedToStaff.add(staff);
        return findSkillsAssignedToStaff.execute();
    }
}
