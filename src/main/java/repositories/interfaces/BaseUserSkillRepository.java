package repositories.interfaces;

import Exceptions.EntryAlreadyExistsException;
import domain.Skill;
import domain.enumerators.SkillLevel;
import domain.StaffUser;
import domain.UserSkill;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseUserSkillRepository {

    void add(UUID UUID, StaffUser staff, Skill mySkill, SkillLevel strengthOfSkills, LocalDate expiry, String notes) throws EntryAlreadyExistsException;

    List<UserSkill> getAll();

    Optional<List<UserSkill>> getSkillsForStaff(StaffUser u);

    void removeSkillFromStaff(StaffUser u, UserSkill s) throws IllegalArgumentException;

    void edit(UserSkill userSkill);
}

