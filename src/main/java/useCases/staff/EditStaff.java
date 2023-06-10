package useCases.staff;

import domain.Manager;
import domain.StaffUser;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import repositories.interfaces.BaseStaffUserRepository;
import useCases.BaseUseCase;
import useCases.UseCaseCommand;

import java.util.UUID;


public class EditStaff extends BaseUseCase implements UseCaseCommand {

    private final BaseStaffUserRepository STAFF_REPOSITORY;

    public EditStaff(BaseStaffUserRepository staffUserRepository) {
        this.STAFF_REPOSITORY = staffUserRepository;
    }

    public void execute() throws IllegalArgumentException {
        UUID id = (UUID) getNextRequestParameter();
        String forename = (String) getNextRequestParameter();
        String surname = (String) getNextRequestParameter();
        String userName = (String) getNextRequestParameter();
        String password = (String) getNextRequestParameter();
        SystemRole systemRole = (SystemRole) getNextRequestParameter();
        JobRole staffRole = (JobRole) getNextRequestParameter();
        Manager manager = (Manager) getNextRequestParameter();
        StaffUser editedStaff = new StaffUser(id, forename, surname,userName, password,
                systemRole,staffRole,manager);
        STAFF_REPOSITORY.edit(editedStaff);

    }


}
