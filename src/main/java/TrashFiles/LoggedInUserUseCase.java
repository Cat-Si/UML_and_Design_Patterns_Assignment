package TrashFiles;

import domain.Manager;
import domain.StaffUser;
import domain.User;
import repositories.interfaces.BaseStaffUserRepository;
import useCases.BaseUseCase;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import java.util.UUID;

public class LoggedInUserUseCase extends BaseUseCase {

    private final BaseStaffUserRepository STAFF_REPOSITORY;

    public LoggedInUserUseCase(BaseStaffUserRepository staffUserRepository) {
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
        StaffUser loggedInUser = new StaffUser(id, forename, surname,userName, password,
                systemRole,staffRole,manager);
        STAFF_REPOSITORY.edit(loggedInUser);

    }
}
