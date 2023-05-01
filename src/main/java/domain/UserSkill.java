package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserSkill extends Validator {
    private UUID id;

    private Skill mySkill;

    private StaffUser staff;

    private SkillLevel strengthOfSkills;

    public Optional<LocalDate> getExpiry() {
        return expiry;
    }

    private Optional<LocalDate> expiry;

    private String notes;

    private final ArrayList<Skill> currentSkills = new ArrayList<>();

    public final ArrayList<Skill> getCurrentSkills() {return currentSkills; }

    public UserSkill(UUID id, Skill mySkill, StaffUser staff, SkillLevel strengthOfSkills, Optional<LocalDate> expiry, String notes) {
        this.id = id;
        this.mySkill = mySkill;
        this.staff = staff;
        this.strengthOfSkills = strengthOfSkills;
        this.expiry = expiry;
        this.notes = notes;
    }

    public void addSkill(Skill mySkill) {
        if (!currentSkills.contains(mySkill)) {
            currentSkills.add(mySkill);
        }
    }

    public void removeSkill(String skillDescription) {
        for (Skill s : currentSkills) {
            if (s.getSkillName().equals(skillDescription)) {
                currentSkills.remove(s);
            }
        }
    }



    public void removeSkill(Skill mySkill) {
        if (currentSkills.contains(mySkill)) {
            currentSkills.remove(mySkill);
        }
    }

    public Skill[] viewCurrentSkills(){
        return currentSkills.toArray(new Skill[currentSkills.size()]);
    }

    public final Skill getMySkill() {
        return mySkill; }

    public void setMySkill(Skill mySkill) {
        this.mySkill = mySkill;
    }

    public UUID getId(){ return id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSkill)) return false;
        UserSkill userSkill = (UserSkill) o;
        return  Objects.equals(mySkill, userSkill.mySkill) &&
                Objects.equals(staff, userSkill.staff);
    }

    @Override
    public String toString(){
        return String.format("%s: (current number of skills: %d)",
                staff, currentSkills.size());
    }
}
