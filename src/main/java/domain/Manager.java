package domain;

import java.util.ArrayList;
import java.util.UUID;

public class Manager extends User{


    public Manager(UUID id, String username, String password, FullName fullName, systemRole userRole, StaffUser staffMember) {
        super(id, username, password, fullName, userRole);
        staffGroup =  new ArrayList<>();
        addStaff(staffMember);


    }

    private ArrayList<StaffUser> staffGroup;


    public void addStaff(StaffUser staffMember) {
        staffGroup.add(staffMember);
    }

    public void removeStaff(StaffUser staffMember) {
        staffGroup.remove(staffMember);
    }




}
