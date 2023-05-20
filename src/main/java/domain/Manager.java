package domain;

import domain.enumerators.SystemRole;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Manager extends User{



    public Manager(UUID id, String forename, String surname, String username, String password, SystemRole systemRole) {
        super(id, forename, surname, username, password, systemRole);
        staffGroup =  new ArrayList<>();
    }


    private ArrayList<StaffUser> staffGroup;


    public void addStaff(StaffUser staffMember) {
        staffGroup.add(staffMember);
    }

    public void removeStaff(StaffUser staffMember) {
        staffGroup.remove(staffMember);
    }

    public  String toString() {
        return String.format("%s (%s)",
                getFullName(), getSystemRole()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), getSystemRole());
    }


}
