package domain;

import java.util.UUID;

public class StaffUser extends User {
    private jobRole staffRole;

   private Manager m = new Manager();

    public Manager getM() {
        return m;
    }

    public void setM(Manager m) {
        this.m = m;
    }


    public StaffUser(UUID id, String username, String password, FullName fullName, systemRole userRole, jobRole staffRole) {
        super(id, username, password, fullName, userRole);
        this.staffRole = staffRole;
    }
    enum jobRole {
        JUNIOR_DEVELOPER,
        MIDLEVEL_DEVELOPER,
        SENIOR_DEVELOPER;


    }

}
