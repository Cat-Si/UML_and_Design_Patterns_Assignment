package domain;

import java.util.Objects;
import java.util.UUID;

public class LoggedInUser extends User {

    private Manager currentManager;

    private StaffUser.JobRole staffRole;

    public LoggedInUser(UUID id, String forename, String surname, String username, String password, User.SystemRole systemRole, StaffUser.JobRole staffRole) {
        super(id, forename, surname, username, password, systemRole);
        this.staffRole = staffRole;

    }



    public void setStaffRole(StaffUser.JobRole staffRole) {
        this.staffRole = staffRole;
    }


    public StaffUser.JobRole getStaffRole() {
        return staffRole;

    }


    public Manager getCurrentManager() {
        return currentManager;
    }

    public void setCurrentManager(Manager currentManager) {

        this.currentManager = currentManager;
    }

    public enum JobRole {
        JUNIOR_DEVELOPER,
        MIDLEVEL_DEVELOPER,
        SENIOR_DEVELOPER;


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
