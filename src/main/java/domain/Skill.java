package domain;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
public class Skill extends Validator{

    private Category category;
    private UUID id;
    private String description;


    public Skill(Category category, UUID id, String description, String notes, LocalDate expiry, SkillLevel strengthOfSkills){
        this.category = category;
        this.id = id;
        this.description = description;

        validate();
    }


    public UUID getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
        validate();
    }


    private final void validate(){
        isNull(id,"id must not be null");
        isNull(description,"name must not be null");
        isBlank(description,"name must not be blank");
    }

    public String toString(){
        return String.format("%s", description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill)) return false;
        Skill skill = (Skill) o;
        return Objects.equals(description, skill.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}

