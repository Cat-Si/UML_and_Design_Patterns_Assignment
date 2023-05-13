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

    public UserSkill(UUID id, StaffUser staff, Skill skill, SkillLevel strengthOfSkill, LocalDate expiry, String notes) {
        this.id =id;
        this.staff = staff;
        this.strengthOfSkill = strengthOfSkill;
        this.expiry = Optional.ofNullable(expiry);
        this.notes = notes;
        addSkill(skill);
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
        return  Objects.equals(skill, userSkill.skill) &&
                Objects.equals(staff, userSkill.staff);
    }

    public UUID getId() {
        return id;
    }

    public final StaffUser getStaff() {
        return staff;
    }

    public final Skill getSkill() {
        return skill;
    }

    public final ArrayList<Skill> getCurrentSkills() {
        return currentSkills;
    }

    @Override
    public String toString(){
        return String.format("%s: (number enrolled: %d)",
                staff, currentSkills.size());
    }

}
  /*  private UUID id;

    private StaffUser staff;

    private Skill mySkill;

    private String skillName;


    private SkillLevel strengthOfSkills;

    public Optional<LocalDate> getExpiry() {
        return expiry;
    }

    private Optional<LocalDate> expiry;

    private String notes;

    private final ArrayList<Skill> currentSkills = new ArrayList<>();

    public UserSkill(UUID id, StaffUser staff, Skill mySkill, SkillLevel strengthOfSkills, LocalDate expiry, String notes) {
        this.id = id;
        this.staff = staff;
        this.mySkill = mySkill;
        this.strengthOfSkills = strengthOfSkills;
        LocalDate.now();
        this.expiry = Optional.ofNullable(expiry);
        this.notes = notes;
        addSkill(mySkill);
    }


    public SkillLevel getStrengthOfSkills() {
        return strengthOfSkills;

    }
    public void addSkill(Skill mySkill) {
        if (!currentSkills.contains(mySkill)) {
            currentSkills.add(mySkill);
        }
    }

    public void removeSkill(String mySkill) {
        for (Skill s : currentSkills) {
            if (s.getSkillName().equals(mySkill)) {
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

    public UUID getId(){ return id;}

    public final Skill getMySkill() {
        return mySkill; }

    public StaffUser getStaff() {
        return staff;
    }

    public void setStaff(StaffUser staff) {
        this.staff = staff;
    }

    public void setMySkill(Skill mySkill) {
        this.mySkill = mySkill;
    }*/

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSkill)) return false;
        UserSkill userSkill = (UserSkill) o;
        return  Objects.equals(mySkill, userSkill.mySkill) &&
                Objects.equals(staff, userSkill.staff);
    }

    public final ArrayList<Skill> getCurrentSkills() {return currentSkills; }

    @Override
    public String toString(){
        return String.format("%s: (current number of skills: %d)",
                staff, currentSkills.size());
    }

    public String getSkillName() {
        return skillName;
    }*/

