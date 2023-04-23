package domain;

import java.util.UUID;

public class StaffUser extends User {

    public StaffUser(UUID id, String username, String password, FullName fullName, systemRole userRole, jobRole staffRole) {
        super(id, username, password, fullName, userRole);
        this.staffRole = staffRole;
    }

    enum jobRole {
        JUNIOR_DEVELOPER,
        MIDLEVEL_DEVELOPER,
        SENIOR_DEVELOPER;

    }

    private jobRole staffRole;

}
