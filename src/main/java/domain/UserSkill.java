package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserSkill extends Validator {
    private UUID id;

    private Skill skill;

    private StaffUser staff;

    private SkillLevel strenghtOfSkills;

    private Optional<LocalDate> expiry = Optional.empty();

    private String notes;

    private final ArrayList<Skill> currentSkills = new ArrayList<>();

    public final ArrayList<Skill> getCurrentSkills() {return currentSkills; }

    public UserSkill(UUID id, Skill skill, Skill skill1, StaffUser staff, SkillLevel strenghtOfSkills, Optional<LocalDate> expiry) {
        this.id = id;
        this.skill = skill1;
        this.staff = staff;
        this.strenghtOfSkills = strenghtOfSkills;
        this.expiry = expiry;
    }

    public void addSkill(Skill skill) {
        if (!currentSkills.contains(skill)) {
            currentSkills.add(skill);
        }
    }

    public void removeSkill(Skill skill) {
        if (currentSkills.contains(skill)) {
            currentSkills.remove(skill);
        }
    }

    public Skill[] viewCurrentSkills(){
        return currentSkills.toArray(new Skill[currentSkills.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSkill)) return false;
        UserSkill userSkill = (UserSkill) o;
        return  Objects.equals(skill, userSkill.skill) &&
                Objects.equals(staff, userSkill.staff);
    }

    public UUID getID(){ return id;}

    public final Skill getSkill() {
        return skill; }

    @Override
    public String toString(){
        return String.format("%s: (current number of skills: %d)",
                staff, currentSkills.size());
    }
}
