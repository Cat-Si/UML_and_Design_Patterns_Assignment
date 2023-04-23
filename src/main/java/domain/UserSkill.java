package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class UserSkill extends Validator {
    private UUID id;

    private Skill skill;

    private StaffUser staff;

    private SkillLevel strenghtOfSkills;

    private LocalDate expiry;

    private final ArrayList<Skill> currentSkills = new ArrayList<>();

    public UserSkill(UUID id, Skill skill, Skill skill1, StaffUser staff, SkillLevel strenghtOfSkills, LocalDate expiry) {
        this.id = id;
        this.skill = skill1;
        this.staff = staff;
        this.strenghtOfSkills = strenghtOfSkills;
        this.expiry = expiry;
    }

    public final void addSkill(Skill skill) {
        if (!currentSkills.contains(skill)) {
            currentSkills.add(skill);
        }
    }

    public void removeSkill(Skill skill) {
        if (currentSkills.contains(skill)) {
            currentSkills.remove(skill);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSkill)) return false;
        UserSkill userSkill = (UserSkill) o;
        return  Objects.equals(id, userSkill.id);
    }

    public UUID getID(){ return id;}

    public final Skill getSkill() {
        return skill; }

    public final ArrayList<Skill> getCurrentSkills() {return currentSkills; }

    @Override
    public String toString(){
        return String.format("%s: (number enrolled: %d)",
                staff, currentSkills.size());
    }
}
