package useCases.staff;

import Exceptions.EntryAlreadyExistsException;
import TrashFiles.getStaffDetails;
import domain.*;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import useCases.utility.UUIDGenerator;
import repositories.interfaces.BaseStaffUserRepository;
import useCases.BaseUseCase;

public class AddNewStaff extends BaseUseCase {
     private final BaseStaffUserRepository STAFF_REPOSITORY;

     private getStaffDetails staffDetails;

    public AddNewStaff(BaseStaffUserRepository staffUserRepository) {
        STAFF_REPOSITORY = staffUserRepository;
    }

    public void execute() throws IllegalArgumentException, EntryAlreadyExistsException {
        String forename = (String) getNextRequestParameter();
        String surname = (String) getNextRequestParameter();
        String userName = (String) getNextRequestParameter();
        String password = (String) getNextRequestParameter();
        SystemRole systemRole = (SystemRole) getNextRequestParameter();
        JobRole staffRole = (JobRole) getNextRequestParameter();
        Manager manager = (Manager) getNextRequestParameter();
        StaffUser newStaffUser = new StaffUser(UUIDGenerator.generate(), forename, surname,userName, password,
                    systemRole,staffRole,manager);
        try {
            STAFF_REPOSITORY.add(newStaffUser);
        } catch (EntryAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
