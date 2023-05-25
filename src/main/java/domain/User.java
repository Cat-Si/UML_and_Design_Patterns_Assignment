package domain;

import domain.enumerators.SystemRole;

import java.util.Objects;
import java.util.UUID;

public abstract class User extends Validator{

    private UUID id;

    private FullName fullName;
    private String username;

    private String forename;

    private String surname;

    private String password;
    private SystemRole systemRole;

    public FullName getFullName() {
        return fullName;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public User(UUID id, String forename, String surname, String username, String password, SystemRole systemRole) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.fullName = new FullName(forename, surname);
        this.username = username;
        this.password = password;
        this.systemRole = systemRole;
        validate();
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }


    public UUID getId() {
        return id;
    }
    public String getFirstName(){
        return fullName.getFirstName();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getSurname(){
        return fullName.getSurname();
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




    private final void validate(){
        isNull(id,"id must not be null");
        isBlank(String.valueOf(id), "id must not be empty");
        isNull(username,"Username cannot be blank");
        isBlank(username, "username must not be empty");
        isNull(password, "Password cannot be blank");
        isBlank(password, "password must not be empty");
        isNull(systemRole, "Please select a role for the user");
        isNull(fullName, "Please Enter a name");
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
