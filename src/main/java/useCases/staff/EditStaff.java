package useCases.staff;

import Bin.GetStaffCreationInfo;
import domain.Manager;
import domain.StaffUser;
import domain.User;
import repositories.interfaces.BaseStaffUserRepository;
import useCases.BaseUseCase;

import java.util.UUID;


public class EditStaff extends BaseUseCase {

    private final BaseStaffUserRepository STAFF_REPOSITORY;

    public EditStaff(BaseStaffUserRepository staffUserRepository) {
        STAFF_REPOSITORY = staffUserRepository;
    }

    public void execute() throws IllegalArgumentException {
        UUID id = (UUID) getNextRequestParameter();
        String userName = (String) getNextRequestParameter();
        String password = (String) getNextRequestParameter();
        String forename = (String) getNextRequestParameter();
        String surname = (String) getNextRequestParameter();
        User.SystemRole systemRole = (User.SystemRole) getNextRequestParameter();
        StaffUser.JobRole staffRole = (StaffUser.JobRole) getNextRequestParameter();
        Manager manager = (Manager) getNextRequestParameter();
        StaffUser editedStaff = new StaffUser(id, userName, password, forename,surname,
                systemRole,staffRole,manager);
        STAFF_REPOSITORY.edit(editedStaff);

    }


}
