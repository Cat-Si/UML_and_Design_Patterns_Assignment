package domain;

import java.util.UUID;

public class StaffUser extends User {
    private Manager currentManager;

    public StaffUser(UUID id, String username, String password, FullName fullName, systemRole userRole, jobRole staffRole, Manager currentManager) {
        super(id, username, password, fullName, userRole);
        this.staffRole = staffRole;
        this.currentManager = currentManager;
    }
    private jobRole staffRole;




    public Manager getCurrentManager() {
        return currentManager;
    }

    public void setM(Manager currentManager) {

        this.currentManager = currentManager;
    }

    enum jobRole {
        JUNIOR_DEVELOPER,
        MIDLEVEL_DEVELOPER,
        SENIOR_DEVELOPER;


    }

}
