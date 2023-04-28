package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Category {

    private List<String> description;
    public Category () {
        description = new ArrayList<>();
        description.add("OFFICE365");
        description.add("PROGRAMMING");
        description.add("TESTING_TOOLS");
        description.add("VERSION_CONTROL");
    }

public List<String> getDescription(){
        return description;
}
    private UUID id;

    public Category(UUID id) {
        this.id = id;
    }

    public UUID getId(){
        return id;
    }
}
