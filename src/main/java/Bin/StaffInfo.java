package Bin;

import domain.Manager;
import domain.StaffUser;
import domain.User;

import java.util.UUID;

public class StaffInfo {

    public final UUID id;

    public final String userName;
    public final String password;
    public final String forename;
    public final String surname;
    public final User.SystemRole systemRole;
    public final StaffUser.JobRole staffRole;
    public final Manager manager;


    public StaffInfo(UUID id, String userName, String password, String forename, String surname, User.SystemRole systemRole, StaffUser.JobRole staffRole, Manager manager) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.forename = forename;
        this.surname = surname;
        this.systemRole = systemRole;
        this.staffRole = staffRole;
        this.manager = manager;
    }


}