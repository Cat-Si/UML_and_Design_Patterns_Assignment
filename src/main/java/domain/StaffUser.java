package domain;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;

import java.util.Objects;
import java.util.UUID;



public class StaffUser extends User {


    private Manager currentManager;

    private JobRole staffRole;

    public StaffUser(UUID id, String forename, String surname, String username, String password, SystemRole systemRole, JobRole staffRole, Manager currentManager) {
        super(id, forename, surname, username, password, systemRole);
        this.staffRole = staffRole;
        this.currentManager = currentManager;
        validate();
    }

    private final void validate(){
        isNull(staffRole,"staff role must not be null");
        isNull(currentManager, "manager must not be null");
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
        return String.format("%s (%s)",
                                    getFullName(), getStaffRole()) ;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StaffUser)) return false;
        StaffUser staff = (StaffUser) o;
        return Objects.equals(getUsername(),staff.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername());
    }
}
