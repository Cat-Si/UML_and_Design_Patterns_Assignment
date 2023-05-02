package repositories.interfaces;

import Exceptions.EntryAlreadyExistsException;
import domain.Skill;
import domain.SkillLevel;
import domain.StaffUser;
import domain.UserSkill;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseUserSkillRepository {

    void add(UUID UUID, Skill mySkill, StaffUser staff, SkillLevel strengthOfSkills, LocalDate expiry, String notes) throws EntryAlreadyExistsException;

    List<UserSkill> getAll();

    Optional<List<Skill>> getSkillsForStaff(StaffUser s);

    void removeSkillFromStaff(StaffUser u, Skill s) throws IllegalArgumentException;
}

