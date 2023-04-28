package domain;

import java.util.UUID;

public abstract class User extends Validator{

    private UUID id;
    private String username;
    private String password;
    private FullName fullName;
    private systemRole userRole;

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }




    public User(UUID id, String username, String password, FullName fullName, systemRole userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.userRole = userRole;
        validate();
    }



    private final void validate(){
        isNull(id,"id must not be null");
        isBlank(String.valueOf(id), "id must not be empty");
        isNull(username,"Username cannot be blank");
        isBlank(username, "username must not be empty");
        isNull(password, "Password cannot be blank");
        isBlank(password, "password must not to empty");
        isNull(userRole, "Please select a role for the user");
        isNull(fullName, "Please Enter a name");
    }
    enum systemRole {
    ADMINISTRATOR,
        MANAGER,
        STAFF_USER;

    }


}
