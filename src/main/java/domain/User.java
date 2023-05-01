package domain;

import java.util.Objects;
import java.util.UUID;

public abstract class User extends Validator{

    private UUID id;

    private String username;

    private String password;
    private FullName fullName;
    private SystemRole systemRole;

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public User(UUID id, String username, String password, String forename, String surname, SystemRole systemRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = new FullName(forename, surname);
        this.systemRole = systemRole;
        validate();
    }
    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }


    public SystemRole getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(SystemRole systemRole) {
        this.systemRole = systemRole;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName(){
        return fullName.getFirstName();
    }
    public String getSurname(){
        return fullName.getSurname();
    }




    private final void validate(){
        isNull(id,"id must not be null");
        isBlank(String.valueOf(id), "id must not be empty");
        isNull(username,"Username cannot be blank");
        isBlank(username, "username must not be empty");
        isNull(password, "Password cannot be blank");
        isBlank(password, "password must not to empty");
        isNull(systemRole, "Please select a role for the user");
        isNull(fullName, "Please Enter a name");
    }

    public enum SystemRole {
        ADMINISTRATOR,
        MANAGER,
        STAFF_USER;

    }
    @Override
    public String toString() {
        return String.format("Name: %s \n JobRole: %s", fullName, systemRole);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return  Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, systemRole);
    }
}
