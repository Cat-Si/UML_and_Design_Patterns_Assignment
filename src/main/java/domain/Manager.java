package domain;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Manager extends User{



    public Manager(UUID id, String username, String password, String forename, String surname, SystemRole systemRole, StaffUser staffMember) {
        super(id, username, password, forename, surname, systemRole);
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
        return String.format("%s (%s)",
                getFullName(), getSystemRole()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), getSystemRole());
    }


}
