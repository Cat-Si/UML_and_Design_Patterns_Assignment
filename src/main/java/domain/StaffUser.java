package domain;

import java.util.Objects;
import java.util.UUID;



public class StaffUser extends User {
    private FullName fullName;
    private Manager currentManager;

    private JobRole staffRole;

    public StaffUser(UUID id, String username, String password, String forename, String surname, SystemRole STAFF_USER, JobRole staffRole, Manager currentManager) {
        super(id, username, password, forename, surname, STAFF_USER);
        this.staffRole = staffRole;
        this.currentManager = currentManager;
         this.fullName = new FullName(forename, surname);
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

    public enum JobRole {
        JUNIOR_DEVELOPER,
        MIDLEVEL_DEVELOPER,
        SENIOR_DEVELOPER;


    }

    @Override
    public String toString() {
        return String.format("%s(%s)",
                                    fullName, staffRole) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), fullName, staffRole);
    }
}
