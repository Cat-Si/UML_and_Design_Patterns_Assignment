package domain;

import domain.enumerators.SkillLevel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserSkill extends Validator {
    private UUID id;

    private StaffUser staff;

    private Skill skill;

    private SkillLevel strengthOfSkill;

    private Optional<LocalDate> expiry;

    private String notes;

    private final ArrayList<Skill> currentSkills = new ArrayList<>();

    public UserSkill(UUID id, StaffUser staff, Skill skill, SkillLevel strengthOfSkill, LocalDate expiry,
                     String notes) {
        this.id = id;
        this.staff = staff;
        this.skill = skill;
        this.strengthOfSkill = strengthOfSkill;
        this.expiry = Optional.ofNullable(expiry);
        this.notes = notes;
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
        if (this == o)
            return true;
        if (!(o instanceof UserSkill))
            return false;
        UserSkill userSkill = (UserSkill) o;
        return Objects.equals(skill, userSkill.skill) &&
                Objects.equals(staff, userSkill.staff);
    }

    public UUID getId() {
        return id;
    }

    public final StaffUser getStaff() {
        return staff;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public final Skill getSkill() {
        return skill;
    }

    public SkillLevel getStrengthOfSkill() {
        return strengthOfSkill;
    }

    public Optional<LocalDate> getExpiry() {
        return expiry;
    }

    public String getNotes() {
        return notes;
    }

    public final ArrayList<Skill> getCurrentSkills() {
        return currentSkills;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", skill, strengthOfSkill);
    }
}
