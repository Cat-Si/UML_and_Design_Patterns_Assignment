package domain;
import java.util.Objects;
import java.util.UUID;



public class StaffUser extends User {


    private Manager currentManager;

    private JobRole staffRole;

    public StaffUser(UUID id, String username, String password, String forename, String surname, SystemRole systemRole, JobRole staffRole, Manager currentManager) {
        super(id, username, password, forename, surname, systemRole);
        this.staffRole = staffRole;
        this.currentManager = currentManager;
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
                                    getFullName(), getSystemRole()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), getSystemRole());
    }
}
