package domain;

import java.util.ArrayList;
import java.util.UUID;

public class Manager extends User{

    private FullName fullName;

    public Manager(UUID id, String username, String password, String forename, String surname, SystemRole MANAGER, StaffUser staffMember) {
        super(id, username, password, forename, surname, MANAGER);
        this.fullName = new FullName(forename, surname);
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

    public String toString() {
        return String.format("%s",
                fullName) ;
    }


}
