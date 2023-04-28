package domain;

import java.util.ArrayList;
import java.util.UUID;

public class Manager extends User{

    public Manager(UUID id, String username, String password, FullName fullName, systemRole userRole) {
        super(id, username, password, fullName, userRole);
    }

        private ArrayList<StaffUser> staffGroup;




    public void addStaff(StaffUser staffUser) {staffGroup.add(staffUser);};


}
