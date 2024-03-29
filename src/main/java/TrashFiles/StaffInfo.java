package TrashFiles;

import domain.Manager;
import domain.StaffUser;
import domain.User;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;

import java.util.UUID;

public class StaffInfo {

    public final UUID id;

    public final String userName;
    public final String password;
    public final String forename;
    public final String surname;
    public final SystemRole systemRole;
    public final JobRole staffRole;
    public final Manager manager;


    public StaffInfo(UUID id, String userName, String password, String forename, String surname, SystemRole systemRole, JobRole staffRole, Manager manager) {
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