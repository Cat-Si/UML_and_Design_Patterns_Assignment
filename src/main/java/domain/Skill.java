package domain;

import java.util.Objects;
import java.util.UUID;
public class Skill extends Validator{

    private Category category;

    private UUID id;
    private String skillName;
    public Skill(Category category, UUID id, String skillName){
        this.category = category;
        this.id = id;
        this.skillName = skillName;

        validate();
    }

    public UUID getId(){
        return id;
    }

    public String getSkillName(){

        return skillName;
    }

    public Category getCategory() {

        return category;
    }

    public void setSkillName(String skillName){
        this.skillName = skillName;
        validate();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private final void validate(){
        isNull(id,"id must not be null");
        isNull(category, "category must not be null");
        isNull(skillName,"name must not be null");
        isBlank(skillName,"name must not be blank");
    }

    @Override
    public String toString(){
        return String.format("%s (%s)", skillName, getCategory());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill)) return false;
        Skill skill = (Skill) o;
        return Objects.equals(skillName, skill.skillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skillName);
    }
}

