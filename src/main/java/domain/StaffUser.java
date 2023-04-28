package domain;

import java.util.UUID;

public class StaffUser extends User {
    private Manager currentManager;

    private jobRole staffRole;

    public StaffUser(UUID id, String username, String password, FullName fullName, systemRole userRole, jobRole staffRole, Manager currentManager) {
        super(id, username, password, fullName, userRole);
        this.staffRole = staffRole;
        this.currentManager = currentManager;
    }



    public jobRole getStaffRole() {
        return staffRole;
    }

    public Manager getCurrentManager() {
        return currentManager;
    }

    public void setCurrentManager(Manager currentManager) {

        this.currentManager = currentManager;
    }

    enum jobRole {
        JUNIOR_DEVELOPER,
        MIDLEVEL_DEVELOPER,
        SENIOR_DEVELOPER;


    }

}
