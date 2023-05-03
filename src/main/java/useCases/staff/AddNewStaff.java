package useCases.staff;

import Exceptions.EntryAlreadyExistsException;
import domain.Manager;
import domain.StaffUser;
import domain.User;
import useCases.utility.UUIDGenerator;
import repositories.interfaces.BaseStaffUserRepository;
import useCases.BaseUseCase;

public class AddNewStaff extends BaseUseCase {
     private final BaseStaffUserRepository STAFF_REPOSITORY;

    public AddNewStaff(BaseStaffUserRepository staffUserRepository) {
        STAFF_REPOSITORY = staffUserRepository;
    }

    public void execute() throws IllegalArgumentException, EntryAlreadyExistsException {
        String forename = (String) getNextRequestParameter();
        String surname = (String) getNextRequestParameter();
        String userName = (String) getNextRequestParameter();
        String password = (String) getNextRequestParameter();
        User.SystemRole systemRole = (User.SystemRole) getNextRequestParameter();
        StaffUser.JobRole staffRole = (StaffUser.JobRole) getNextRequestParameter();
        Manager manager = (Manager) getNextRequestParameter();
        StaffUser newStaffUser = new StaffUser(UUIDGenerator.generate(), forename, surname,userName, password, systemRole, staffRole, manager);
        try {
            STAFF_REPOSITORY.add(newStaffUser);
        } catch (EntryAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
