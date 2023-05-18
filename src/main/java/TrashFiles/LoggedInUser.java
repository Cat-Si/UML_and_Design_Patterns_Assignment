package TrashFiles;

import domain.Manager;
import domain.StaffUser;
import domain.User;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;

import java.util.Objects;
import java.util.UUID;

public class LoggedInUser extends User {

    private Manager currentManager;

    private JobRole staffRole;

    public LoggedInUser(UUID id, String forename, String surname, String username, String password, SystemRole systemRole, JobRole staffRole) {
        super(id, forename, surname, username, password, systemRole);
        this.staffRole = staffRole;

    }



    public void setStaffRole(JobRole staffRole) {
        this.staffRole = staffRole;
    }


    public JobRole getStaffRole() {
        return staffRole;

    }


    public Manager getCurrentManager() {
        return currentManager;
    }

    public void setCurrentManager(Manager currentManager) {

        this.currentManager = currentManager;
    }



    @Override
    public String toString() {
        return String.format("%s(%s)",
                getFullName(), getStaffRole()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), getSystemRole());
    }
}
