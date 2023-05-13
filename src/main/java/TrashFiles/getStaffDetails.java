package TrashFiles;

import domain.Manager;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import useCases.BaseUseCase;

public class getStaffDetails extends BaseUseCase {

    public String currentStaffDetails;

    public String StaffDetails() {
        String forename = (String) getNextRequestParameter();
        String surname = (String) getNextRequestParameter();
        String userName = (String) getNextRequestParameter();
        String password = (String) getNextRequestParameter();
        SystemRole systemRole = (SystemRole) getNextRequestParameter();
        JobRole staffRole = (JobRole) getNextRequestParameter();
        Manager manager = (Manager) getNextRequestParameter();
        return currentStaffDetails;
    }
}
