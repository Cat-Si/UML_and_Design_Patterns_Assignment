package domain;

import java.util.ArrayList;
import java.util.UUID;

public class Manager extends User{

        private ArrayList<StaffUser> staffGroup;

    public Manager(UUID id, String username, String password, FullName fullName, systemRole userRole) {
        super(id, username, password, fullName, userRole);
    }

    public Manager() {
        super();
    }

    public void addStaff(StaffUser staffUser) {staffGroup.add(staffUser);};


}
