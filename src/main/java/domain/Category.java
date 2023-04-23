package domain;

import java.util.UUID;

public enum Category {
    OFFICE365,
    PROGRAMMING,
    TESTING_TOOLS,
    VERSION_CONTROL;

    public UUID id;




    public UUID getId(){
        return id;
    }
}
