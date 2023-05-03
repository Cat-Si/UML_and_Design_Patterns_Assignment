package domain;
import java.util.Objects;
import java.util.UUID;



public class StaffUser extends User {


    private Manager currentManager;

    private JobRole staffRole;

    public StaffUser(UUID id, String forename, String surname, String username, String password,  SystemRole systemRole, JobRole staffRole, Manager currentManager) {
        super(id, forename, surname, username, password, systemRole);
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
                                    getFullName(), getStaffRole()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), getSystemRole());
    }
}
